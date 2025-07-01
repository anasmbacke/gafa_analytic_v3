package com.gafapay.elasticsearch.database.repository.redis;

import com.gafapay.elasticsearch.database.model.UserAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisUserSecretDao extends CrudRepository<UserAuth, String> {
}
