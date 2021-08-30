package com.wangfan.ioc.overview.dependency.injection;

import com.wangfan.ioc.overview.annotation.Super;
import com.wangfan.ioc.overview.domain.User;
import com.wangfan.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-08-29
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义Bean
        UserRepository repository = (UserRepository) applicationContext.getBean("userRepository");
        System.out.println(repository.getUserList());

        // 依赖来源二：容器内建依赖Bean
        System.out.println(repository.getBeanFactory());
        // 下面两个输出可以看出 ApplicationContext 不等于 BeanFactory
        // 虽然 ApplicationContext 继承与 BeanFactory的接口
        // 它相当于是一个 代理模式，在AbstractRefreshableApplicationContext类中设置设置了BeanFactory 代理了实际的BeanFactory
        // ClassPathXmlApplicationContext#getBean() 的方法实际上就是 内部beanFactory#getBean()
        // 这里有点类似与 Spring Kafka 中的 CloseSafeProducer 和 Producer 的关系
        System.out.println(repository.getBeanFactory().equals(applicationContext));
        System.out.println(repository.getBeanFactory().equals(applicationContext.getBeanFactory()));


        // 依赖查找（错误）
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器内建Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }
}
