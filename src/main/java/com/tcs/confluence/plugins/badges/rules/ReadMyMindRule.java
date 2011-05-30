package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.search.SearchPerformedEvent;
import com.atlassian.confluence.event.events.userstatus.StatusCreateEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserStatisticDaoService;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;

public class ReadMyMindRule extends AbstractEventCountRule<StatusCreateEvent>
{
  private static final int STATUS_UPDATE_THRESHOLD = 5;

  public ReadMyMindRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager, IUserStatisticDaoService userStatisticDaoService)
  {
    super(eventPublisher, userManager, achievementManager, userStatisticDaoService);
  }

  @Override
  protected StatisticRefEnum getStatisticRefEnum()
  {
    return StatisticRefEnum.STATUS_UPDATE_COUNT;
  }

  @Override
  protected int getThreshold()
  {
    return STATUS_UPDATE_THRESHOLD ;
  }

  @EventListener
  public void check(StatusCreateEvent event)
  {
    innerCheck(event);
  }

  @Override
  protected AchievementRefEnum getAchievementRef()
  {
    return AchievementRefEnum.READ_MY_MIND;
  }

  @Override
  protected UserWrapper getUserWrapperForAchievement(StatusCreateEvent event)
  {
    return userManager.get(event.getUserStatus().getCreatorName());
  }
}
