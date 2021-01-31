package cn.author.fwwd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "author")
@Data
public class PropertiesConfig {
    private Long serverId;
    private String fileServerIp;
}
