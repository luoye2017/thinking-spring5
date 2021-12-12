package com.wangfan.bean.lifecycle.pojo;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-24
 */
public class Person implements DisposableBean, BeanNameAware {

    private User user;

    private Integer number;

    private String description;

    public Person(User user) {
        this.user = user;
    }

    public Person() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }

    @PreDestroy
    public void preDestroyAnnotationDestroyMethod() {
        System.out.println("beanName:" + beanName + "  preDestroyAnnotationDestroyMethod");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("beanName:" + beanName + "  interface DisposableBean");
    }

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
