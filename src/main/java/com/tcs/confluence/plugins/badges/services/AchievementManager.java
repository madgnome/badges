package com.tcs.confluence.plugins.badges.services;

import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IAchievementDaoService;
import com.tcs.confluence.plugins.badges.data.services.IUserAchievementDaoService;

import java.util.List;

public class AchievementManager
{
  private final IAchievementDaoService achievementDaoService;
  private final IUserAchievementDaoService userAchievementDaoService;

  public AchievementManager(IAchievementDaoService achievementDaoService, IUserAchievementDaoService userAchievementDaoService)
  {
    this.achievementDaoService = achievementDaoService;
    this.userAchievementDaoService = userAchievementDaoService;
  }

  public Achievement addAchievementToUser(AchievementRefEnum achievementRefEnum, UserWrapper userWrapper)
  {
    Achievement achievement = achievementDaoService.get(achievementRefEnum);
    if (achievement != null)
    {
      userAchievementDaoService.addAchievementToUser(achievement, userWrapper);
    }

    return achievement;
  }

  public List<Achievement> allAchievements()
  {
    return achievementDaoService.all();
  }
}
