package com.wangfan.bean.lifecycle;

import com.wangfan.ioc.overview.domain.SuperUser;
import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-23
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);
    }
}
