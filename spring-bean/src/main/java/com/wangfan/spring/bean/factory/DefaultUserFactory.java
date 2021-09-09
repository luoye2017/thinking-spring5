package com.wangfan.spring.bean.factory;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link UserFactory} 的默认实现
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-07
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void initBeanFactory(){
        System.out.println("@PostConstruct 初始化 Bean");
    }

    public void initMethod() {
        System.out.println("InitMethod 初始化 Bean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("继承 InitializingBean接口 初始化 Bean");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy 销毁 Bean");
    }

    public void destroyMethod() {
        System.out.println("destroyMethod 销毁 Bean");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("继承 DisposableBean接口 销毁 Bean");
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("Spring Bean 正在被垃圾回收");
    }
}
