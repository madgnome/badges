package com.tcs.confluence.plugins.badges.rules;

import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;

public interface IRule
{
  AchievementRefEnum getAchievementRef();
  void check();
}
