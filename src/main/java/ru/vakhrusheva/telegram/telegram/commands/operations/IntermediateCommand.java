package ru.vakhrusheva.telegram.telegram.commands.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.vakhrusheva.telegram.Utils;
import ru.vakhrusheva.telegram.enums.OperationEnum;

public class IntermediateCommand extends OperationCommand {
  private Logger logger = LoggerFactory.getLogger(IntermediateCommand.class);

  public IntermediateCommand(String identifier, String description) {
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
        OperationEnum.INTERMEDIATE,
        this.getDescription(),
        this.getCommandIdentifier(),
        userName);
    logger.debug(
        String.format(
            "Пользователь %s. Завершено выполнение команды %s",
            userName, this.getCommandIdentifier()));
  }
}
