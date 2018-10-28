package com.ras.teleskabot.telegram;

import com.ras.teleskabot.telegram.settings.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Telegram {

    private static final Logger logger = LoggerFactory.getLogger(Telegram.class);

    static {
        logger.info("-========== Start program ==========-");

        ApiContextInitializer.init();
    }

    public void start() {
        createTelegramBotsApi();
    }

    private void createTelegramBotsApi() {
        logger.info("Create Telegram bot");

        TelegramBotsApi telegramBotsApi;

        try {
            telegramBotsApi = createSelfSignedTelegramBotsApi();
        } catch (TelegramApiException e) {
            logger.error("Failed create method - Webhook (" + e.getMessage() + ")");
        }
    }

    private TelegramBotsApi createSelfSignedTelegramBotsApi() throws TelegramApiException  {
        logger.info("Running the event handler method - Webhook");

        return new TelegramBotsApi(Config.pathToCertificateStore, Config.certificateStorePassword, Config.EXTERNAL_WEBHOOK_URL, Config.INTERNAL_WEBHOOK_URL, Config.pathToCertificatePublicKey);
    }

}