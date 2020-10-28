package shop.config;




import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("org.springframework.security.samples.mvc")
public class MyMvcConfig implements WebMvcConfigurer {
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("admin/AdminLogin");
        registry.addViewController("/User").setViewName("user/userLogin");
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
	
	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry){
		 
        
        //addResourceHandler是指定的虚拟路径，addResourceLocations是自己的物理路径，
        registry.addResourceHandler("/file/**").addResourceLocations("file:C:/");
        registry.addResourceHandler("/Ramen_pc/**").addResourceLocations("file:D:/Ramen_pc/"); 
        registry.addResourceHandler("/Index/**").addResourceLocations("file:D:/IndexRamen/"); 
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

	
    
   
    

}
