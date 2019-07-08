package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class can be used by the Spring IoC (Inversion of Control)
 * container as a source of bean definitions
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Returns an object that should be registered as a bean in the Spring application context
     *
     * @return messageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Returns an object that should be registered as a bean in the Spring application context
     *
     * @return bean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    /**
     * The {@link Validator} interface for implementing the validation logic of the Spring MVC
     *
     * @return validator
     */
    @Override
    public Validator getValidator() {
        return validator();
    }

    /**
     * Creates simple automated controller pre-configured with status code and a view
     *
     * @param registry the Spring MVC {@link ViewControllerRegistry}
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/error/errorPage").setViewName("errorPage");
        registry.addViewController("/error").setViewName("errorPage");
        registry.addViewController("/contacts");
        registry.addViewController("/login");
        registry.addViewController("/customer/customerPage").setViewName("customer");
        registry.addViewController("/register/registerCustomer").setViewName("registerCustomer");
        registry.addViewController("/register/registerHandyman").setViewName("registerHandyman");
        registry.addViewController("/register/registerAdmin").setViewName("registerAdmin");
        registry.addViewController("/handyman/handymanPage").setViewName("handymanPage");
        registry.addViewController("/handyman/mySpecialties").setViewName("mySpecialties");
        registry.addViewController("/handyman/updateSpecialty").setViewName("updateSpecialty");
        registry.addViewController("/handyman/schedule").setViewName("schedule");
        registry.addViewController("/admin/deleteHandyman/{id}").setViewName("deleteHandyman");
        registry.addViewController("/customer/customerPage").setViewName("customerPage");
        registry.addViewController("/customer/findHandyman").setViewName("findHandyman");
    }
}
