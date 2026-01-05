package com.gxweb.multilevelcache;

import com.gxweb.multilevelcache.model.User;
import com.gxweb.multilevelcache.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MultiLevelCacheApplication.class)
public class CacheServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testCacheFlow() {
        // 1. 首次查询（缓存未命中，查询数据库）
        User user = userService.getUserById(1L);
        assertNotNull(user);

        // 2. 二次查询（缓存命中，从缓存获取）
        User cachedUser = userService.getUserById(1L);
        assertEquals(user.getId(), cachedUser.getId());

        // 3. 更新用户（清理缓存）
        user.setNickname("测试昵称");
        User updatedUser = userService.updateUser(user);
        assertEquals("测试昵称", updatedUser.getNickname());

        // 4. 验证缓存已更新
        User newCachedUser = userService.getUserById(1L);
        assertEquals("测试昵称", newCachedUser.getNickname());
    }
}