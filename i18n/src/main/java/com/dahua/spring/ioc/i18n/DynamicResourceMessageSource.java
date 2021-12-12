package com.dahua.spring.ioc.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * 动态（更新）资源 {@link MessageSource} 实现
 *
 * 实现步骤：
 *
 * 1. 定位资源位置 (Properties 文件)
 * 2. 初始化Properties对象
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 * 4.
 *
 * @author <a href="mailto:wangfan1996love@gmail">wf</a>
 * @since 2021-12-06*
 * @see MessageSource
 * @see AbstractMessageSource
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private final String resourcePath = "classpath:/META-INF/message.properties";

    private ResourceLoader resourceLoader;

    private final Properties messageProperties;

    public DynamicResourceMessageSource() {
        messageProperties = loadMessageProperties();
    }

    private Properties loadMessageProperties() {
        Resource resource = getResourceLoader().getResource(resourcePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        Properties properties = new Properties();
        try (Reader reader = encodedResource.getReader()){
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern,locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    public static void main(String[] args) {
        DynamicResourceMessageSource dynamicResourceMessageSource = new DynamicResourceMessageSource();
        String result = dynamicResourceMessageSource.getMessage("name", new Object[]{}, Locale.CHINA);
        System.out.println(result);
    }
}
