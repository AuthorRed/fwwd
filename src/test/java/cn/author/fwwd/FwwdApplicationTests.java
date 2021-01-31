package cn.author.fwwd;


import cn.author.fwwd.config.PropertiesConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FwwdApplicationTests {
    @Value("${spring.datasource.name}")
    private   static String name ;
    @Autowired
    private PropertiesConfig config;
    @Test
    void contextLoads() {
        System.out.println(config.getServerId());
    }

}
