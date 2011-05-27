package com.tcs.confluence.plugins.badges.data.services;

import com.atlassian.activeobjects.tx.Transactional;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserStatistic;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;

@Transactional
public interface IUserStatisticDaoService extends IDaoService<UserStatistic>
{
  UserStatistic get(UserWrapper userWrapper, StatisticRefEnum statisticRefEnum);
  UserStatistic createOrUpdate(StatisticRefEnum statRef, UserWrapper userWrapper, int value);
}
