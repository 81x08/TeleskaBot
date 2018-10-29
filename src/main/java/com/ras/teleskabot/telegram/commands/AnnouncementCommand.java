package com.ras.teleskabot.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AnnouncementCommand extends AbstractCommand {

    @Override
    public SendMessage execute(final Update update) {
        return sendAnnouncementMessage(update.getMessage());
    }

    private SendMessage sendAnnouncementMessage(final Message message) {
        return new SendMessage()
                .enableMarkdown(true)
                .setReplyMarkup(getAnnouncementInlineKeyboardMarkup())
                .setText("Выберите месяц")
                .setChatId(message.getChatId());
    }

    private InlineKeyboardMarkup getAnnouncementInlineKeyboardMarkup() {
        final Locale locale = new Locale("ru");

        final int numberMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;

        final InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        final String prevMonth = Month.of(numberMonth - 1).getDisplayName(TextStyle.FULL_STANDALONE, locale);
        final List<InlineKeyboardButton> inlineKeyboardButtonsRowFirst = new ArrayList<>();
        inlineKeyboardButtonsRowFirst.add(
                new InlineKeyboardButton()
                        .setText(prevMonth)
                        .setCallbackData("announcement_" + prevMonth)
        );

        final String currentMonth = Month.of(numberMonth).getDisplayName(TextStyle.FULL_STANDALONE, locale);
        final List<InlineKeyboardButton> inlineKeyboardButtonsRowSecond = new ArrayList<>();
        inlineKeyboardButtonsRowSecond.add(
                new InlineKeyboardButton()
                        .setText(currentMonth)
                        .setCallbackData("announcement_" + currentMonth)
        );

        final String nextMonth = Month.of(numberMonth + 1).getDisplayName(TextStyle.FULL_STANDALONE, locale);
        final List<InlineKeyboardButton> inlineKeyBoardButtonsRowThird = new ArrayList<>();
        inlineKeyBoardButtonsRowThird.add(
                new InlineKeyboardButton()
                        .setText(nextMonth)
                        .setCallbackData("announcement_" + nextMonth)
        );

        final List<List<InlineKeyboardButton>> inlineKeyboardButtonList = new ArrayList<>();

        inlineKeyboardButtonList.add(inlineKeyboardButtonsRowFirst);
        inlineKeyboardButtonList.add(inlineKeyboardButtonsRowSecond);
        inlineKeyboardButtonList.add(inlineKeyBoardButtonsRowThird);

        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtonList);

        return inlineKeyboardMarkup;
    }

}