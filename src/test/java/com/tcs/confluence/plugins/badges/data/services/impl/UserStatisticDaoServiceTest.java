package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.user.User;
import com.atlassian.user.impl.DefaultUser;
import com.tcs.confluence.plugins.badges.data.ao.Statistic;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserStatistic;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserStatisticDaoServiceTest extends BaseDaoServiceTest<UserStatistic, UserStatisticDaoService>
{
  private StatisticDaoService StatisticDaoService;
  private UserWrapperDaoService userWrapperDaoService;

  @Before
  public void setUp() throws Exception
  {
    StatisticDaoService = new StatisticDaoService(createActiveObjects());
    userWrapperDaoService = new UserWrapperDaoService(createActiveObjects());

    daoService = new UserStatisticDaoService(createActiveObjects(), StatisticDaoService);
  }

  @Test
  public void getShouldReturnNullIfStatisticRefDoesntExist()
  {
    UserWrapper userWrapper = createUserWrapper();
    assertNull(daoService.get(userWrapper, StatisticRefEnum.COMMENT_COUNT));
  }

  @Test
  public void getShouldReturnStatisticWithValueAtZeroIfAny()
  {
    StatisticRefEnum statRef = StatisticRefEnum.COMMENT_COUNT;
    createStatisticRef(statRef);
    UserWrapper userWrapper = createUserWrapper();

    UserStatistic userStatistic = daoService.get(userWrapper, statRef);
    assertNotNull(userStatistic);
    assertEquals(0, userStatistic.getValue());
  }

  @Test
  public void createOrUpdateShouldCreateStatisticWithValueIfAny()
  {
    StatisticRefEnum statRef = StatisticRefEnum.COMMENT_COUNT;
    createStatisticRef(statRef);
    UserWrapper userWrapper = createUserWrapper();

    int value = 1;
    daoService.createOrUpdate(statRef, userWrapper, value);
    UserStatistic userStatistic = daoService.get(userWrapper, statRef);
    assertNotNull(userStatistic);
    assertEquals(value, userStatistic.getValue());
  }

  @Test
  public void createOrUpdateShouldUpdateStatisticWithValueIfAny()
  {
    StatisticRefEnum statRef = StatisticRefEnum.COMMENT_COUNT;
    createStatisticRef(statRef);
    UserWrapper userWrapper = createUserWrapper();

    int value = 2;
    daoService.createOrUpdate(statRef, userWrapper, 1);
    daoService.createOrUpdate(statRef, userWrapper, value);
    UserStatistic userStatistic = daoService.get(userWrapper, statRef);
    assertNotNull(userStatistic);
    assertEquals(value, userStatistic.getValue());
  }

  private Statistic createStatisticRef(StatisticRefEnum statisticRefEnum)
  {
    return StatisticDaoService.create(statisticRefEnum);
  }

  private UserWrapper createUserWrapper()
  {
    User user = new DefaultUser("bob", "Sponge Bob", "sponge@bob.com");
    return userWrapperDaoService.create(user);
  }
}
