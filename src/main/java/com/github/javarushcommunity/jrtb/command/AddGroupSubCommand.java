package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.javarushclients.JavarushGroupClient;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupRequestArgs;
import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.service.GroupSubService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.javarushcommunity.jrtb.command.CommandUtils.getChatId;
import static com.github.javarushcommunity.jrtb.command.CommandUtils.getMessage;
import static com.github.javarushcommunity.jrtb.command.CommandName.ADD_GROUP_SUB;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static java.util.Objects.isNull;

public class AddGroupSubCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final JavarushGroupClient javarushGroupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService, JavarushGroupClient javarushGroupClient, GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.javarushGroupClient = javarushGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if(getMessage(update).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())){
            sendGroupIdList(getChatId(update));
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update);
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = javarushGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "???????????????? ???? ???????????? " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }

    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "?????? ???????????? ?? ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(String chatId) {
        String groupIds = javarushGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "?????????? ?????????????????????? ???? ???????????? - ?????????????? ?????????????? ???????????? ?? ID ????????????. \n" +
                "????????????????: /addGroupSub 16. \n\n" +
                "?? ???????????????????? ???????????? ???????? ?????????? - ?????????????? ?????????? ???????????? :) \n\n" +
                "?????? ???????????? - ID ???????????? \n\n" +
                "%s";

        sendBotMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}
