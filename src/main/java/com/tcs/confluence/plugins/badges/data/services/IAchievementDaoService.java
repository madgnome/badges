package com.tcs.confluence.plugins.badges.data.services;

import com.atlassian.activeobjects.tx.Transactional;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;

import java.util.List;
import java.util.Map;

@Transactional
public interface IAchievementDaoService extends IReferencableDaoService<Achievement, AchievementRefEnum>
{
  List<Achievement> allActive();

  void activate(int id, boolean activate);
}
