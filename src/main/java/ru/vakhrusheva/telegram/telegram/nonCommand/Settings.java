package ru.vakhrusheva.telegram.telegram.nonCommand;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Settings {

    private int level;

    private int numberOfWords;

    public Settings(int level, int numberOfWords) {
        this.level = level;
        this.numberOfWords = numberOfWords;
}
}