package ru.vakhrusheva.telegram.task;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.vakhrusheva.telegram.enums.OperationEnum;
import ru.vakhrusheva.telegram.fileProcessor.WordFileProcessorImpl;
import ru.vakhrusheva.telegram.telegram.nonCommand.Settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WordService {
  WordFileProcessorImpl fileProcessor;

  public FileInputStream getFile(OperationEnum level, Settings settings) throws IOException {
    List<String> taskList = new ArrayList<>();
    switch (level) {
      case BASIC:
        fillTaskList(settings, taskList, "basic.txt");
        break;
      case INTERMEDIATE:
        fillTaskList(settings, taskList, "inter.txt");
        break;
      case ADVANCED:
        fillTaskList(settings, taskList, "advanced.txt");
        break;
    }
    if (taskList.isEmpty()) {
      throw new IllegalArgumentException(
          String.format(
              "По непонятным причинам по заданным настройкам "
                  + "(level = %s, numberOfWords = %s) не удалось создать ни одной строки "
                  + "со словами",
              settings.getLevel(), settings.getNumberOfWords()));
    }
    return fileProcessor.createWordFile(taskList);
  }

  private void fillTaskList(Settings settings, List<String> taskList, String s) throws IOException {
    Path path;
    long count;
    String absolutePath =
        "C:\\Users\\vavik\\Desktop\\Java\\easyLearnEnglish\\src\\main\\resources\\";
    path = Paths.get(absolutePath + s);
    count = Files.lines(path).count();
    for (int i = 1; i <= settings.getNumberOfWords(); i++) {
      int randomInt = ThreadLocalRandom.current().nextInt(1, (int) (count + 1));
      taskList.add(Files.lines(path).skip(randomInt - 1).findFirst().get());
    }
  }
}
