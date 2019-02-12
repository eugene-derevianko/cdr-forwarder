package com.symulakr.telegram.bot.message;

import com.symulakr.telegram.bot.model.ChatOptions;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class SenderPool {

    private final Map<String, ChatOptions> chatOptions;
    private final ChatOptions defaultChatOptions;
    private final MessageSender messageSender;

    public void send(ChatOptions chatOptions, String message) {
        messageSender.send(chatOptions, message);
    }

    public void send(String message) {
        messageSender.send(defaultChatOptions, message);
    }

    public boolean isSubscriber(String userId) {
        return chatOptions.containsKey(userId);
    }

    public ChatOptions getChatOptions(String userId) {
        return chatOptions.getOrDefault(userId, defaultChatOptions);
    }

}
