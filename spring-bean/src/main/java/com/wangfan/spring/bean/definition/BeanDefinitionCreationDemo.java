package com.wangfan.spring.bean.definition;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建事例
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-02
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 1 通过BeanDefinitionBuilder 构建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        builder.addPropertyValue("id",1)
                .addPropertyValue("name","王凡");
        // 获取 BeanDefinition实例
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        // beanDefinition 并非Bean最终形态，可以自定义修改

        // 2 通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过 MutablePropertyValues批量操作属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id",1)
                .add("name",propertyValues);
        // 通过 set MutablePropertyValues 批量操作属性
        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}
