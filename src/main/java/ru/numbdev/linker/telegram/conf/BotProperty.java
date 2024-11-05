package ru.numbdev.linker.telegram.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "bot")
public class BotProperty {
    private Long createdId;
    private String name;
    private String token;
}
