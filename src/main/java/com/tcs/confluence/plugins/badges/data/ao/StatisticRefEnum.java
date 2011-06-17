package com.tcs.confluence.plugins.badges.data.ao;

public enum StatisticRefEnum
{
  SEARCH_COUNT("search_count"),
  COMMENT_COUNT("comment_count"),
  STATUS_UPDATE_COUNT("status_count"),
  CONSECUTIVE_LOGIN_COUNT("consecutive_login_count");

  private final String ref;

  StatisticRefEnum(String ref)
  {
    this.ref = ref;
  }

  @Override
  public String toString()
  {
    return ref;
  }
}
