package ru.vakhrusheva.telegram.telegram.commands.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.vakhrusheva.telegram.Utils;

public class HelpCommand extends ServiceCommand {
  private Logger logger = LoggerFactory.getLogger(HelpCommand.class);

  public HelpCommand(String identifier, String description) {
    super(identifier, description);
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    String userName = Utils.getUserName(user);
    logger.debug(
        String.format(
            "Пользователь %s. Начато выполнение команды %s",
            userName, this.getCommandIdentifier()));
    sendAnswer(
        absSender,
        chat.getId(),
        this.getCommandIdentifier(),
        userName,
        "Я бот, который поможет вам проверить свои знания английских слов\n\n"
            + "Я сгенерирую word-файл со словами, чтобы Вам не пришлось искать или придумывать их. "
            + "Напечатайте его и попробуйте переводить по одной страничке в день\n\n"
            + "❗*Список команд*\n"
            + "/basic - слова базового уровня (A1, A1 / Basic, Elementary)\n"
            + "/intermediate - слова среднего уровня (B1, B2 / Intermediate, Upper Intermediate)\n"
            + "/advanced - слова продвинутого уровня (C1, C2 / Advanced, Proficient)\n"
            + "/grammar - выбрать тему грамматики и получить файлы с правилами и заданиями по ней\n"
            + "/settings - посмотреть текущие настройки\n"
            + "/help - помощь\n\n"
            + "По умолчанию я сформирую страницу заданий с использованием *15* слов. Если Вы "
            + "хотите изменить эти параметры, введите через пробел или запятую 2 числа - уровень английского "
            + "для использования в заданиях (1 - начальный, 2 - средний, 3 - продвинутый) и количество слов "
            + "в файле (не более 50)\n"
            + "\uD83D\uDC49 Например, 3,15 или 1 17\n\n"
            + "Желаю удачи\uD83D\uDE42");
    logger.debug(
        String.format(
            "Пользователь %s. Завершено выполнение команды %s",
            userName, this.getCommandIdentifier()));
  }
}
