package com.wangfan.dependency.injection;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanNameAware;

/**
 * {@link com.wangfan.ioc.overview.domain.User} 管理
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-10-17
 */
public class UserHolder implements BeanNameAware {

    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    private User user;

    private Integer id;

    private String name;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("setBeanName: BeanToString:" + this.toString());
    }
}
