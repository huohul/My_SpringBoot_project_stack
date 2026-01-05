//package com.gxweb.multilevelcache.cache;
//
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
///**
// * Redis Pub/Sub 实现缓存一致性通知
// */
//@Component
//public class CacheConsistencyListener implements MessageListener {
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Resource
//    private RedisMessageListenerContainer redisMessageListenerContainer;
//
//    @Resource
//    private MultiLevelCacheManager cacheManager;
//
//    private static final String CACHE_EVICT_TOPIC = "cache:evict:topic";
//
//    @PostConstruct
//    public void init() {
//        // 订阅缓存清理主题
//        redisMessageListenerContainer.addMessageListener(this, new ChannelTopic(CACHE_EVICT_TOPIC));
//    }
//
//    /**
//     * 接收其他节点的缓存清理通知
//     */
//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String messageBody = redisTemplate.getStringSerializer().deserialize(message.getBody());
//        if (messageBody == null) {
//            return;
//        }
//
//        // 消息格式：cacheName:key
//        String[] parts = messageBody.split(":");
//        if (parts.length == 2) {
//            String cacheName = parts[0];
//            String key = parts[1];
//            // 清理本地缓存
//            cacheManager.getCache(cacheName).evict(key);
//            System.out.println("收到缓存清理通知，cacheName=" + cacheName + ", key=" + key);
//        }
//    }
//
//    /**
//     * 发送缓存清理通知
//     */
//    public void publishEvictNotice(String cacheName, String key) {
//        String message = cacheName + ":" + key;
//        redisTemplate.convertAndSend(CACHE_EVICT_TOPIC, message);
//    }
//}