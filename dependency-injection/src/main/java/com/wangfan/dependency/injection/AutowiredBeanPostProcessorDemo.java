package com.wangfan.dependency.injection;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * {@link AutowiredAnnotationBeanPostProcessor} 源码跟读
 *
 * @see AutowiredAnnotationBeanPostProcessor
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-11-15
 */
public class AutowiredBeanPostProcessorDemo {

    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AutowiredBeanPostProcessorDemo.class);

        // 加载XML资源，解析并生成 BeanDefinition
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        // 启动应用上下文
        applicationContext.refresh();

        // 依赖查找并创建 Bean
        AutowiredBeanPostProcessorDemo demo = applicationContext.getBean(AutowiredBeanPostProcessorDemo.class);

        System.out.println("user:" + demo.user);

        // 关闭应用上下文
        applicationContext.close();
    }

}
