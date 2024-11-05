package ru.numbdev.linker.telegram.bot.replybuilder;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.telegrambots.abilitybots.api.db.DBContext;
import org.telegram.telegrambots.abilitybots.api.objects.Flag;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.numbdev.linker.telegram.dto.Commands;

import java.util.function.BiConsumer;

import static org.telegram.telegrambots.abilitybots.api.util.AbilityUtils.getChatId;

@Component
public class DeleteAllCVReplyBuilder extends AbstractReplyBuilder {

    protected DeleteAllCVReplyBuilder(TelegramClient client, DBContext db) {
        super(client, db);
    }

    @Override
    public BiConsumer<BaseAbilityBot, Update> buildAction() {
        return (bot, udp) -> silent.send("Все удалили", getChatId(udp));
    }

    @Override
    public Flag replyType() {
        return Flag.MESSAGE;
    }

    @Override
    public String targetCommand() {
        return Commands.CV_DELETE_ALL;
    }
}
