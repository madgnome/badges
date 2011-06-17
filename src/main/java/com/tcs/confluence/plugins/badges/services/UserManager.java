package com.tcs.confluence.plugins.badges.services;

import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.user.User;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserWrapperDaoService;

import java.util.Calendar;

public class UserManager
{
  private final IUserWrapperDaoService userWrapperDaoService;
  private final AchievementManager achievementManager;

  public UserManager(IUserWrapperDaoService userWrapperDaoService, AchievementManager achievementManager)
  {
    this.userWrapperDaoService = userWrapperDaoService;
    this.achievementManager = achievementManager;
  }

  public UserWrapper getCurrentUserWrapper()
  {
    User user = AuthenticatedUserThreadLocal.getUser();

    return get(user);
  }

  public UserWrapper get(String username)
  {
    UserWrapper userWrapper = userWrapperDaoService.get(username);
    if (userWrapper == null)
    {
      userWrapper = userWrapperDaoService.getOrCreate(username);
      addFirstAchievements(userWrapper);
    }

    return userWrapper;
  }

  public UserWrapper get(User confluenceUser)
  {
    return get(confluenceUser.getName());
  }

  private void addFirstAchievements(UserWrapper userWrapper)
  {
    // TODO: ugly, this class shouldn't know what achievements to add.
    achievementManager.addAchievementToUser(AchievementRefEnum.NEWBIE, userWrapper);
  }

  public void updateLastLogin(UserWrapper userWrapper, long timestamp)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(timestamp);
    calendar.set(Calendar.HOUR, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    userWrapperDaoService.updateLastLogin(userWrapper, calendar.getTime());
  }
}
