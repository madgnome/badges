package com.tcs.confluence.plugins.badges.data.services.impl;

import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import org.junit.Before;

public class AchievementDaoServiceTest extends ReferencableDaoServiceTest<Achievement, AchievementDaoService>
{
  @Before
  public void setUp() throws Exception
  {
    referencableDaoService = new AchievementDaoService(createActiveObjects());
  }

}
