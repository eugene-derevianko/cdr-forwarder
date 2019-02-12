package com.symulakr.telegram.bot.message;

import com.symulakr.telegram.bot.model.ChatOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class MessageSender {

    private final RestTemplate restTemplate;

    public void send(ChatOptions chatOptions, String message) {
        restTemplate.getForObject("https://api.telegram.org/bot{token}/sendMessage?chat_id={id}&text={text}&parse_mode=Markdown",
                String.class, chatOptions.getBotToken(), chatOptions.getChatId(), message);
    }

}
