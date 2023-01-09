package ru.vakhrusheva.telegram.telegram.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.vakhrusheva.telegram.Utils;

import java.util.ArrayList;
import java.util.List;

public class GrammarCommand extends BotCommand {
    private Logger logger = LoggerFactory.getLogger(GrammarCommand.class);

    public GrammarCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);
        logger.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
                this.getCommandIdentifier()));
        try {
            absSender.execute(menu(chat.getId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        logger.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
                this.getCommandIdentifier()));
    }

    public SendMessage menu(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Времена");
        inlineKeyboardButton1.setCallbackData("Времена");
        inlineKeyboardButton2.setText("Синтаксис");
        inlineKeyboardButton2.setCallbackData("Синтаксис");
        inlineKeyboardButton3.setText("Глаголы");
        inlineKeyboardButton3.setCallbackData("Глаголы");
        inlineKeyboardButton4.setText("Части речи");
        inlineKeyboardButton4.setCallbackData("Части речи");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        inlineKeyboardMarkup.setKeyboard(rowList);
        SendMessage answer = new SendMessage();
        answer.setChatId(String.valueOf(chatId));
        answer.setText("Выберите тему, которая вас интересует:");
        answer.setReplyMarkup(inlineKeyboardMarkup);
        return answer;
    }
}
