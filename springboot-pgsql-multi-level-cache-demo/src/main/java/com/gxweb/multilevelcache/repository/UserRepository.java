package com.gxweb.multilevelcache.repository;

import com.gxweb.multilevelcache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 查询登录次数前100的热点用户（用于缓存预热）
     */
    List<User> findTop100ByOrderByLoginCountDesc();

}