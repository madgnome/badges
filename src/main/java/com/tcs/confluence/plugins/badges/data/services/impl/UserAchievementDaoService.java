package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.UserAchievement;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserAchievementDaoService;
import net.java.ao.Query;

import java.util.HashMap;
import java.util.Map;

public class UserAchievementDaoService extends BaseDaoService<UserAchievement> implements IUserAchievementDaoService
{
  @Override
  protected Class<UserAchievement> getClazz()
  {
    return UserAchievement.class;
  }

  public UserAchievementDaoService(ActiveObjects ao)
  {
    super(ao);
  }

  @Override
  public void addAchievementToUser(Achievement achievement, UserWrapper userWrapper)
  {
    if (get(achievement, userWrapper) == null)
    {
      UserAchievement userAchievement = ao.create(UserAchievement.class);
      userAchievement.setUserWrapper(userWrapper);
      userAchievement.setAchievement(achievement);
      userAchievement.save();
    }
  }

  @Override
  public UserAchievement get(Achievement achievement, UserWrapper userWrapper)
  {
    return get(achievement.getID(), userWrapper.getID());
  }

  @Override
  public UserAchievement get(int achievementId, int userWrapperId)
  {
    UserAchievement[] userAchievements =
            ao.find(clazz, "ACHIEVEMENT_ID = ? AND USER_WRAPPER_ID = ?", achievementId, userWrapperId);

    return userAchievements.length > 0 ? userAchievements[0] : null;
  }

  @Override
  public void setNotified(int achievementId, UserWrapper userWrapper, boolean notified)
  {
    UserAchievement userAchievement = get(achievementId, userWrapper.getID());
    userAchievement.setNotified(notified);
    userAchievement.save();
  }
}
