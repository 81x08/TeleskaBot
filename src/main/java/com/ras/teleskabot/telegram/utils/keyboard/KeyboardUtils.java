package com.ras.teleskabot.telegram.utils.keyboard;

import com.ras.teleskabot.telegram.database.entity.UserEntity;
import com.ras.teleskabot.telegram.database.service.UserService;
import com.ras.teleskabot.telegram.settings.Commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardUtils {

    public static ReplyKeyboardMarkup getMainReplyKeyboardMarkup(final Message message) {
        final long userId = message.getFrom().getId();
        final long chatId = message.getChatId();

        boolean access;

        final List<UserEntity> userList = UserService.getInstance().readAll();
        final UserEntity user = userList.stream().filter(item -> item.getUserId() == userId).findFirst().orElse(null);

        if (user == null) {
            access = userList.isEmpty();

            UserService.getInstance().create(
                    new UserEntity(
                            message.getFrom().getId(),
                            chatId,
                            access
                    )
            );
        } else {
            access = user.isAccess();
        }

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        final List<KeyboardRow> keyboardRowList = new ArrayList<>();

        final KeyboardRow keyboardRowFirst = new KeyboardRow();
        keyboardRowFirst.add(Commands.articlesCommand);
        keyboardRowFirst.add(Commands.announcementCommand);
        keyboardRowFirst.add(Commands.intensiveCommand);

        final KeyboardRow keyboardRowSecond = new KeyboardRow();
        keyboardRowSecond.add(Commands.informationCitiesCommand);

        keyboardRowList.add(keyboardRowFirst);
        keyboardRowList.add(keyboardRowSecond);

        if (access) {
            final KeyboardRow keyboardRowThird = new KeyboardRow();
            keyboardRowThird.add(Commands.workWithBotCommand);

            keyboardRowList.add(keyboardRowThird);
        }

        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        return replyKeyboardMarkup;
    }

}