package com.tcs.confluence.plugins.badges.data.services.impl;

import com.tcs.confluence.plugins.badges.data.ao.Statistic;
import org.junit.Before;

public class StatisticRefDaoServiceTest extends ReferencableDaoServiceTest<Statistic, StatisticDaoService>
{
  @Before
  public void setUp() throws Exception
  {
    referencableDaoService = new StatisticDaoService(createActiveObjects());
  }
}
