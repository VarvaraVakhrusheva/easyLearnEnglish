package ru.vakhrusheva.telegram.telegram.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.vakhrusheva.telegram.Utils;
import ru.vakhrusheva.telegram.telegram.nonCommand.NonCommandUtil;

import java.util.Arrays;
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
        List<String> buttonsAndCallback = Arrays.asList("Времена", "Синтаксис", "Глаголы", "Части речи");
        return NonCommandUtil.getSendMessage(chatId, "Выберите тему, которая вас интересует:",
                buttonsAndCallback, buttonsAndCallback);
    }
}
