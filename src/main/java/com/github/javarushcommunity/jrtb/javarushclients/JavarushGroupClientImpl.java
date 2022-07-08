package com.github.javarushcommunity.jrtb.javarushclients;

import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupInfo;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupRequestArgs;
import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupsCountReuestArgs;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JavarushGroupClientImpl implements JavarushGroupClient{

    private final String javarushApiGroupPath;

    public JavarushGroupClientImpl(@Value("${javarush.api.path}") String javarushApi){
        this.javarushApiGroupPath = javarushApi + "/groups";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                })
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupsCountReuestArgs countReuestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", javarushApiGroupPath))
                .queryString(countReuestArgs.populateQueries())
                .asString()
                .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", javarushApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }
}
