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

public class TreasureHunterRule extends AbstractEventBasedRule<SearchPerformedEvent>
{
  private IUserStatisticDaoService statisticDaoService;

  public TreasureHunterRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager)
  {
    super(eventPublisher, userManager, achievementManager);
  }

  @EventListener
  public void check(SearchPerformedEvent event)
  {
    UserWrapper userWrapper = getUserWrapperForAchievement(event);
    UserStatistic userStatistic = statisticDaoService.get(userWrapper, StatisticRefEnum.SEARCH_COUNT);
    int searchCount = userStatistic.getValue();
    if (searchCount == 5)
    {
      addAchievement(event);
    }

    userStatistic.setValue(++searchCount);
    userStatistic.save();
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
