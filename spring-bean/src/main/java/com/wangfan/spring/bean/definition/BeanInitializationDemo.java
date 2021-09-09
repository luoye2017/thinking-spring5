package com.wangfan.spring.bean.definition;

import com.wangfan.spring.bean.factory.DefaultUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 初始化 Demo
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-09
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);
        // 初始化容器
        context.refresh();
        DefaultUserFactory bean = context.getBean(DefaultUserFactory.class);
        // 关闭容器
        context.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
