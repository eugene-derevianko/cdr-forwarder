package com.symulakr.telegram.bot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {

    @Valid
    private List<User> users;

}
