package com.tcs.confluence.plugins.badges.data.services;

import com.atlassian.activeobjects.tx.Transactional;
import com.atlassian.user.User;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;

@Transactional
public interface IUserWrapperDaoService extends IDaoService<UserWrapper>
{
  UserWrapper create(User confluenceUser);
  UserWrapper create(String confluenceUserName);
  UserWrapper getOrCreate(User confluenceUser);
  UserWrapper getOrCreate(String confluenceUserName);
  UserWrapper get(User confluenceUser);
  UserWrapper get(String confluenceUserName);
}
