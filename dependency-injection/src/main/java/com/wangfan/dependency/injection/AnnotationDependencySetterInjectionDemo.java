package com.wangfan.dependency.injection;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-10-17
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        // 加载XML资源，解析并生成 BeanDefinition
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并创建 Bean
        UserHolder bean = applicationContext.getBean(UserHolder.class);
        System.out.println(bean);

        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean
    UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
