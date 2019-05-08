package com.example.demo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class AppConfigLog {

    @ApolloConfig("provider")
    private Config config;

    @ApolloConfigChangeListener("provider")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        Set<String> changedKeys = changeEvent.changedKeys();
        changedKeys.forEach(item -> {
            log.info("配置[{}] 由 [{}] 改成 [{}]", item, changeEvent.getChange(item).getOldValue(), changeEvent.getChange(item).getNewValue());
        });
    }
}
