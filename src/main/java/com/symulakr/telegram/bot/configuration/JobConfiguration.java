package com.symulakr.telegram.bot.configuration;

import com.symulakr.telegram.bot.message.CdrTranslator;
import com.symulakr.telegram.bot.message.SenderPool;
import com.symulakr.telegram.bot.model.Cdr;
import com.symulakr.telegram.bot.repository.CdrRepository;
import com.symulakr.telegram.bot.task.EveryTenSecondsJob;
import com.symulakr.telegram.bot.task.LastCdrForwarder;
import com.symulakr.telegram.bot.task.Schedule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;

@Configuration
public class JobConfiguration {

    @Bean
    public Function<Cdr, String> cdrTranslator() {
        return new CdrTranslator();
    }

    @Bean
    public EveryTenSecondsJob lastCdrForwarder(CdrRepository cdrRepository,
                                               SenderPool senderPool,
                                               Function<Cdr, String> cdrTranslator) {
        return new LastCdrForwarder(cdrRepository, senderPool, cdrTranslator);
    }

    @Bean
    public Schedule schedule(List<EveryTenSecondsJob> every10Seconds) {
        return new Schedule(every10Seconds);
    }

}
