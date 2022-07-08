package com.github.javarushcommunity.jrtb.javarushclients;

import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupInfo;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupRequestArgs;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupsCountReuestArgs;

import java.util.List;

public interface JavarushGroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);
    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);
    Integer getGroupCount(GroupsCountReuestArgs countReuestArgs);
    GroupDiscussionInfo getGroupById(Integer id);
}
