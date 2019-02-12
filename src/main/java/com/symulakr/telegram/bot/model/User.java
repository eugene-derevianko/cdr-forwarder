package com.symulakr.telegram.bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @NotNull
    private String id;
    private String chatId;
    private String botToken;

}
