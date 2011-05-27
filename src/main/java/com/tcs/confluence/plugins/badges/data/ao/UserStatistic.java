package com.tcs.confluence.plugins.badges.data.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Default;

public interface UserStatistic extends Entity
{
  Statistic getStatistic();
  void setStatistic(Statistic statistic);

  UserWrapper getUserWrapper();
  void setUserWrapper(UserWrapper userWrapper);

  @Default("0")
  int getValue();
  void setValue(int value);


}
