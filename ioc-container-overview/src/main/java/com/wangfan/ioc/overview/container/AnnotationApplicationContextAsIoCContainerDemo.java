package com.wangfan.ioc.overview.container;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-08-30
 */
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建ApplicationContext (BeanFactory工厂)
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextAsIoCContainerDemo 作为配置类（Configuration Class）
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        // 启动并应用applicationContext
        applicationContext.refresh();
        // 依赖查找对象
        lookupCollection(applicationContext);
        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId("1");
        user.setName("王凡");
        return user;
    }

    private static void lookupCollection(BeanFactory beanFactory) {
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
        System.out.println(map);
    }
}
