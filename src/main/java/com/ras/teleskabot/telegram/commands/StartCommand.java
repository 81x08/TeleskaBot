package com.ras.teleskabot.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class StartCommand extends AbstractCommand {

    private static final String ANNOUNCEMENTS = "Анонсы";
    private static final String INTENSIVE = "Интенсивы";
    private static final String INFORMATION_CITIES = "Информация по городам";
    private static final String WORK_WITH_BOT = "Работа с ботом";

    @Override
    public SendMessage execute(Update update) {
        return sendStartMessage(update.getMessage());
    }

    private SendMessage sendStartMessage(Message message){
        return new SendMessage()
                .enableMarkdown(true)
                .setReplyMarkup(getMainReplyKeyboardMarkup())
                .setText("<b>Приветственное сообщение</b>")
                .setParseMode("HTML")
                .setChatId(message.getChatId());
    }

    private ReplyKeyboardMarkup getMainReplyKeyboardMarkup() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        final List<KeyboardRow> keyboardRowList = new ArrayList<>();

        final KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(ANNOUNCEMENTS);
        keyboardFirstRow.add(INTENSIVE);

        final KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(INFORMATION_CITIES);

        final KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(WORK_WITH_BOT);

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardSecondRow);
        keyboardRowList.add(keyboardThirdRow);

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

}