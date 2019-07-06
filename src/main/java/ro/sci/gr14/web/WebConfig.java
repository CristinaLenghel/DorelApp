package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/errors").setViewName("errors");
        registry.addViewController("/login");
        registry.addViewController("/register/registerCustomer").setViewName("registerCustomer");
        registry.addViewController("/register/registerHandyman").setViewName("registerHandyman");
        registry.addViewController("/register/registerAdmin").setViewName("registerAdmin");
        registry.addViewController("/handyman/handymanPage").setViewName("handymanPage");
        registry.addViewController("/handyman/mySpecialties").setViewName("mySpecialties");
        registry.addViewController("/handyman/updateSpecialty").setViewName("updateSpecialty");
        registry.addViewController("/handyman/schedule").setViewName("schedule");
        registry.addViewController("/admin/deleteHandyman/{id}").setViewName("deleteHandyman");
        registry.addViewController("/customer/customerPage").setViewName("customerPage");
    }
}
