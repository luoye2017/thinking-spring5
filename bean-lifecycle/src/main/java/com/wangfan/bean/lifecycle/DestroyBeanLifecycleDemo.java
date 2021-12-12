package com.wangfan.bean.lifecycle;

import com.wangfan.bean.lifecycle.pojo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-25
 */
public class DestroyBeanLifecycleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DestroyBeanLifecycleDemo.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    @Bean
    Person person() {
        return new Person();
    }
}
