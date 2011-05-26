package com.tcs.confluence.plugins.badges.data.upgrades.v1;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.tcs.confluence.plugins.badges.data.services.IAchievementDaoService;
import com.tcs.confluence.plugins.badges.data.services.impl.AchievementDaoService;
import com.tcs.confluence.plugins.badges.data.upgrades.AbstractUpgradeTask;
import com.tcs.confluence.plugins.badges.utils.initializers.AchievementsInitializer;

public class InitUpgradeTask extends AbstractUpgradeTask
{
  @Override
  protected int getVersion()
  {
    return 1;
  }

  @Override
  protected void doUpgrade(ActiveObjects ao)
  {
    initializeAchievements(ao);
  }

  private void initializeAchievements(ActiveObjects ao)
  {
    IAchievementDaoService achievementDaoService = new AchievementDaoService(ao);
    AchievementsInitializer achievementsInitializerinitializer = new AchievementsInitializer(achievementDaoService);
    achievementsInitializerinitializer.initialize();
  }

}
