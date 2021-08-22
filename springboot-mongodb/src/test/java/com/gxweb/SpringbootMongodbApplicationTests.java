package com.gxweb;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 布隆过滤器 https://mp.weixin.qq.com/s/5yKIUF840lWOt2A_JxJarQ
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootMongodbApplicationTests {

    private static int size = 1000000;//预计要插入多少数据
    private static double fpp = 0.01;//期望的误判率
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    @Test
    public void bloom() {
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
    }
}
/**
 * redis实现布隆过滤器
 * <p>
 * 上面使用guava实现布隆过滤器是把数据放在本地内存中，无法实现布隆过滤器的共享。
 * 我们还可以把数据放在redis中，用 redis来实现布隆过滤器，我们要使用的数据结构是bitmap，你可能会有疑问，redis支持五种数据结构：String，List，Hash，Set，ZSet，没有bitmap呀。没错，实际上bitmap的本质还是String。
 * 可能有小伙伴会说，纳尼，布隆过滤器还没介绍完，怎么又出来一个bitmap，没事，你可以把bitmap就理解为一个二进制向量。
 * 要用redis来实现布隆过滤器，我们需要自己设计映射函数，自己度量二进制向量的长度，这对我来说，无疑是一个不可能完成的任务，只能借助搜索引擎，下面直接放出代码把。
 */

//public class RedisMain {
//    static final int expectedInsertions = 100;//要插入多少数据
//    static final double fpp = 0.01;//期望的误判率
//
//    //bit数组长度
//    private static long numBits;
//
//    //hash函数数量
//    private static int numHashFunctions;
//
//    static {
//        numBits = optimalNumOfBits(expectedInsertions, fpp);
//        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
//    }
//
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("192.168.0.109", 6379);
//        for (int i = 0; i < 100; i++) {
//            long[] indexs = getIndexs(String.valueOf(i));
//            for (long index : indexs) {
//                jedis.setbit("codebear:bloom", index, true);
//            }
//        }
//        for (int i = 0; i < 100; i++) {
//            long[] indexs = getIndexs(String.valueOf(i));
//            for (long index : indexs) {
//                Boolean isContain = jedis.getbit("codebear:bloom", index);
//                if (!isContain) {
//                    System.out.println(i + "肯定没有重复");
//                }
//            }
//            System.out.println(i + "可能重复");
//        }
//    }
//
//    /**
//     * 根据key获取bitmap下标
//     */
//    private static long[] getIndexs(String key) {
//        long hash1 = hash(key);
//        long hash2 = hash1 >>> 16;
//        long[] result = new long[numHashFunctions];
//        for (int i = 0; i < numHashFunctions; i++) {
//            long combinedHash = hash1 + i * hash2;
//            if (combinedHash < 0) {
//                combinedHash = ~combinedHash;
//            }
//            result[i] = combinedHash % numBits;
//        }
//        return result;
//    }
//
//    private static long hash(String key) {
//        Charset charset = Charset.forName("UTF-8");
//        return Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asLong();
//    }
//
//    //计算hash函数个数
//    private static int optimalNumOfHashFunctions(long n, long m) {
//        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
//    }
//
//    //计算bit数组长度
//    private static long optimalNumOfBits(long n, double p) {
//        if (p == 0) {
//            p = Double.MIN_VALUE;
//        }
//        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
//    }
//}