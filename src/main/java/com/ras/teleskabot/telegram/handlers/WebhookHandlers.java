package com.ras.teleskabot.telegram.handlers;

import com.ras.teleskabot.telegram.commands.DispatcherCommand;
import com.ras.teleskabot.telegram.settings.Config;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WebhookHandlers extends TelegramWebhookBot {

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return new DispatcherCommand().execute(update);
        }

        return null;
    }

    @Override
    public String getBotUsername() {
        return Config.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Config.BOT_TOKEN;
    }

    @Override
    public String getBotPath() {
        return Config.WEBHOOK_NAME;
    }

}