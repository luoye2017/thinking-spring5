package com.wangfan.spring.bean.definition;

import com.wangfan.spring.bean.factory.DefaultUserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Spring Bean GC 示例
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-09
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitializationDemo.class);
        // 初始化容器
        context.refresh();
        DefaultUserFactory bean = context.getBean(DefaultUserFactory.class);
        // 关闭容器
        context.close();

        bean = null;
        System.gc();
        Thread.sleep(5000);
    }
}
