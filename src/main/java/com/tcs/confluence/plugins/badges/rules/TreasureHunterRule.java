package com.tcs.confluence.plugins.badges.rules;

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

public class TreasureHunterRule extends AbstractEventCountRule<SearchPerformedEvent>
{
  private static final int SEARCH_THRESHOLD = 5;

  public TreasureHunterRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager, IUserStatisticDaoService userStatisticDaoService)
  {
    super(eventPublisher, userManager, achievementManager, userStatisticDaoService);
  }

  @Override
  protected StatisticRefEnum getStatisticRefEnum()
  {
    return StatisticRefEnum.SEARCH_COUNT;
  }

  @Override
  protected int getThreshold()
  {
    return SEARCH_THRESHOLD;
  }

  @EventListener
  public void check(SearchPerformedEvent event)
  {
    innerCheck(event);
  }

  @Override
  protected AchievementRefEnum getAchievementRef()
  {
    return AchievementRefEnum.TREASURE_HUNTER;
  }

  @Override
  protected UserWrapper getUserWrapperForAchievement(SearchPerformedEvent event)
  {
    return userManager.get(event.getUser());
  }
}
