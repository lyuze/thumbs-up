package com.lyz.thumbsup.demo;

import com.lyz.thumbsup.utils.JedisConnectionUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * @Author: liuyuze
 * @Date: 2019.11.5 13:49
 */
public class DistributedLock {

    /**
     * 获取锁
     * @param lockName 锁的名字
     * @param acquireTimeout 或得所的超时时间
     * @param lockTimeout 所本身的超时时间
     * @return
     */
    public String acquireLock(String lockName, long acquireTimeout, long lockTimeout){
        // 锁标识
        String identifler = UUID.randomUUID().toString();
        String lockKey = "lock" + lockName;
        int lockExpire = (int) (lockTimeout / 1000);
        Jedis jedis = null;

        try {
            jedis = JedisConnectionUtil.getJedis();

            long end = System.currentTimeMillis() + acquireTimeout;
            // 获取锁的限定时间
            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(lockKey, identifler) == 1) {
                    // 设置成功
                    // 设置超时时间
                    jedis.expire(lockKey, lockExpire);
                    // 或得锁成功
                    return identifler;
                }
                if (jedis.ttl(lockKey) == -1) {
                    // 如果一直没有或得锁设置超时时间
                    jedis.expire(lockKey, lockExpire);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 释放锁
     * @param lockName 锁的名字
     * @param identifler 锁标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifler){
        System.out.println(lockName + "释放锁" + identifler);
        String lockKey = "lock:" + lockName;
        Jedis jedis = null;
        boolean isrelease = false;
        try {
            jedis = JedisConnectionUtil.getJedis();
            while (true){
                jedis.watch(lockKey);
                // 判断是否是同一把锁
                if (identifler.equals(jedis.get(lockKey))){
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if (transaction.exec().isEmpty()){
                        continue;
                    }
                    isrelease = true;
                }
                // TODO 异常
                jedis.unwatch();
                break;
            }
        }finally {
            jedis.close();
        }
        return isrelease;
    }
}
