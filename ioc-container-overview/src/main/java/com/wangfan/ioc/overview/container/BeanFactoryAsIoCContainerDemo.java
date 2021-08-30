package com.wangfan.ioc.overview.container;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-08-30
 */
public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 配置文件 ClassPath 路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载配置
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("Bean Definition count:" + beanDefinitionCount);
        // 依赖查找对象
        lookupCollection(beanFactory);
    }

    private static void lookupCollection(BeanFactory beanFactory) {
        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
        Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
        System.out.println(map);
    }
}
