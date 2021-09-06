package com.wangfan.spring.bean.definition;

import com.wangfan.ioc.overview.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Alias Bean Name (Bean 别名 示例)
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-09-06
 */
public class AliasBeanNameDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        User user = context.getBean("user", User.class);
        User aliasUser = context.getBean("alias-user", User.class);
        System.out.println("user Bean 和 user 的别名 alias-user bean 是否是同一个示例：" + (user == aliasUser));

    }
}
