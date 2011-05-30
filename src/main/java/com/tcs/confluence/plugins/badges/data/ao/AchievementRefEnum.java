package com.tcs.confluence.plugins.badges.data.ao;

public enum AchievementRefEnum
{
  NEWBIE("newbie"),
  NIGHT_HAWK("night_hawk"),
  TREASURE_HUNTER("treasure_hunter"),
  COMMENT_CANNON("comment_cannon");

  private final String ref;

  AchievementRefEnum(String ref)
  {
    this.ref = ref;
  }

  @Override
  public String toString()
  {
    return ref;
  }
}
