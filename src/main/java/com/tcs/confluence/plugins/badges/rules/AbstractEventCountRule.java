package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.ConfluenceEvent;
import com.atlassian.confluence.event.events.search.SearchPerformedEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserStatistic;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserStatisticDaoService;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;

public abstract class AbstractEventCountRule<T extends ConfluenceEvent> extends AbstractEventBasedRule<T>
{
  private final IUserStatisticDaoService userStatisticDaoService;

  public AbstractEventCountRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager, IUserStatisticDaoService userStatisticDaoService)
  {
    super(eventPublisher, userManager, achievementManager);
    this.userStatisticDaoService = userStatisticDaoService;
  }

  protected void innerCheck(T event)
  {
    UserWrapper userWrapper = getUserWrapperForAchievement(event);
    UserStatistic userStatistic = userStatisticDaoService.incrementAndGet(userWrapper, getStatisticRefEnum());
    int searchCount = userStatistic.getValue();

    if (searchCount == getThreshold()) // We use the equality to give the badge only one time.
    {
      addAchievement(event);
    }
  }

  protected abstract StatisticRefEnum getStatisticRefEnum();
  protected abstract int getThreshold();
}
