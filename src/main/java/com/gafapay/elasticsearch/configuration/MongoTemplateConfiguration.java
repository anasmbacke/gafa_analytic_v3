package com.gafapay.elasticsearch.configuration;

import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoTemplateConfiguration {
   private final MongoMappingContext mongoMappingContext;

   @Autowired
   public MongoTemplateConfiguration(MongoMappingContext mongoMappingContext) {
      this.mongoMappingContext = mongoMappingContext;
   }

   @Bean
   public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
      DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
      MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext);
      converter.setTypeMapper(new DefaultMongoTypeMapper((String)null));
      MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, converter);
      mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);
      return mongoTemplate;
   }
}
