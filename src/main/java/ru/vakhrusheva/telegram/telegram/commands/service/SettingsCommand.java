package ru.vakhrusheva.telegram.telegram.commands.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.vakhrusheva.telegram.telegram.nonCommand.Settings;
import ru.vakhrusheva.telegram.Utils;
import ru.vakhrusheva.telegram.telegram.Bot;

public class SettingsCommand extends ServiceCommand {
  private Logger logger = LoggerFactory.getLogger(SettingsCommand.class);

  public SettingsCommand(String identifier, String description) {
    super(identifier, description);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    String userName = Utils.getUserName(user);
    logger.debug(
        String.format(
            "Пользователь %s. Начато выполнение команды %s",
            userName, this.getCommandIdentifier()));
    Long chatId = chat.getId();
    Settings settings = Bot.getUserSettings(chatId);
    sendAnswer(
        absSender,
        chatId,
        this.getCommandIdentifier(),
        userName,
        String.format(
            "*Текущие настройки*\n"
                + "- заданный уровень - *%s*\n"
                + "- количество слов - *%s*\n"
                + "Если Вы хотите изменить эти параметры, введите через пробел или запятую 2 числа - "
                + "уровень от 1 до 3 и количество слов в файле (не более 50)\n\n"
                + "\uD83D\uDC49 Например, 3,15 или 1 17",
            settings.getLevel(), settings.getNumberOfWords()));
    logger.debug(
        String.format(
            "Пользователь %s. Завершено выполнение команды %s",
            userName, this.getCommandIdentifier()));
  }
}
