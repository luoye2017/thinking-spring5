package com.wangfan.ioc.overview.repository;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;

import java.util.List;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-08-29
 */
public class UserRepository {

    private List<User> userList;

    private BeanFactory beanFactory;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
