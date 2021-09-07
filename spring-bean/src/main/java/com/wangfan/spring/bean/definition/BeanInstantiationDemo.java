package com.wangfan.spring.bean.definition;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化 Demo
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-07
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = context.getBean("create-user-by-static-method", User.class);
        User userByInstanceMethod = context.getBean("create-user-by-instance-method", User.class);
        User userByFactoryBean = context.getBean("create-user-by-factory-bean", User.class);
        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);
        System.out.println(user == userByInstanceMethod);
        System.out.println(user == userByFactoryBean);
    }
}
