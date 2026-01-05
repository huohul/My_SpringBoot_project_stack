package com.gxweb.multilevelcache.service;

import com.gxweb.multilevelcache.cache.CacheService;
import com.gxweb.multilevelcache.model.User;
import com.gxweb.multilevelcache.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    private static final String USER_CACHE = "userCache";
    @Resource
    private UserRepository userRepository;
    @Resource
    private CacheService cacheService;

    /**
     * 根据ID查询用户（带缓存保护）
     */
    public User getUserById(Long userId) {
        String key = "user:" + userId;
        // 带穿透/击穿保护的缓存查询
        return cacheService.getWithProtection(
                USER_CACHE,
                key,
                User.class,
                () -> userRepository.findById(userId).orElse(null)
        );
    }

    /**
     * 更新用户（更新后清理缓存）
     */
    @Transactional
    public User updateUser(User user) {
        User updated = userRepository.save(user);
        // 清理缓存
        String key = "user:" + user.getId();
        cacheService.evict(USER_CACHE, key);
        return updated;
    }

    /**
     * 缓存预热（应用启动后执行）
     */
    @EventListener(ApplicationReadyEvent.class)
    public void warmUpCache() {
        // 加载登录次数前100的热点用户
        List<User> hotUsers = userRepository.findTop100ByOrderByLoginCountDesc();
        hotUsers.forEach(user -> {
            String key = "user:" + user.getId();
            cacheService.put(USER_CACHE, key, user);
        });
        System.out.println("缓存预热完成，加载热点用户数：" + hotUsers.size());
    }
}