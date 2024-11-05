package ru.numbdev.linker.telegram.bot.replybuilder;

import org.telegram.telegrambots.abilitybots.api.db.DBContext;
import org.telegram.telegrambots.abilitybots.api.objects.Flag;
import org.telegram.telegrambots.abilitybots.api.objects.Reply;
import org.telegram.telegrambots.abilitybots.api.objects.ReplyFlow;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.numbdev.linker.telegram.dto.Commands;

import static org.telegram.telegrambots.abilitybots.api.util.AbilityUtils.getChatId;

public class RegisterCVReplyProcess extends AbstractReplyBuilder {

    private RegisterCVReplyProcess(TelegramClient client, DBContext db) {
        super(client, db);
    }

    public Reply buildReply() {
        Reply saidLeft = Reply.of((b, upd) -> silent.send("Sir, I have gone left.", getChatId(upd)),
                hasCallbackName("go left or else"));

        ReplyFlow leftflow = ReplyFlow.builder(db)
                .action((b, upd)  -> silent.send("I don't know how to go left.", getChatId(upd)))
                .onlyIf(hasCallbackName("left"))
                .next(saidLeft).build();

        Reply saidRight = Reply.of((b, upd)  -> silent.send("Sir, I have gone right.", getChatId(upd)),
                hasCallbackName("right"));

        return ReplyFlow.builder(db)
                .action((b, upd)  -> silent.send("Command me to go left or right!", getChatId(upd)))
                .onlyIf(hasTestInMessage())
                .next(leftflow)
                .next(saidRight)
                .build();
    }

    @Override
    public Flag replyType() {
        return Flag.MESSAGE;
    }

    @Override
    public String targetCommand() {
        return Commands.CV_TAKE;
    }
}
