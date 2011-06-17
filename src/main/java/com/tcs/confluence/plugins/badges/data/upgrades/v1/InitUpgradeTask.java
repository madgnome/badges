package com.tcs.confluence.plugins.badges.data.upgrades.v1;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import com.tcs.confluence.plugins.badges.data.ao.*;
import com.tcs.confluence.plugins.badges.data.services.IAchievementDaoService;
import com.tcs.confluence.plugins.badges.data.services.IStatisticDaoService;
import com.tcs.confluence.plugins.badges.data.services.impl.AchievementDaoService;
import com.tcs.confluence.plugins.badges.data.services.impl.StatisticDaoService;
import com.tcs.confluence.plugins.badges.data.upgrades.AbstractUpgradeTask;
import com.tcs.confluence.plugins.badges.utils.initializers.AchievementsInitializer;

public class InitUpgradeTask extends AbstractUpgradeTask
{
  @Override
  protected int getVersion()
  {
    return 7;
  }

  @Override
  protected void doUpgrade(ActiveObjects ao)
  {
    initializeAO(ao);

    initializeAchievements(ao);
    initializeStatistics(ao);
  }

  private void initializeAO(ActiveObjects ao)
  {
    ao.migrate(Achievement.class,
               Statistic.class,
               UserAchievement.class,
               UserStatistic.class,
               UserWrapper.class);
  }

  private void initializeAchievements(ActiveObjects ao)
  {
    IAchievementDaoService achievementDaoService = new AchievementDaoService(ao);
    AchievementsInitializer achievementsInitializerinitializer = new AchievementsInitializer(achievementDaoService);
    achievementsInitializerinitializer.initialize();
  }

  private void initializeStatistics(ActiveObjects ao)
  {
    IStatisticDaoService statisticDaoService = new StatisticDaoService(ao);
    for (StatisticRefEnum statisticRefEnum : StatisticRefEnum.values())
    {
      statisticDaoService.getOrCreate(statisticRefEnum);
    }
  }

}
