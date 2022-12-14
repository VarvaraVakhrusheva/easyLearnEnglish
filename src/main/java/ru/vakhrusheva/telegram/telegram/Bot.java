package ru.vakhrusheva.telegram.telegram;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.vakhrusheva.telegram.telegram.commands.GrammarCommand;
import ru.vakhrusheva.telegram.telegram.commands.operations.*;
import ru.vakhrusheva.telegram.telegram.commands.service.SettingsCommand;
import ru.vakhrusheva.telegram.telegram.nonCommand.NonCommandUtil;
import ru.vakhrusheva.telegram.telegram.nonCommand.Settings;
import ru.vakhrusheva.telegram.Utils;
import ru.vakhrusheva.telegram.telegram.commands.service.HelpCommand;
import ru.vakhrusheva.telegram.telegram.commands.service.StartCommand;
import ru.vakhrusheva.telegram.telegram.nonCommand.NonCommand;

import java.util.HashMap;
import java.util.Map;

@Service
public final class Bot extends TelegramLongPollingCommandBot {
  private Logger logger = LoggerFactory.getLogger(Bot.class);
  private final String BOT_NAME = "easy_learn_eng_bot";
  private final String BOT_TOKEN = "5716451887:AAFqKg-S_PqJMtlF-nHcQOMRdWWc1oCPlgY";

  @Getter private static final Settings defaultSettings = new Settings(2, 15);
  private final NonCommand nonCommand;

  /** Настройки файла для разных пользователей. Ключ - уникальный id чата */
  @Getter private static Map<Long, Settings> userSettings;

  public Bot(DefaultBotOptions options) {
    super(options);
    logger.debug("Конструктор суперкласса отработал");
    logger.debug("Имя и токен присвоены");

    this.nonCommand = new NonCommand();
    logger.debug("Класс обработки сообщения, не являющегося командой, создан");

    register(new StartCommand("start", "Старт"));
    logger.debug("Команда start создана");

    register(new IntermediateCommand("intermediate", "Средний уровень"));
    logger.debug("Команда intermediate создана");

    register(new AdvancedCommand("advanced", "Продвинутый уровень"));
    logger.debug("Команда advanced создана");

    register(new BasicCommand("basic", "Базовый уровень"));
    logger.debug("Команда basic создана");

    register(new HelpCommand("help", "Помощь"));
    logger.debug("Команда help создана");

    register(new GrammarCommand("grammar", "Грамматика"));
    logger.debug("Команда grammar создана");

    register(new SettingsCommand("settings", "Мои настройки"));
    logger.debug("Команда settings создана");

    userSettings = new HashMap<>();
    logger.info("Бот создан!");
  }

  @Override
  public String getBotToken() {
    return BOT_TOKEN;
  }

  @Override
  public String getBotUsername() {
    return BOT_NAME;
  }

  @Override
  public void processNonCommandUpdate(Update update) {
    if (update.hasCallbackQuery()) {
      try {
        if (update.getCallbackQuery().getData().startsWith("Правила ")
            || update.getCallbackQuery().getData().startsWith("Задания ")) {
          SendDocument document =
              NonCommandUtil.getSendDocument(update, update.getCallbackQuery().getData());
          execute(document);
        } else {
          execute(
              nonCommand.nonCommandExecute(
                  update.getCallbackQuery().getFrom().getId(),
                  update.getCallbackQuery().getFrom().getUserName(),
                  update.getCallbackQuery().getMessage().getText(),
                  update));
        }
      } catch (TelegramApiException e) {
        logger.error(
            String.format(
                "Ошибка %s. Сообщение, не являющееся командой. Пользователь: %s",
                e.getMessage(), update.getCallbackQuery().getFrom().getUserName()));
        e.printStackTrace();
      }
    } else {
      Message msg = update.getMessage();
      Long chatId = msg.getChatId();
      String userName = Utils.getUserName(msg);
      try {
        execute(nonCommand.nonCommandExecute(chatId, userName, msg.getText(), update));
      } catch (TelegramApiException e) {
        logger.error(
            String.format(
                "Ошибка %s. Сообщение, не являющееся командой. Пользователь: %s",
                e.getMessage(), userName));
        e.printStackTrace();
      }
    }
  }

  public static Settings getUserSettings(Long chatId) {
    Map<Long, Settings> userSettings = Bot.getUserSettings();
    Settings settings = userSettings.get(chatId);
    if (settings == null) {
      return defaultSettings;
    }
    return settings;
  }
}
