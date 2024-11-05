package ru.numbdev.linker.telegram.bot.replybuilder;

import org.telegram.telegrambots.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.telegrambots.abilitybots.api.db.DBContext;
import org.telegram.telegrambots.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public abstract class AbstractReplyBuilder implements ReplyBuilder {

    protected final SilentSender silent;
    protected final DBContext db;

    protected AbstractReplyBuilder(TelegramClient client, DBContext db) {
        this.silent = new SilentSender(client);
        this.db = db;
    }

    @Override
    public Reply buildReply() {
        return Reply.of(
                buildAction(),
                replyType(),
                hasTestInMessage()
        );
    }

    @Override
    public BiConsumer<BaseAbilityBot, Update> buildAction() {
        return (bot, udp) -> {};
    }

    protected Predicate<Update> hasTestInMessage() {
        return udp -> udp.getMessage().getText().contains(targetCommand());
    }

    protected Predicate<Update> hasCallbackName(String msg) {
        return upd -> upd.getCallbackQuery().getData().equals(msg);
    }

}
