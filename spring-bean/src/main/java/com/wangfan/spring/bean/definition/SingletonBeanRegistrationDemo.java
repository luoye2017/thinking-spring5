package com.wangfan.spring.bean.definition;

import com.wangfan.ioc.overview.domain.User;
import com.wangfan.spring.bean.factory.DefaultUserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean 实例注册
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-09
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        User user = new User();
        // 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 注册Bean
        beanFactory.registerSingleton("user", user);
        // 初始化容器
        context.refresh();
        User bean = context.getBean(User.class);
        System.out.println("user == bean: " + (bean == user));
        // 关闭容器
        context.close();
    }
}
