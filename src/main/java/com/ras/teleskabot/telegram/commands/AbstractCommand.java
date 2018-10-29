package com.ras.teleskabot.telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractCommand {

    public abstract SendMessage execute(final Update update);

}