package com.symulakr.telegram.bot.task;

import com.symulakr.telegram.bot.message.MarkdownMessageBuilder;
import com.symulakr.telegram.bot.message.SenderPool;
import com.symulakr.telegram.bot.model.Cdr;
import com.symulakr.telegram.bot.model.ChatOptions;
import com.symulakr.telegram.bot.repository.CdrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.NestedRuntimeException;

import java.util.function.Function;

@RequiredArgsConstructor
public class LastCdrForwarder implements EveryTenSecondsJob {

    private final CdrRepository repository;
    private final SenderPool senderPool;
    private final Function<Cdr, String> translator;

    private int lastId = 0;
    private boolean connected = false;

    @Override
    public void doIt() {
        if (connected) {
            forwardLastCdr();
        } else {
            waitConnection();
        }
    }

    private void forwardLastCdr() {
        try {
            Cdr last = repository.findFirstByOrderByIdDesc();
            if (lastId < last.getId()) {
                senderPool.send(getChatOptions(last), translator.apply(last));
                lastId = last.getId();
            }
        } catch (NestedRuntimeException e) {
            sendMessage("*Can't connect to database.*\n\n"+e.getMessage());
            waitConnection();
        }
    }

    private ChatOptions getChatOptions(Cdr last) {
        if (senderPool.isSubscriber(last.getSrc())) {
            return senderPool.getChatOptions(last.getSrc());
        }
        return senderPool.getChatOptions(last.getDst());
    }

    private void waitConnection() {
        try {
            sendMessage(MarkdownMessageBuilder.builder()
                    .italic("Connected to database.")
                    .lineFeed()
                    .append("Found ")
                    .bold(repository.count())
                    .append(" records.")
                    .build());
            connected = true;
            Cdr last = repository.findFirstByOrderByIdDesc();
            senderPool.send(translator.apply(last));
            lastId = last.getId();
        } catch (NestedRuntimeException e) {
            if (connected) {
                sendMessage(MarkdownMessageBuilder.builder()
                        .bold("Can't connect to database.")
                        .lineFeed()
                        .append(e)
                        .build());
            }
            connected = false;
        }
    }

    private void sendMessage(String message) {
        senderPool.send(message);
    }
}
