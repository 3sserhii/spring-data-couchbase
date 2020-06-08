package ssserhii.spring.data.composite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;

@Configuration
@EnableCouchbaseRepositories(basePackageClasses = {
        UserRepository.class
})
public class Config extends AbstractCouchbaseConfiguration {
    public static final String BUCKET_NAME = "mybucket";
    @Autowired
    CouchbaseContainer couchbaseContainer;

    @Override
    public String getConnectionString() {
        return couchbaseContainer.getConnectionString();
    }

    @Override
    public String getUserName() {
        return couchbaseContainer.getUsername();
    }

    @Override
    public String getPassword() {
        return couchbaseContainer.getPassword();
    }

    @Override
    public String getBucketName() {
        return BUCKET_NAME;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public CouchbaseContainer couchbaseContainer() {
        BucketDefinition bucketDefinition = new BucketDefinition(BUCKET_NAME);
        return new CouchbaseContainer()
                .withBucket(bucketDefinition);
    }
}