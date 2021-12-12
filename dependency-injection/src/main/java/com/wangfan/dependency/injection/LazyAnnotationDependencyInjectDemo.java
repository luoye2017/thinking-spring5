package com.wangfan.dependency.injection;

import com.wangfan.dependency.injection.annotation.UserGroup;
import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * {@link ObjectProvider} 注解依赖注入
 *
 * @see ObjectProvider
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-15
 */
public class LazyAnnotationDependencyInjectDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<List<User>> userObjectFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectDemo.class);

        // 加载XML资源，解析并生成 BeanDefinition
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并创建 Bean
        LazyAnnotationDependencyInjectDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectDemo.class);

        System.out.println("user:" + demo.user);
        System.out.println("objectProvider:" + demo.userObjectProvider.getObject());
        System.out.println("objectFactory:" + demo.userObjectFactory.getObject());

        System.out.println("objectProvider all User");
        demo.userObjectProvider.forEach(System.out::println);

        // 关闭应用上下文
        applicationContext.close();
    }

}
