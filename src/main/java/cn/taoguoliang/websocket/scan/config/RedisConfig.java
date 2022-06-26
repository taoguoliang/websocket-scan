package cn.taoguoliang.websocket.scan.config;

import cn.taoguoliang.websocket.scan.util.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * redis配置
 *
 * @author taogl
 * @date 2022/6/26 15:29
 */
@Configuration
public class RedisConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RedisUtil.setRedisTemplate(applicationContext.getBean(StringRedisTemplate.class));
    }

}
