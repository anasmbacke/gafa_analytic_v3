package com.gafapay.elasticsearch.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MutableHttpServletRequest extends HttpServletRequestWrapper {
   private final Map<String, String> customHeaders = new HashMap();

   public MutableHttpServletRequest(HttpServletRequest request) {
      super(request);
   }

   public void putHeader(String name, String value) {
      this.customHeaders.put(name, value);
   }

   public String getHeader(String name) {
      String header = super.getHeader(name);
      return header != null ? header : super.getParameter(name);
   }

   public Enumeration<String> getHeaderNames() {
      List<String> names = Collections.list(super.getHeaderNames());
      names.addAll(this.customHeaders.keySet());
      names.addAll(Collections.list(super.getParameterNames()));
      return Collections.enumeration(names);
   }

   public Enumeration<String> getHeaders(String name) {
      Set<String> set = new HashSet();
      Optional var10000 = Optional.ofNullable((String)this.customHeaders.get(name));
      Objects.requireNonNull(set);
      var10000.ifPresent(set::add);
      Enumeration<String> e = ((HttpServletRequest)this.getRequest()).getHeaders(name);

      while(e.hasMoreElements()) {
         String n = (String)e.nextElement();
         set.add(n);
      }

      var10000 = Optional.ofNullable((String)this.customHeaders.get(name));
      Objects.requireNonNull(set);
      var10000.ifPresent(set::add);
      return Collections.enumeration(set);
   }
}
