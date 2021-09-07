package com.wangfan.spring.bean.definition;

import com.wangfan.ioc.overview.domain.User;
import com.wangfan.spring.bean.factory.DefaultUserFactory;
import com.wangfan.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化方式
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-07
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory beanFactory = context.getBeanFactory();
        DefaultUserFactory factory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(factory.createUser());

        ServiceLoader<UserFactory> serviceLoader = context.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        demoServiceLoader();
    }

    private static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        for (UserFactory factory : serviceLoader) {
            System.out.println(factory.createUser());
        }
    }
}
