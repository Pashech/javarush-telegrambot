package com.github.javarushcommunity.jrtb.javarushclients.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class GroupDiscussionInfo extends GroupInfo{

    private UserDiscussionInfo userDiscussionInfo;
    private Integer commentsCount;
}
