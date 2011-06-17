package com.tcs.confluence.plugins.badges.data.ao;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;
import net.java.ao.schema.Default;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.Unique;

import java.util.Date;

public interface UserWrapper extends Entity
{
  @Unique
  @NotNull
  String getUserName();
  void setUserName(String name);

  @ManyToMany(value = UserAchievement.class, where = "NOTIFIED = FALSE")
  Achievement[] getNewAchievements();

  @ManyToMany(value = UserAchievement.class)
  Achievement[] getAchievements();

  @Default("true")
  boolean isActive();
  void setActive(boolean active);

  Date getLastLogin();
  void setLastLogin(Date loginDate);
}
