package com.wangfan.bean.lifecycle;

import com.wangfan.bean.lifecycle.pojo.Person;
import com.wangfan.dependency.injection.UserHolder;
import com.wangfan.ioc.overview.domain.SuperUser;
import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * Bean 实例化阶段
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-23
 */
public class MyInstantiationAwareBeanPostProcessorDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/bean-lifecycle-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(location);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println(superUser);

        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);

        beanFactory.getBean("userHolder", UserHolder.class);
    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "superUser") && beanClass.equals(SuperUser.class)) {
                return new SuperUser();
            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "user") && bean.getClass().equals(User.class)) {
                return false;
            }
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals(beanName, "person") && bean.getClass().equals(Person.class)) {
                MutablePropertyValues mutablePropertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    mutablePropertyValues = (MutablePropertyValues) pvs;
                } else {
                    mutablePropertyValues = new MutablePropertyValues();
                }

                mutablePropertyValues.addPropertyValue("number","1");
                if (mutablePropertyValues.contains("description")) {
                    mutablePropertyValues.removePropertyValue("description");
                    mutablePropertyValues.addPropertyValue("description","this is person V2");
                }
                return mutablePropertyValues;
            }
            return null;
        }
    }
}
