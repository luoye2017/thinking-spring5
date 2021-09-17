package com.wangfan.dependency.lookup;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的查找方式
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-12
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 演示 beanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderGetIfAvailable(applicationContext);
        // 演示 ObjectFactory#getBeansOfType 方法的安全性
        displayObjectFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider#stream 方法的安全性
        displayObjectProviderStreamOps(applicationContext);

        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printTrackException("displayObjectProviderStreamOps", () -> beanProvider.stream().forEach(System.out::println));
    }

    private static void displayObjectFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        printTrackException("displayObjectFactoryGetBeansOfType", () -> applicationContext.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        printTrackException("displayObjectProviderGetIfAvailable", () -> applicationContext.getBeanProvider(User.class).getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printTrackException("displayObjectFactoryGetObject", () -> objectFactory.getObject());
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        printTrackException("displayBeanFactoryGetBean", () -> applicationContext.getBean(User.class));
    }

    private static void printTrackException(String source, Runnable runnable) {
        System.err.println("======================");
        System.err.println("source: " + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }
}
