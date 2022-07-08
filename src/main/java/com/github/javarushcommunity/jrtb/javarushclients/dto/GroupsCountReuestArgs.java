package com.github.javarushcommunity.jrtb.javarushclients.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.nonNull;

@Getter
@Builder
public class GroupsCountReuestArgs {

    private String query;
    private GroupInfoType type;
    private GroupFilter filter;

    public Map populateQueries(){
        Map queries = new HashMap();

        if(nonNull(query)){
            queries.put("query", query);
        }
        if(nonNull(type)){
            queries.put("type", type);
        }
        if(nonNull(filter)){
            queries.put("filter", filter);
        }
        return queries;
    }
}
