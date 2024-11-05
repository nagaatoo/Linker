package ru.numbdev.linker.telegram.bot.replybuilder;

import org.telegram.telegrambots.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.telegrambots.abilitybots.api.objects.Flag;
import org.telegram.telegrambots.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.BiConsumer;

public interface ReplyBuilder {
    BiConsumer<BaseAbilityBot, Update> buildAction();
    Flag replyType();
    String targetCommand();
    Reply buildReply();
}
