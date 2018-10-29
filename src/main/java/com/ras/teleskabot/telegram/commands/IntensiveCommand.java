package com.ras.teleskabot.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class IntensiveCommand extends AbstractCommand {

    @Override
    public SendMessage execute(final Update update) {
        return sendIntensiveMessage(update.getMessage());
    }

    private SendMessage sendIntensiveMessage(final Message message) {
        return new SendMessage()
                .enableMarkdown(true)
                .setReplyMarkup(getIntensiveInlineKeyboardMarkup())
                .setText("Выберите интенсив")
                .setChatId(message.getChatId());
    }

    private InlineKeyboardMarkup getIntensiveInlineKeyboardMarkup() {
        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        final List<InlineKeyboardButton> inlineKeyboardButtonsRowFirst = new ArrayList<>();
        inlineKeyboardButtonsRowFirst.add(
                new InlineKeyboardButton()
                        .setText("Летний \"Пробуждение\"")
                        .setCallbackData("intensive_summer")
        );

        final List<InlineKeyboardButton> inlineKeyboardButtonsRowSecond = new ArrayList<>();
        inlineKeyboardButtonsRowSecond.add(
                new InlineKeyboardButton()
                        .setText("Зимний \"Погружение\"")
                        .setCallbackData("intensive_winter")
        );

        final List<List<InlineKeyboardButton>> inlineKeyboardButtonList = new ArrayList<>();

        inlineKeyboardButtonList.add(inlineKeyboardButtonsRowFirst);
        inlineKeyboardButtonList.add(inlineKeyboardButtonsRowSecond);

        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtonList);

        return inlineKeyboardMarkup;
    }

}
