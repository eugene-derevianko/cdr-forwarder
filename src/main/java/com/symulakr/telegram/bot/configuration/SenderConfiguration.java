package com.symulakr.telegram.bot.configuration;

import com.symulakr.telegram.bot.message.MessageSender;
import com.symulakr.telegram.bot.message.SenderPool;
import com.symulakr.telegram.bot.model.ChatOptions;
import com.symulakr.telegram.bot.model.User;
import com.symulakr.telegram.bot.model.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

@Configuration
public class SenderConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MessageSender messageSender(RestTemplate restTemplate) {
        return new MessageSender(restTemplate);
    }

    @Bean
    public SenderPool senderPool(Users users,
                                 ChatOptions defaultChatOptions,
                                 MessageSender messageSender) {
        return new SenderPool(chatOptions(users, defaultChatOptions), defaultChatOptions, messageSender);
    }

    private Map<String, ChatOptions> chatOptions(Users users,
                                                 ChatOptions defaultChatOptions) {
        return Optional.ofNullable(users.getUsers())
                .map(list -> list.stream()
                        .collect(toMap(User::getId, user -> ChatOptions.builder()
                                .botToken(Optional.ofNullable(user.getBotToken()).orElse(defaultChatOptions.getBotToken()))
                                .chatId(Optional.ofNullable(user.getChatId()).orElse(defaultChatOptions.getChatId()))
                                .build())))
                .orElseGet(HashMap::new);
    }

}
