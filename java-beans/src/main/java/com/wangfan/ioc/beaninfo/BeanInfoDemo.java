package com.wangfan.ioc.beaninfo;

import com.wangfan.ioc.pojo.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author 110634
 * @since 2021-08-26
 */
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            // propertyDescriptor 允许添加数据编辑器 - PropertyEditor
            System.out.println(propertyDescriptor.toString());
        }
    }
}
