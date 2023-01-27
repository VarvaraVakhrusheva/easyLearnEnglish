package ru.vakhrusheva.telegram.telegram.commands.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.vakhrusheva.telegram.enums.OperationEnum;

public class ResendWordsTask implements Runnable {
  private Logger logger = LoggerFactory.getLogger(ResendWordsTask.class);
  private AbsSender absSender;
  private SendDocument document;
  private String userName;
  private Long chatId;
  private OperationEnum level;

  public ResendWordsTask(OperationEnum level, SendDocument document, AbsSender absSender, String userName, Long chatId) {
    this.document = document;
    this.absSender = absSender;
    this.userName = userName;
    this.chatId = chatId;
    this.level = level;
  }

  @Override
  public void run() {
    if (level.equals(OperationEnum.BASIC)) {
      BasicCommand command = new BasicCommand("basic", "Базовый уровень");
      command.setDocument(document);
      User user = new User();
      user.setUserName(userName);
      Chat chat = new Chat();
      chat.setId(chatId);
      String[] strings = new String[1];
      strings[0] = "repeat";
      command.execute(absSender, user, chat, strings);
    } else if (level.equals(OperationEnum.INTERMEDIATE)) {
      IntermediateCommand command = new IntermediateCommand("intermediate", "Средний уровень");
      command.setDocument(document);
      User user = new User();
      user.setUserName(userName);
      Chat chat = new Chat();
      chat.setId(chatId);
      String[] strings = new String[1];
      strings[0] = "repeat";
      command.execute(absSender, user, chat, strings);
    } else {
      AdvancedCommand command = new AdvancedCommand("advanced", "Продвинутый уровень");
      command.setDocument(document);
      User user = new User();
      user.setUserName(userName);
      Chat chat = new Chat();
      chat.setId(chatId);
      String[] strings = new String[1];
      strings[0] = "repeat";
      command.execute(absSender, user, chat, strings);
    }
  }
}
