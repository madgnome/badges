package com.tcs.confluence.plugins.badges.rest;

import com.atlassian.crowd.manager.permission.PermissionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBaseResource
{
  protected final Logger logger = LoggerFactory.getLogger(AbstractBaseResource.class);

  public AbstractBaseResource()
  {
  }
}
