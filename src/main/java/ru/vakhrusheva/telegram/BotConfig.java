package ru.vakhrusheva.telegram;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.vakhrusheva.telegram.telegram.Bot;
import ru.vakhrusheva.telegram.telegram.commands.service.HelpCommand;
import ru.vakhrusheva.telegram.telegram.commands.service.StartCommand;
import ru.vakhrusheva.telegram.telegram.nonCommand.NonCommand;

@Configuration
public class BotConfig {

    @Bean
    public Bot bot() {
        return new Bot(new DefaultBotOptions());
    }

    @Bean
    public HelpCommand helpCommand() {
        return new HelpCommand("help", "Помощь");
    }

    @Bean
    public NonCommand nonCommand() {
        return new NonCommand();
    }

    @Bean
    public StartCommand startCommand() {
        return new StartCommand("start", "Старт");
    }
}
