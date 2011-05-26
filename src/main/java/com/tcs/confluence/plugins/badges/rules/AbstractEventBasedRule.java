package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.ConfluenceEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractEventBasedRule<T extends ConfluenceEvent> implements InitializingBean, DisposableBean
{
  private final static Logger logger = LoggerFactory.getLogger(AbstractEventBasedRule.class);

  private final EventPublisher eventPublisher;
  protected final UserManager userManager;
  protected final AchievementManager achievementManager;

  public AbstractEventBasedRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager)
  {
    this.eventPublisher = eventPublisher;
    this.userManager = userManager;
    this.achievementManager = achievementManager;
  }

  protected void addAchievement(T event)
  {
    UserWrapper userWrapper = getUserWrapperForAchievement(event);
    if (userWrapper != null)
    {
      achievementManager.addAchievementToUser(getAchievementRef(), userWrapper);
    }
    else
    {
      logger.warn("Couldn't get the user wrapper for the current event <{}>", event.getClass());
    }
  }

  protected abstract AchievementRefEnum getAchievementRef();

  protected abstract UserWrapper getUserWrapperForAchievement(T event);

  @Override
  public void afterPropertiesSet() throws Exception
  {
    eventPublisher.register(this);
  }

  @Override
  public void destroy() throws Exception
  {
    eventPublisher.unregister(this);
  }
}
