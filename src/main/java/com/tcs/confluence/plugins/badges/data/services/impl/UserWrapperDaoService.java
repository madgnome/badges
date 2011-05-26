package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.user.User;
import com.google.common.collect.ImmutableMap;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserWrapperDaoService;

public class UserWrapperDaoService extends BaseDaoService<UserWrapper> implements IUserWrapperDaoService
{
  @Override
  protected Class<UserWrapper> getClazz()
  {
    return UserWrapper.class;
  }

  public UserWrapperDaoService(ActiveObjects ao)
  {
    super(ao);
  }

  @Override
  public UserWrapper getOrCreate(User confluenceUser)
  {
    return getOrCreate(confluenceUser.getName());
  }

  @Override
  public UserWrapper getOrCreate(String confluenceUserName)
  {
    UserWrapper userWrapper = get(confluenceUserName);

    return userWrapper == null ? create(confluenceUserName) : userWrapper;
  }

  @Override
  public UserWrapper create(User confluenceUser)
  {
    return create(confluenceUser.getName());
  }

  @Override
  public UserWrapper create(String confluenceUserName)
  {
    return ao.create(UserWrapper.class, ImmutableMap.<String, Object>of("USER_NAME", confluenceUserName));
  }

  @Override
  public UserWrapper get(User confluenceUser)
  {
    return get(confluenceUser.getName());
  }

  @Override
  public UserWrapper get(String jiraUserName)
  {
    UserWrapper[] userWrappers = ao.find(UserWrapper.class, "USER_NAME = ?", jiraUserName);
    return userWrappers.length > 0 ? userWrappers[0] : null;
  }
}
