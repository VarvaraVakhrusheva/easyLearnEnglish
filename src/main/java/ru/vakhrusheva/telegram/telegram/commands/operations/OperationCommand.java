package ru.vakhrusheva.telegram.telegram.commands.operations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.vakhrusheva.telegram.telegram.Bot;
import ru.vakhrusheva.telegram.task.WordService;
import ru.vakhrusheva.telegram.enums.OperationEnum;
import ru.vakhrusheva.telegram.fileProcessor.WordFileProcessorImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

abstract class OperationCommand extends BotCommand {
  private Logger logger = LoggerFactory.getLogger(OperationCommand.class);
  private WordService service;

  OperationCommand(String identifier, String description) {
    super(identifier, description);
    this.service = new WordService(new WordFileProcessorImpl());
  }

  void sendAnswer(
      AbsSender absSender,
      Long chatId,
      OperationEnum level,
      String description,
      String commandName,
      String userName,
      String[] strings) {
    try {
      SendDocument document = createDocument(chatId, level, description);
      if (strings.length == 0) {
        absSender.execute(document);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(
            new ResendWordsTask(level, document, absSender, userName, chatId), 9, TimeUnit.HOURS);
        scheduledExecutorService.schedule(
            new ResendWordsTask(level, document, absSender, userName, chatId), 24, TimeUnit.HOURS);
        scheduledExecutorService.schedule(
            new ResendWordsTask(level, document, absSender, userName, chatId), 7, TimeUnit.DAYS);
        scheduledExecutorService.schedule(
            new ResendWordsTask(level, document, absSender, userName, chatId), 12, TimeUnit.DAYS);
        scheduledExecutorService.shutdown();
      } else {
        absSender.execute(document);
      }
    } catch (IOException | RuntimeException e) {
      logger.error(
          String.format(
              "Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
      sendError(absSender, chatId, commandName, userName);
      e.printStackTrace();
    } catch (TelegramApiException e) {
      logger.error(
          String.format(
              "Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
      e.printStackTrace();
    }
  }

  private SendDocument createDocument(Long chatId, OperationEnum level, String fileName)
      throws IOException {
    FileInputStream stream = service.getFile(level, Bot.getUserSettings(chatId));
    SendDocument document = new SendDocument();
    document.setChatId(chatId.toString());
    document.setDocument(new InputFile(stream, String.format("%s.docx", fileName)));
    return document;
  }

  private void sendError(AbsSender absSender, Long chatId, String commandName, String userName) {
    try {
      absSender.execute(new SendMessage(chatId.toString(), "Похоже, я сломался. Попробуйте позже"));
    } catch (TelegramApiException e) {
      logger.error(
          String.format(
              "Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
      e.printStackTrace();
    }
  }
}
