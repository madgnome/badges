package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.tcs.confluence.plugins.badges.data.ao.Statistic;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserStatistic;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IStatisticDaoService;
import com.tcs.confluence.plugins.badges.data.services.IUserStatisticDaoService;

public class UserStatisticDaoService extends BaseDaoService<UserStatistic> implements IUserStatisticDaoService
{
  private final IStatisticDaoService statisticDaoService;

  @Override
  protected Class<UserStatistic> getClazz()
  {
    return UserStatistic.class;
  }

  public UserStatisticDaoService(ActiveObjects ao, IStatisticDaoService statisticDaoService)
  {
    super(ao);
    this.statisticDaoService = statisticDaoService;
  }

  @Override
  public UserStatistic get(UserWrapper userWrapper, StatisticRefEnum statisticRefEnum)
  {
    Statistic statisticRef = statisticDaoService.get(statisticRefEnum);
    if (statisticRef == null)
    {
      return null;
    }

    return getOrCreate(statisticRef, userWrapper);
  }

  @Override
  public UserStatistic createOrUpdate(StatisticRefEnum statRef, UserWrapper userWrapper, int value)
  {
    Statistic statisticRef = statisticDaoService.get(statRef);

    UserStatistic userStatistic = null;
    if (statisticRef != null)
    {
      userStatistic = getOrCreate(statisticRef, userWrapper);
      userStatistic.setValue(value);
      userStatistic.save();
    }

    return userStatistic;
  }

  @Override
  public UserStatistic incrementAndGet(UserWrapper userWrapper, StatisticRefEnum statisticRefEnum)
  {
    UserStatistic userStatistic = get(userWrapper, statisticRefEnum);
    userStatistic.setValue(userStatistic.getValue() + 1);
    userStatistic.save();

    return userStatistic;
  }

  private UserStatistic getOrCreate(Statistic statistic, UserWrapper userWrapper)
  {
    final UserStatistic[] userStatistics = ao.find(UserStatistic.class, "STATISTIC_ID = ? AND USER_WRAPPER_ID = ?", statistic.getID(), userWrapper.getID());
    if (userStatistics.length > 1)
    {
      throw new IllegalStateException("Found more than one statistic (" + userStatistics.length + ") with ref " + statistic.getRef() + " for user " + userWrapper.getUserName());
    }

    return userStatistics.length == 0 ? create(statistic, userWrapper) : userStatistics[0];
  }

  private UserStatistic create(Statistic statistic, UserWrapper userWrapper)
  {
    UserStatistic userStatistic = ao.create(UserStatistic.class);
    userStatistic.setStatistic(statistic);
    userStatistic.setUserWrapper(userWrapper);
    userStatistic.save();

    return userStatistic;
  }
}
