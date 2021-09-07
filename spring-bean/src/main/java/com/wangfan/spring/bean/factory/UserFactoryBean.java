package com.wangfan.spring.bean.factory;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link com.wangfan.ioc.overview.domain.User} Bean 的 {@link org.springframework.beans.factory.FactoryBean} 的实现
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-07
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        User user = new User();
        user.setName("王凡");
        user.setId("1");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
