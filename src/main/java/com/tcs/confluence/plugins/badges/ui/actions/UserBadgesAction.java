package com.tcs.confluence.plugins.badges.ui.actions;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.user.actions.AbstractUserProfileAction;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;

import java.util.List;

public class UserBadgesAction extends AbstractUserProfileAction
{
  private final UserManager userManager;
  private final AchievementManager achievementManager;

  private UserWrapper userWrapper;

  public UserWrapper getUserWrapper()
  {
    return userWrapper;
  }

  public List<Achievement> getAchievements()
  {
    return achievementManager.allAchievements();
  }

  public UserBadgesAction(UserManager userManager, AchievementManager achievementManager)
  {
    this.userManager = userManager;
    this.achievementManager = achievementManager;
  }

  @Override
  public String execute() throws Exception
  {
    userWrapper = userManager.get(getUsername());

    return "success";
  }
}
