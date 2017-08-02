package com.myorg.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@PropertySource(value = { "classpath:application.properties" })

@Import(value = MongoAutoConfiguration.class)
public class DataConfigMongo extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	@Autowired
	private Mongo mongo;

	@Autowired
	private MongoProperties mongoProperties;

	@Override
	public String getDatabaseName() {
		mongoProperties.setDatabase(database);
		mongoProperties.setUri(mongoUri);
		return mongoProperties.getDatabase();
	}

	@Override
	public Mongo mongo() throws Exception {
		return mongo;
	}
}
