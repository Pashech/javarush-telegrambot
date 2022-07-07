package com.github.javarushcommunity.jrtb.command;

import org.junit.jupiter.api.DisplayName;
import static com.github.javarushcommunity.jrtb.command.StartCommand.*;
import static com.github.javarushcommunity.jrtb.command.CommandName.*;

@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService, telegramUserService);
    }
}
