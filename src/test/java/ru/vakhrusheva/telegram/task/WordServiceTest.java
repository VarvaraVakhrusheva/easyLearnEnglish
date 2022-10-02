package ru.vakhrusheva.telegram.task;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vakhrusheva.telegram.enums.OperationEnum;
import ru.vakhrusheva.telegram.telegram.nonCommand.Settings;
import ru.vakhrusheva.telegram.exceptions.IllegalSettingsException;
import ru.vakhrusheva.telegram.fileProcessor.WordFileProcessorImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class WordServiceTest {
    Logger logger = LoggerFactory.getLogger(WordServiceTest.class);
    WordService service;

    WordServiceTest() {
        this.service = new WordService(new WordFileProcessorImpl());
    }

    @Test
    void getArithmeticFileTest() throws IOException {
        logger.info("Начинаем создание настроек");
        List<Settings> settingsList = createSettings();

        logger.info("Начинаем проверку формирования файлов");
        checkSettings(settingsList);

        logger.info("Успех");
    }

    /**
     * Создание списка настроек выгружаемого файла
     */
    private List<Settings> createSettings() {
        List<Settings> settingsList = new ArrayList<>();
        List<Integer> numbers = getNumbers();
        List<String> illegalNumbers = new ArrayList<>();

        for (Integer i : numbers) {
            for (Integer j : numbers) {
                try {
                    settingsList.add(new Settings(i, j));
                } catch (IllegalSettingsException e) {
                    illegalNumbers.add(String.format("%s и %s", i, j));
                }
            }
        }
        logger.info("Не удалось создать настройки для следующих пар значений:");
        illegalNumbers.forEach(logger::info);
        return settingsList;
    }

    /**
     * Создания списка используемых чисел
     */
    private List<Integer> getNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    /**
     * Проверка получения стрима на основе сформированных настроек
     */
    private void checkSettings(List<Settings> settingsList) throws IOException {
        for (Settings settings : settingsList) {
            logger.info(String.format("Проверяем пару значений %s - %s", settings.getLevel(), settings.getNumberOfWords()));
            Assertions.assertNotNull(service.getFile(OperationEnum.BASIC, settings));
            Assertions.assertNotNull(service.getFile(OperationEnum.INTERMEDIATE, settings));
            Assertions.assertNotNull(service.getFile(OperationEnum.ADVANCED, settings));
            logger.info(String.format("Проверка пары значений %s - %s прошла успешно", settings.getLevel(),
                    settings.getNumberOfWords()));
        }
    }
}