package com.symulakr.telegram.bot.configuration;

import com.symulakr.telegram.bot.model.ChatOptions;
import com.symulakr.telegram.bot.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DictionaryContext {

    @Value("${telegram.bot.token}")
    private String defaultBotToken;
    @Value("${telegram.chat.id}")
    private String defaultChatId;

    @Bean
    @ConfigurationProperties("configuration")
    public Users users() {
        return new Users();
    }

    @Bean
    public ChatOptions defaultChatOptions() {
        return ChatOptions.builder()
                .botToken(defaultBotToken)
                .chatId(defaultChatId)
                .build();
    }

}
