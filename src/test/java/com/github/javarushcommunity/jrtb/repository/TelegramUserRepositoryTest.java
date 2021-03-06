package com.github.javarushcommunity.jrtb.repository;

import com.github.javarushcommunity.jrtb.repository.entity.GroupSub;
import com.github.javarushcommunity.jrtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TelegramUserRepositoryTest {

    @Autowired
    TelegramUserRepository telegramUserRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers(){

        List<TelegramUser> users = telegramUserRepository.findAllByActiveTrue();
        Assertions.assertEquals(5, users.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveTelegramUser(){
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("1234567890");
        telegramUser.setActive(false);
        telegramUserRepository.save(telegramUser);

        Optional<TelegramUser> saved = telegramUserRepository.findById(telegramUser.getChatId());

        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser, saved.get());
    }

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/fiveGroupSubsForUser.sql"})
    @Test
    public void shouldProperlyGetAllGroupSubsForUser(){

        Optional<TelegramUser> userFromDb = telegramUserRepository.findById("1");
        Assertions.assertTrue(userFromDb.isPresent());

        List<GroupSub> groupSubList = userFromDb.get().getGroupSubs();
        for (int i = 0; i < groupSubList.size(); i++) {
            Assertions.assertEquals(String.format("g%s", (i + 1)), groupSubList.get(i).getTitle());
            Assertions.assertEquals(i + 1, groupSubList.get(i).getId());
            Assertions.assertEquals(i + 1, groupSubList.get(i).getLastArticleId());
        }

    }

}