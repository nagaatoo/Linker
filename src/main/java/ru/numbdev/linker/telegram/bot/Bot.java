package ru.numbdev.linker.telegram.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.abilitybots.api.objects.*;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import ru.numbdev.linker.telegram.bot.replybuilder.*;
import ru.numbdev.linker.telegram.conf.BotProperty;
import ru.numbdev.linker.telegram.keyboards.UtilsKeyboardMarkup;

import static org.telegram.telegrambots.abilitybots.api.objects.Locality.ALL;
import static org.telegram.telegrambots.abilitybots.api.objects.Privacy.PUBLIC;

@Slf4j
@Component
public class Bot extends AbilityBot implements SpringLongPollingBot {

    private final BotProperty property;

    private final RegisterCVReplyProcess registerCVReplyBuilder;
    private final ListCVReplyBuilder listCVReplyBuilder;
    private final RecallCVReplyBuilder recallCVReplyBuilder;
    private final ListVacancyReplyBuilder listVacancyReplyBuilder;
    private final DeleteCVReplyBuilder deleteCVReplyBuilder;
    private final DeleteAllCVReplyBuilder deleteAllCVReplyBuilder;

    public Bot(
            TelegramClient telegramClient,
            BotProperty property,
            RegisterCVReplyProcess registerCVReplyBuilder,
            ListCVReplyBuilder listCVReplyBuilder,
            RecallCVReplyBuilder recallCVReplyBuilder,
            ListVacancyReplyBuilder listVacancyReplyBuilder,
            DeleteCVReplyBuilder deleteCVReplyBuilder,
            DeleteAllCVReplyBuilder deleteAllCVReplyBuilder
    ) {
        super(telegramClient, property.getName());
        this.property = property;
        this.registerCVReplyBuilder = registerCVReplyBuilder;
        this.listCVReplyBuilder = listCVReplyBuilder;
        this.recallCVReplyBuilder = recallCVReplyBuilder;
        this.listVacancyReplyBuilder = listVacancyReplyBuilder;
        this.deleteCVReplyBuilder = deleteCVReplyBuilder;
        this.deleteAllCVReplyBuilder = deleteAllCVReplyBuilder;
        onRegister();
    }

    @Override
    public long creatorId() {
        return property.getCreatedId();
    }

    @Override
    public String getBotToken() {
        return property.getToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }


//    @NotNull
//    private Predicate<Update> hasMessageWith(String msg) {
//        return upd -> upd.getMessage().getText().equalsIgnoreCase(msg);
//    }

//    public ReplyFlow markupFlow() {
//        return ReplyFlow.builder(db)
//                .action((b, upd)  -> silent.execute(
//                        EditMessageText
//                                .builder()
//                                .chatId(getChatId(upd))
//                                .messageId(upd.getCallbackQuery().getMessage().getMessageId())
//                                .text("Готово")
//                                .build()
//                ))
//                .onlyIf(hasMessageWith("update_msg_text"))
//                .build();
//    }


    public Ability unknownMessage() {
        return Ability
                .builder()
                .name(DEFAULT)
                .privacy(PUBLIC)
                .locality(ALL)
                .input(0)
                .action(ctx -> silent.send("Не понимаю - используйте клавиатуру ниже", ctx.chatId()))
                .build();
    }

    public Ability mainMenu() {
        return Ability
                .builder()
                .name("start")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.execute(
                        SendMessage
                                .builder()
                                .chatId(ctx.chatId())
                                .text("Чем мы вам можем помочь?")
                                .replyMarkup(UtilsKeyboardMarkup.mainMenu())
                                .build()
                ))
                .build();
    }

    public Reply registerCV() {
        return registerCVReplyBuilder.buildReply();
    }

    public Reply listCV() {
        return listCVReplyBuilder.buildReply();
    }

    public Reply recallCV() {
        return recallCVReplyBuilder.buildReply();
    }

    public Reply listVacancy() {
        return listVacancyReplyBuilder.buildReply();
    }

    public Reply deleteCV() {
        return deleteCVReplyBuilder.buildReply();
    }

    public Reply deleteAllCV() {
        return deleteAllCVReplyBuilder.buildReply();
    }
}
