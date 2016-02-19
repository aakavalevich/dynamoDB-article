package org.article.config;

import org.apache.commons.lang3.StringUtils;

import org.socialsignin.spring.data.dynamodb.core.DynamoDBOperations;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBTemplate;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.regions.Regions;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@EnableDynamoDBRepositories(basePackages = "org.article.repo", dynamoDBOperationsRef = "dynamoDBOperations")
@Configuration
public class DynamoDBConfig {

    @Value("${amazonDynamodbEndpoint}")
    private String amazonDynamoDBEndpoint;
    @Value("${environment}")
    private String environment;
    @Value("${region}")
    private String region;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setSignerRegionOverride(Regions.fromName(region).getName());
        if (StringUtils.isNotEmpty(amazonDynamoDBEndpoint)) {
            client.setEndpoint(amazonDynamoDBEndpoint);
        }

        return client;
    }

    @Bean
    public DynamoDBOperations dynamoDBOperations() {
        final DynamoDBTemplate dynamoDBTemplate = new DynamoDBTemplate(amazonDynamoDB());
        final DynamoDBMapperConfig.TableNameOverride tableNameOverride = DynamoDBMapperConfig.TableNameOverride
                .withTableNamePrefix(environment);
        dynamoDBTemplate.setDynamoDBMapperConfig(new DynamoDBMapperConfig(tableNameOverride));

        return dynamoDBTemplate;
    }

}
