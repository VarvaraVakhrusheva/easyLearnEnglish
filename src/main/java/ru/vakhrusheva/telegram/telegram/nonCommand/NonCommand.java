package ru.vakhrusheva.telegram.telegram.nonCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.vakhrusheva.telegram.exceptions.IllegalSettingsException;
import ru.vakhrusheva.telegram.telegram.Bot;

import java.util.ArrayList;
import java.util.List;

public class NonCommand {
  private Logger logger = LoggerFactory.getLogger(NonCommand.class);

  public SendMessage nonCommandExecute(Long chatId, String userName, String text, Update update) {
    logger.debug(
        String.format(
            "Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой",
            userName, text));
    Settings settings;
    SendMessage answer;
    try {
      if (update.hasCallbackQuery()) {
        switch (update.getCallbackQuery().getData()) {
          case "Времена":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Past");
              buttons.add("Present");
              buttons.add("Future");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Выберите время, которое вас интересует:", buttons, buttons);
              return answer;
            }
          case "Синтаксис":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Предложения");
              buttons.add("Вопросы");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId,
                      "Выберите раздел синтаксиса, который вас интересует:",
                      buttons,
                      buttons);
              return answer;
            }
          case "Глаголы":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Основные глаголы");
              buttons.add("Неправильные глаголы");
              buttons.add("Модальные глаголы");
              buttons.add("Неличные формы");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Выберите глаголы, которые вас интересуют:", buttons, buttons);
              return answer;
            }
          case "Части речи":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Существительное");
              buttons.add("Артикль");
              buttons.add("Местоимение");
              buttons.add("Прилагательное");
              buttons.add("Числительное");
              buttons.add("Другие части речи");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Выберите часть речи, которая вас интересует:", buttons, buttons);
              return answer;
            }
          case "Past":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Past");
              callbackData.add("Задания Past");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Present":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Present");
              callbackData.add("Задания Present");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Future":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Future");
              callbackData.add("Задания Future");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }

          case "Предложения":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Подлежащее");
              buttons.add("Сказуемое");
              buttons.add("Дополнение");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId,
                      "Выберите часть предложения, которая вас интересует:",
                      buttons,
                      buttons);
              return answer;
            }
          case "Вопросы":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Общий вопрос");
              buttons.add("Специальный вопрос");
              buttons.add("Вопрос к подлежащему");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Выберите вопрос, который вас интересует:", buttons, buttons);
              return answer;
            }
          case "Подлежащее":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Подлежащее");
              callbackData.add("Задания Подлежащее");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Сказуемое":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Сказуемое");
              callbackData.add("Задания Сказуемое");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Дополнение":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Дополнение");
              callbackData.add("Задания Дополнение");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Общий вопрос":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Общий вопрос");
              callbackData.add("Задания Общий вопрос");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Специальный вопрос":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Специальный вопрос");
              callbackData.add("Задания Специальный вопрос");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Вопрос к подлежащему":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Вопрос к подлежащему");
              callbackData.add("Задания Вопрос к подлежащему");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Существительное":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Существительное");
              callbackData.add("Задания Существительное");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Артикль":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Артикль");
              callbackData.add("Задания Артикль");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Местоимение":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Местоимение");
              callbackData.add("Задания Местоимение");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Прилагательное":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Прилагательное");
              callbackData.add("Задания Прилагательное");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Числительное":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Числительное");
              callbackData.add("Задания Числительное");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
          case "Другие части речи":
            {
              List<String> buttons = new ArrayList<>();
              buttons.add("Правила");
              buttons.add("Задания");
              List<String> callbackData = new ArrayList<>();
              callbackData.add("Правила Другие части речи");
              callbackData.add("Задания Другие части речи");
              answer =
                  NonCommandUtil.getSendMessage(
                      chatId, "Что вы хотите получить сейчас?", buttons, callbackData);
              return answer;
            }
        }
      }
      logger.debug(
          String.format(
              "Пользователь %s. Пробуем создать объект настроек из сообщения \"%s\"",
              userName, text));
      settings = createSettings(text);
      saveUserSettings(chatId, settings);
      logger.debug(
          String.format(
              "Пользователь %s. Объект настроек из сообщения \"%s\" создан и сохранён",
              userName, text));
      answer = new SendMessage();
      answer.setText(
          String.format(
              "Настройки обновлены. Вы всегда можете их посмотреть с помощью /settings%s", ""));
      answer.setChatId(String.valueOf(chatId));
    } catch (IllegalSettingsException e) {
      logger.debug(
          String.format(
              "Пользователь %s. Не удалось создать объект настроек из сообщения \"%s\". " + "%s",
              userName, text, e.getMessage()));
      answer = new SendMessage();
      answer.setChatId(chatId.toString());
      answer.setText(
          e.getMessage()
              + "\n\n❗ Настройки не были изменены. Вы всегда можете их посмотреть с помощью /settings");
    } catch (Exception e) {
      logger.debug(
          String.format(
              "Пользователь %s. Не удалось создать объект настроек из сообщения \"%s\". "
                  + "%s. %s",
              userName, text, e.getClass().getSimpleName(), e.getMessage()));
      answer = new SendMessage();
      answer.setChatId(chatId.toString());
      answer.setText(
          "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату, или "
              + "использовали слишком большие числа\n\n"
              + "Возможно, Вам поможет /help");
    }
    logger.debug(
        String.format(
            "Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой",
            userName, text));
    return answer;
  }

  private Settings createSettings(String text) throws IllegalArgumentException {
    // отсекаем файлы, стикеры, гифки и прочее
    if (text == null) {
      throw new IllegalArgumentException("Сообщение не является текстом");
    }
    text =
        text.replaceAll("-", "") // избавляемся от отрицательных чисел
            .replaceAll(", ", ",") // меняем ошибочный разделитель "запятая+пробел" на запятую
            .replaceAll(" ", ","); // меняем разделитель-пробел на запятую
    String[] parameters = text.split(",");
    if (parameters.length != 2) {
      throw new IllegalArgumentException(
          String.format("Не удалось разбить сообщение \"%s\" на 2 составляющие", text));
    }
    int level = Integer.parseInt(parameters[0]);
    int numberOfWords = Integer.parseInt(parameters[1]);

    validateSettings(level, numberOfWords);
    return new Settings(level, numberOfWords);
  }

  private void validateSettings(int level, int numberOfWords) {
    if (level == 0 || numberOfWords == 0) {
      throw new IllegalSettingsException("Ни один из параметров не может равняться 0.");
    }
    if (level > 3 || numberOfWords > 50) {
      throw new IllegalSettingsException("Заданные значения слишком велики.");
    }
  }

  private void saveUserSettings(Long chatId, Settings settings) {
    if (!settings.equals(Bot.getDefaultSettings())) {
      Bot.getUserSettings().put(chatId, settings);
    } else {
      Bot.getUserSettings().remove(chatId);
    }
  }
}
