package com.wangfan.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过层次性依赖查找 {@link org.springframework.beans.factory.HierarchicalBeanFactory}
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-12
 */
public class HierarchicalDependencyDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 ObjectProviderDemo 作为配置类（Configuration Class）
        applicationContext.register(HierarchicalDependencyDemo.class);

        // 1. 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 查找Parent BeanFactory
//        System.out.println("applicationContext 的 ParentBeanFactory: " + beanFactory.getParentBeanFactory());
        // 设置Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
//        System.out.println("applicationContext 的 ParentBeanFactory: " + beanFactory);

        // 自己手写双亲委派的依赖查找
        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");

        // 启动应用上下文
        applicationContext.refresh();
        // 关闭应用上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含 某个BeanName[%s]: %s\n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    private static Boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory factory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            return containsBean(factory, beanName);
        }
        return beanFactory.containsBean(beanName);
    }


    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含 某个BeanName[%s]: %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }


    private static HierarchicalBeanFactory createParentBeanFactory() {
        return new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
    }
}
