package cn.author.fwwd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 设置允许跨域的路径
                registry.addMapping("/**")
                        // 设置允许跨域请求的域名
                        .allowedOrigins("http://localhost:8000","http://192.168.1.11:8000","http://192.168.1.11",
                                "http://10.6.10.109:8000","http://10.6.10.109")
                        // 是否允许证书
                        .allowCredentials(true)
                        // 设置允许的方法
                        .allowedMethods("GET", "POST", "DELETE", "PUT")
                        // 设置允许的header属性
                        .allowedHeaders("*")
                        // 跨域允许时间
                        .maxAge(3600);
            }
        };
    }
}
