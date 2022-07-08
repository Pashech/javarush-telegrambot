package com.github.javarushcommunity.jrtb.javarushclients;

import com.github.javarushcommunity.jrtb.javarushclients.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
class JavarushGroupClientTest {

    private final JavarushGroupClient groupClient = new JavarushGroupClientImpl("https://javarush.ru/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupWithEmptyArgs(){
        GroupRequestArgs groupRequestArgs = GroupRequestArgs.builder().build();
        List<GroupInfo> groupInfoList = groupClient.getGroupList(groupRequestArgs);

        Assertions.assertNotNull(groupInfoList);
        Assertions.assertFalse(groupInfoList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit(){
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        List<GroupInfo> groupInfo = groupClient.getGroupList(args);
        groupInfo.stream().forEach(g -> System.out.println(g));
        Assertions.assertNotNull(groupInfo);
        Assertions.assertEquals(3, groupInfo.size());
    }

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs(){
        GroupRequestArgs args = GroupRequestArgs.builder().build();
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit(){
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount(){
        GroupsCountReuestArgs args = GroupsCountReuestArgs.builder().build();
        Integer count = groupClient.getGroupCount(args);

        Assertions.assertEquals(32, count);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount(){
        GroupsCountReuestArgs args = GroupsCountReuestArgs.builder()
                .type(GroupInfoType.TECH)
                .build();
        Integer groupCount = groupClient.getGroupCount(args);

        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById(){
        Integer androidGroupId = 16;
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(GroupInfoType.TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }

}