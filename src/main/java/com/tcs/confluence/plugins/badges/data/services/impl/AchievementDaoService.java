package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.services.IAchievementDaoService;

import java.util.*;

public class AchievementDaoService extends ReferencableDaoService<Achievement, AchievementRefEnum> implements IAchievementDaoService
{
  @Override
  protected Class<Achievement> getClazz()
  {
    return Achievement.class;
  }

  public AchievementDaoService(ActiveObjects ao)
  {
    super(ao);
  }

  public List<Achievement> allActive()
  {
    return Arrays.asList(ao.find(getClazz(), "ACTIVE = TRUE"));
  }

  public void activate(int id, boolean activate)
  {
    Achievement achievement = get(id);
    if (achievement == null)
    {
      logger.warn("No achievement found with this id: ", id);
      return;
    }

    achievement.setActive(activate);
    achievement.save();
  }
}
