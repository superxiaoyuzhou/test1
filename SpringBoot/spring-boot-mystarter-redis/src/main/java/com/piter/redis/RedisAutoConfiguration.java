package com.piter.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;


//开启配置
@Configuration
//存在对应类时，加载该配置类
@ConditionalOnClass(Jedis.class)
//开启使用映射实体对象
@EnableConfigurationProperties(RedisProperties.class)
//存在对应配置信息时初始化该配置类
@ConditionalOnProperty(
        prefix = "redis",   // 存在配置前缀redis
        value = "enabled",  //开启
        matchIfMissing = true)  //缺失检查
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis(RedisProperties redisProperties) {
        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
    }
}
