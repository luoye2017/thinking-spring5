package com.wangfan.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Auto-Writing 方式 byName or byType 自动注入
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-10-17
 */
public class AutoWritingConstructorDependencySetterInjectionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载XML资源，解析并生成 BeanDefinition
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        String resourcePath = "classpath:/META-INF/auto-writing-dependency-constructor-injection-context.xml";
        definitionReader.loadBeanDefinitions(resourcePath);
        // 依赖查找并且创建 Bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);
    }
}
