package com.symulakr.telegram.bot.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChatOptions {

    String botToken;
    String chatId;

}
