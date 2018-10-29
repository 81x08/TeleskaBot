package com.ras.teleskabot.telegram.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DispatcherCommand {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherCommand.class);

    public SendMessage execute(final Update update) {
        final Message message = update.getMessage();

        return sendHelpMessage(message);
    }

    private SendMessage sendHelpMessage(final Message message) {
        return new SendMessage()
                .setChatId(message.getChatId())
                .enableMarkdown(true)
                .setText("Чтобы воспользоваться ботом выберите команду из ниже предложенного меню.");
    }

}