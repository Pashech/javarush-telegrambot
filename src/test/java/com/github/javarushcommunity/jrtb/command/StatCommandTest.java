package com.github.javarushcommunity.jrtb.command;

import static com.github.javarushcommunity.jrtb.command.StatCommand.*;
import static com.github.javarushcommunity.jrtb.command.CommandName.*;

public class StatCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STAT_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StatCommand(telegramUserService, sendBotMessageService);
    }
}
