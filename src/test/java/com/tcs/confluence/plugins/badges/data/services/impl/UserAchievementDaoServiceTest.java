package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.user.User;
import com.atlassian.user.impl.DefaultUser;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.UserAchievement;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserAchievementDaoServiceTest extends BaseDaoServiceTest<UserAchievement, UserAchievementDaoService>
{
  private AchievementDaoService achievementDaoService;
  private UserWrapperDaoService userWrapperDaoService;

  @Before
  public void setUp()
  {
    ActiveObjects ao = createActiveObjects();
    daoService = new UserAchievementDaoService(ao);

    achievementDaoService = new AchievementDaoService(ao);
    userWrapperDaoService = new UserWrapperDaoService(ao);
  }

  @Test
  public void addAchievementShouldAddAchievementToUser()
  {
    UserWrapper userWrapper = createUserWrapper();
    Achievement achievement = createAchievement("Achievement");
    assertEquals(0, userWrapper.getAchievements().length);
    
    daoService.addAchievementToUser(achievement, userWrapper);
    entityManager.flushAll();

    assertEquals(achievement, userWrapper.getAchievements()[0]);
  }

  @Test
  public void addAchievementShouldNotAddIfExist()
  {
    UserWrapper userWrapper = createUserWrapper();
    Achievement achievement = createAchievement("Achievement");
    daoService.addAchievementToUser(achievement, userWrapper);
    daoService.addAchievementToUser(achievement, userWrapper);
    entityManager.flushAll();

    assertEquals(1, userWrapper.getAchievements().length);
  }

  @Test
  public void getShouldReturnNullIfAny()
  {
    UserWrapper userWrapper = createUserWrapper();
    Achievement achievement = createAchievement("Achievement");

    assertNull(daoService.get(achievement, userWrapper));
  }

  @Test
  public void getShouldReturnUserAchievement()
  {
    UserWrapper userWrapper = createUserWrapper();
    Achievement achievement = createAchievement("Achievement");

    daoService.addAchievementToUser(achievement, userWrapper);
    entityManager.flushAll();

    UserAchievement userAchievement = daoService.get(achievement, userWrapper);
    assertNotNull(userAchievement);
    assertEquals(achievement, userAchievement.getAchievement());
    assertEquals(userWrapper, userAchievement.getUserWrapper());
  }

  private Achievement createAchievement(String ref)
  {
    Achievement achievement = achievementDaoService.create(ref);
    achievement.save();

    return achievement;
  }

  private UserWrapper createUserWrapper()
  {
    User user = new DefaultUser("bob", "Sponge Bob", "sponge@bob.com");

    return userWrapperDaoService.create(user);
  }
}
