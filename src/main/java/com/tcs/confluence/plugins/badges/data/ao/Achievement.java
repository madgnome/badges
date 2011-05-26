package com.tcs.confluence.plugins.badges.data.ao;

import net.java.ao.Implementation;
import net.java.ao.ManyToMany;
import net.java.ao.schema.Default;
import net.java.ao.schema.Ignore;

@Implementation(AchievementImpl.class)
public interface Achievement extends ReferencableEntity
{
  String getName();
  void setName(String name);

  String getCatchPhrase();
  void setCatchPhrase(String catchPhrase);

  String getDescription();
  void setDescription(String description);

  @Ignore
  String getImageRef();

  @Default("true")
  boolean isActive();
  void setActive(boolean active);

  @ManyToMany(UserAchievement.class)
  UserWrapper[] getUsers();
}
