package com.ras.teleskabot.telegram.commands;

import com.ras.teleskabot.telegram.utils.keyboard.KeyboardUtils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand extends AbstractCommand {

    @Override
    public SendMessage execute(final Update update) {
        return sendStartMessage(update.getMessage());
    }

    private SendMessage sendStartMessage(final Message message) {
        final long chatId = message.getChatId();

        return new SendMessage()
                .enableMarkdown(true)
                .setReplyMarkup(KeyboardUtils.getMainReplyKeyboardMarkup(message))
                .setText("<b>Приветственное сообщение</b>")
                .setParseMode("HTML")
                .setChatId(chatId);
    }

}