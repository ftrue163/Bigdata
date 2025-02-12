package com.atguigu.gmall.realtime.util

import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

object MyRedisUtils {
    var jedisPool: JedisPool = null

    def getJedisFromPool(): Jedis = {
        if (jedisPool == null) {
            var host: String = PropertiesUtils("redis.host")
            var port: String = PropertiesUtils("redis.port")
            val jedisPoolConfig = new JedisPoolConfig()
            jedisPoolConfig.setMaxTotal(100) //最大连接数
            jedisPoolConfig.setMaxIdle(20) //最大空闲
            jedisPoolConfig.setMinIdle(20) //最小空闲
            jedisPoolConfig.setBlockWhenExhausted(true) //忙碌时是否等待
            jedisPoolConfig.setMaxWaitMillis(5000) //忙碌时等待时长 毫秒
            jedisPoolConfig.setTestOnBorrow(true) //每次获得连接的进行测试

            jedisPool = new JedisPool(jedisPoolConfig, host, port.toInt)
        }
        jedisPool.getResource
    }
}
