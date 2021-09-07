package com.wangfan.spring.bean.factory;

import com.wangfan.ioc.overview.domain.User;

/**
 * {@link com.wangfan.ioc.overview.domain.User} 工厂类
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-07
 */
public interface UserFactory {

     default User createUser(){
        User user = new User();
        user.setName("王凡");
        user.setId("1");
        return user;
    }
}
