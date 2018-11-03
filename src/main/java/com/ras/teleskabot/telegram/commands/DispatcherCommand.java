package com.ras.teleskabot.telegram.commands;

import com.ras.teleskabot.telegram.settings.Commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DispatcherCommand {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherCommand.class);

    public SendMessage execute(final Update update) {
        final Message message = update.getMessage();

        switch (message.getText()) {
            case Commands.startCommand: {
                return new StartCommand().execute(update);
            }
            case Commands.articlesCommand: {
                break;
            }
            case Commands.announcementCommand: {
                return new AnnouncementCommand().execute(update);
            }
            case Commands.intensiveCommand: {
                return new IntensiveCommand().execute(update);
            }
            case Commands.informationCitiesCommand: {
                break;
            }
            case Commands.workWithBotCommand: {
                break;
            }
        }

        return sendHelpMessage(message);
    }

    private SendMessage sendHelpMessage(final Message message) {
        return new SendMessage()
                .enableMarkdown(true)
                .setText("Чтобы воспользоваться ботом выберите команду из ниже предложенного меню.")
                .setChatId(message.getChatId());
    }

}