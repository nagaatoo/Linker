package ru.numbdev.linker.telegram.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.numbdev.linker.telegram.dto.Commands;

import java.util.List;

public class UtilsKeyboardMarkup {

    public static ReplyKeyboard mainMenu() {
        return ReplyKeyboardMarkup
                .builder()
                .keyboard(
                        List.of(
                                new KeyboardRow(
                                        List.of(
                                                KeyboardButton
                                                        .builder()
                                                        .text(Commands.CV_TAKE)
                                                        .build(),
                                                KeyboardButton
                                                        .builder()
                                                        .text(Commands.CV_LIST)
                                                        .build(),
                                                KeyboardButton
                                                        .builder()
                                                        .text(Commands.CV_RECALL)
                                                        .build()
                                        )
                                ),
                                new KeyboardRow(
                                        List.of(
                                                KeyboardButton
                                                        .builder()
                                                        .text(Commands.VACANCY_LIST)
                                                        .build(),
                                                KeyboardButton
                                                        .builder()
                                                        .text(Commands.CV_DELETE)
                                                        .build(),
                                                KeyboardButton
                                                        .builder()
                                                        .text(Commands.CV_DELETE_ALL)
                                                        .build()
                                        )
                                )
                        )
                )
                .build();
    }
}
