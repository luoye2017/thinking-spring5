package com.wangfan.dependency.injection;

import com.wangfan.dependency.injection.annotation.UserGroup;
import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * {@link Qualifier} 注解依赖注入
 *
 * @see Qualifier
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-15
 */
public class QualifierAnnotationDependencyInjectDemo {


    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User nameUser;

    @Autowired
    @Qualifier
    private List<User> qualifierUsers;

    @Autowired
    private List<User> noQualifierUser;

    @Autowired
    @UserGroup
    private List<User> userGroupUsers;


    @Bean
    @Qualifier
    public User user1() {
        User user = new User();
        user.setName("Qualifier user");
        return user;
    }

    @Bean
    @Qualifier
    public User user2() {
        User user = new User();
        user.setName("Qualifier user");
        return user;
    }

    @Bean
    @UserGroup
    public User user3() {
        User user = new User();
        user.setName("UserGroup user");
        return user;
    }

    @Bean
    @UserGroup
    public User user4() {
        User user = new User();
        user.setName("UserGroup user");
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectDemo.class);

        // 加载XML资源，解析并生成 BeanDefinition
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并创建 Bean
        QualifierAnnotationDependencyInjectDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectDemo.class);

        System.out.println("user:" + demo.user);
        System.out.println("nameUser:" + demo.nameUser);
        System.out.println("no qualifier Users" + demo.noQualifierUser);
        System.out.println("qualifier Users" + demo.qualifierUsers);
        System.out.println("group user Users" + demo.userGroupUsers);

        // 关闭应用上下文
        applicationContext.close();
    }

}
