package com.wangfan.spring.bean.definition;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注解 BeanDefinition 示例
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-06
 */

// 3 通过@Import 导入的方式
@Import(value = AnnotationBeanDefinitionDemo.Configure.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class (配置类)
        context.register(AnnotationBeanDefinitionDemo.class);
        // 启动 applicationContext
        context.refresh();

        // 通过 BeanDefinition 注册API实现
        // 1. 命名 Bean 的注册方式
        registerUserBeanDefinition(context, "test-user");
        // 2. 非命名 Bean 的注册方式
        registerUserBeanDefinition(context);
        // 按照类型依赖查找
        System.out.println("通过依赖查找，找到 Configure类型的Bean信息：" + context.getBeansOfType(Configure.class));
        System.out.println("通过依赖查找，找到 User类型的Bean信息：" + context.getBeansOfType(User.class));
        System.out.println("通过依赖查找，找到 AnnotationBeanDefinitionDemo类型的Bean信息：" + context.getBeansOfType(AnnotationBeanDefinitionDemo.class));

        // 手动关闭 applicationContext
        context.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = genericBeanDefinition(User.class);
        builder.addPropertyValue("id", "1").addPropertyValue("name", "sss");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    // 2 通过 @Component的方式注册Bean
    @Component
    public static class Configure {

        // 1 通过@Bean的方式注册Bean
        @Bean
        public User user() {
            User user = new User();
            user.setName("Jack");
            user.setId("1");
            return user;
        }
    }
}

