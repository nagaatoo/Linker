package ru.numbdev.linker.telegram.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TelegramConf {

    @Bean
    public TelegramClient telegramClient(BotProperty property) {
        return new OkHttpTelegramClient(property.getToken());
    }
}
