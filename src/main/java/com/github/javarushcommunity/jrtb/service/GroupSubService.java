package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.javarushclients.dto.GroupDiscussionInfo;
import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;

public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}
