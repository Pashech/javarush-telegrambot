package com.github.javarushcommunity.jrtb.command;

import org.junit.jupiter.api.DisplayName;
import static com.github.javarushcommunity.jrtb.command.UnknownCommand.*;
import static com.github.javarushcommunity.jrtb.command.CommandName.*;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return "/jhkjljhk";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
