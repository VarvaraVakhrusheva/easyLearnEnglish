package ru.vakhrusheva.telegram.telegram.nonCommand;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NonCommandUtil {

  public static SendMessage getSendMessage(
      Long chatId, String text, List<String> buttons, List<String> callbackData) {
    SendMessage answer;
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    for (int i = 0; i < buttons.size(); i++) {
      List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
      keyboardButtonsRow.add(
          InlineKeyboardButton.builder()
              .text(buttons.get(i))
              .callbackData(callbackData.get(i))
              .build());
      rowList.add(keyboardButtonsRow);
    }
    inlineKeyboardMarkup.setKeyboard(rowList);
    answer = new SendMessage();
    answer.setChatId(String.valueOf(chatId));
    answer.setText(text);
    answer.setReplyMarkup(inlineKeyboardMarkup);
    return answer;
  }

  public static SendDocument getSendDocument(Update update, String callback) {
    SendDocument document = new SendDocument();
    document.setChatId(update.getCallbackQuery().getFrom().getId().toString());
    document.setDocument(
        new InputFile()
            .setMedia(
                new File("C:\\Users\\vavik\\Desktop\\ЯГТУ\\ВКР\\Файлы\\" + callback + ".docx")));
    return document;
  }
}
