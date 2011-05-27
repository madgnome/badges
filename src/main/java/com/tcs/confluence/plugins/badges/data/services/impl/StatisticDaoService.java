package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.tcs.confluence.plugins.badges.data.ao.Statistic;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.services.IStatisticDaoService;

public class StatisticDaoService extends ReferencableDaoService<Statistic, StatisticRefEnum> implements IStatisticDaoService
{
  @Override
  protected Class<Statistic> getClazz()
  {
    return Statistic.class;
  }

  public StatisticDaoService(ActiveObjects ao)
  {
    super(ao);
  }
}
