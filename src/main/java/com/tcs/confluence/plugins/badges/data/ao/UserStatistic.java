package com.tcs.confluence.plugins.badges.data.ao;

import net.java.ao.Entity;

public interface UserStatistic extends Entity
{
  Statistic getStatistic();
  void setStatistic(Statistic statistic);

  UserWrapper getUserWrapper();
  void setUserWrapper(UserWrapper userWrapper);

  int getValue();
  void setValue(int value);


}
