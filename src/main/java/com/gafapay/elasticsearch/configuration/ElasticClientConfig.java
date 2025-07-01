package com.gafapay.elasticsearch.configuration;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
public class ElasticClientConfig extends AbstractElasticsearchConfiguration {
   @Value("${spring.elasticsearch.uris}")
   private String hostUrl;
   @Value("${spring.elasticsearch.username}")
   private String userName;
   @Value("${spring.elasticsearch.password}")
   private String password;
   @Value("${spring.elasticsearch.port}")
   private Integer port;
   @Value("${spring.elasticsearch.host.type}")
   private String hostType;

   public ElasticClientConfig() {
   }

   @Bean
   public RestHighLevelClient elasticsearchClient() {
      CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
      credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.userName, this.password));
      RestClientBuilder builder = RestClient.builder(new HttpHost[]{new HttpHost(this.hostUrl, this.port, this.hostType)}).setHttpClientConfigCallback((httpAsyncClientBuilder) -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
      builder.setRequestConfigCallback((requestConfigBuilder) -> requestConfigBuilder.setSocketTimeout(60000));
      return new RestHighLevelClient(builder);
   }

   @Bean
   public ElasticsearchRestTemplate elasticsearchRestTemplate() {
      return new ElasticsearchRestTemplate(this.elasticsearchClient());
   }
}
