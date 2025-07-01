package com.gafapay.elasticsearch.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gafapay.elasticsearch.database.model.UserAuth;
import com.gafapay.elasticsearch.database.repository.redis.RedisUserSecretDao;
import com.gafapay.elasticsearch.helper.ElasticSearchUtility;
import com.gafapay.elasticsearch.helper.MutableHttpServletRequest;
import com.gafapay.elasticsearch.utils.Logger;
import com.gafapay.elasticsearch.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthorizationFilterSpring extends OncePerRequestFilter {
   private final RedisUserSecretDao redisUserSecretDao;
   private final ElasticSearchUtility elasticSearchUtility;
   private String encryptedToken = "";
   private Map<String, Object> map = null;

   public CustomAuthorizationFilterSpring(RedisUserSecretDao redisUserSecretDao, ElasticSearchUtility elasticSearchUtility) {
      this.redisUserSecretDao = redisUserSecretDao;
      this.elasticSearchUtility = elasticSearchUtility;
   }

   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
      Optional<UserAuth> sub = Optional.empty();

      try {
         this.encryptedToken = request.getHeader("Authorization").split(" ")[1];
         String decodedBase64 = new String((new Base64()).decode(this.encryptedToken.split("\\.")[1].getBytes()));
         this.map = (Map)(new ObjectMapper()).readValue(decodedBase64, new TypeReference<Map<String, Object>>() {
         });
         sub = this.redisUserSecretDao.findById(String.valueOf(this.map.get("sub")));
         if (sub.isEmpty() || StringUtils.isEmpty(((UserAuth)sub.get()).getSecret_key()) || !((UserAuth)sub.get()).getUser_token().contains(this.encryptedToken)) {
            this.sendErrorResponse(response);
            return;
         }

         DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(((UserAuth)sub.get()).getSecret_key().getBytes())).build().verify(this.encryptedToken);
         String userName = decodedJWT.getSubject();
         Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList();
         Arrays.stream((String[])decodedJWT.getClaim("roles").asArray(String.class)).forEach((role) -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role)));
         SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userName, (Object)null, simpleGrantedAuthorities));
         MutableHttpServletRequest mutableHttpServletRequest = new MutableHttpServletRequest(request);
         mutableHttpServletRequest.putHeader("token_user_id", userName);
         MDC.put("user_id", userName);
         filterChain.doFilter(mutableHttpServletRequest, response);
      } catch (NullPointerException | ArrayIndexOutOfBoundsException | JWTDecodeException | AlgorithmMismatchException | SignatureVerificationException signatureVerificationException) {
         this.sendErrorResponse(response);
         Logger.error("Error : " + ExceptionUtils.getStackTrace(signatureVerificationException));
      } catch (TokenExpiredException tokenExpiredException) {
         Logger.error("Error : " + ExceptionUtils.getStackTrace(tokenExpiredException));
         this.elasticSearchUtility.sendRemoveExpiredTokenDataToProducer(this.encryptedToken, this.map.get("sub").toString(), request.getHeader("RequestID") == null ? Utils.generateUUID() : request.getHeader("RequestID"), (UserAuth)sub.orElse((Object)null));
         this.sendErrorResponse(response);
      } catch (Exception exception) {
         throw new RuntimeException(exception);
      }

   }

   public void sendErrorResponse(HttpServletResponse response) {
      Map<String, Object> mapBodyException = new HashMap();
      mapBodyException.put("status", "error");
      mapBodyException.put("code", HttpStatus.UNAUTHORIZED.value());
      mapBodyException.put("message", HttpStatus.UNAUTHORIZED.name());
      response.setContentType("application/json");
      response.setStatus(HttpStatus.UNAUTHORIZED.value());

      try {
         (new ObjectMapper()).writeValue(response.getOutputStream(), mapBodyException);
      } catch (IOException exception) {
         throw new RuntimeException(exception);
      }
   }
}
