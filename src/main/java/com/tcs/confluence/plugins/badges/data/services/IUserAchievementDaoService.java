package com.tcs.confluence.plugins.badges.data.services;

import com.atlassian.activeobjects.tx.Transactional;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.UserAchievement;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;

import java.util.Map;

@Transactional
public interface IUserAchievementDaoService extends IDaoService<UserAchievement>
{
  void addAchievementToUser(Achievement achievement, UserWrapper userWrapper);

  UserAchievement get(Achievement achievement, UserWrapper userWrapper);
  UserAchievement get(int achievementId, int userWrapperId);

  void setNotified(int achievementId, UserWrapper userWrapper, boolean notified);
}
