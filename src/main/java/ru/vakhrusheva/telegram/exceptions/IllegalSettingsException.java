package ru.vakhrusheva.telegram.exceptions;

public class IllegalSettingsException extends IllegalArgumentException {

  public IllegalSettingsException(String s) {
    super(s);
  }
}