package vn.khanhduc.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.khanhduc.springboot.model.RedisToken;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
