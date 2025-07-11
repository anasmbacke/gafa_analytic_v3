package com.gafapay.elasticsearch.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {
   @Value("${aws.s3.access.key}")
   private String accessKeyId;
   @Value("${aws.s3.secret.key}")
   private String secretAccessKey;
   @Value("${aws.s3.region}")
   private String region;

   public AwsS3Config() {
   }

   @Bean({"amazonS3"})
   public AmazonS3 getAmazonS3Client() {
      BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(this.accessKeyId, this.secretAccessKey);
      return (AmazonS3)((AmazonS3ClientBuilder)((AmazonS3ClientBuilder)AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(this.region))).withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))).build();
   }
}
