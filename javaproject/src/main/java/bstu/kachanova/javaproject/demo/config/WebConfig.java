package bstu.kachanova.javaproject.demo.config;

import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

//Эти классы состоят в основном из @Bean аннотированных методов, которые определяют создание экземпляров,
//конфигурацию и логику инициализации для объектов, которые будут управляться контейнером Spring IoC

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //описание доступных бинов
    //зарегистрирует возвращаемое значение как bean-компонент
    @Bean
    public ClassLoaderTemplateResolver templateResolver(){
        var templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/WEB-INF/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }


    @Bean
    public ViewResolver viewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/reg").setViewName("registration");
        registry.addViewController("/loginin").setViewName("login2");
        registry.addViewController("/register").setViewName("registrationPage");
        registry.addViewController("/user/index").setViewName("usrindex");
        registry.addViewController("/admin/index").setViewName("admindex");
    }

}

