package com.wangfan.ioc.overview.dependency.lookup;

import com.wangfan.ioc.overview.annotation.Super;
import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-08-29
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
//        lookupRealTime(beanFactory);
//        lookupLazy(beanFactory);
        lookupByType(beanFactory);
        lookupList(beanFactory);
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> beans = listBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("注解查找" + beans);
        }
    }

    private static void lookupList(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listBeanFactory.getBeansOfType(User.class);
            System.out.println("查找列表" + map);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("类型查找" + user);
    }

    private static void lookupLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找" + user);
    }

    private static void lookupRealTime(BeanFactory beanFactory) {
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找" + user);
    }
}
