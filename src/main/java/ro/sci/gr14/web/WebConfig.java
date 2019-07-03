package ro.sci.gr14.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/errors").setViewName("errors");
        registry.addViewController("/registerCustomer");
        registry.addViewController("/registerHandyman");
        registry.addViewController("/admin/handymansList").setViewName("handymansList");
        registry.addViewController("/login");
        registry.addViewController("/handyman/handymanPage").setViewName("handymanPage");
        registry.addViewController("/handyman/mySpecialties").setViewName("mySpecialties");
        registry.addViewController("/handyman/updateSpecialty").setViewName("updateSpecialty");
        registry.addViewController("/handyman/schedule").setViewName("schedule");
        registry.addViewController("/customer/customerPage").setViewName("customerPage");
    }

}
