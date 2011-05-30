package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.content.comment.CommentCreateEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserStatisticDaoService;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;

public class CommentCannonRule extends AbstractEventCountRule<CommentCreateEvent>
{
  private static final int COMMENT_THRESHOLD = 50;

  public CommentCannonRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager, IUserStatisticDaoService userStatisticDaoService)
  {
    super(eventPublisher, userManager, achievementManager, userStatisticDaoService);
  }

  @EventListener
  public void check(CommentCreateEvent event)
  {
    innerCheck(event);
  }

  @Override
  protected StatisticRefEnum getStatisticRefEnum()
  {
    return StatisticRefEnum.COMMENT_COUNT;
  }

  @Override
  protected int getThreshold()
  {
    return COMMENT_THRESHOLD;
  }

  @Override
  protected AchievementRefEnum getAchievementRef()
  {
    return AchievementRefEnum.COMMENT_CANNON;
  }

  @Override
  protected UserWrapper getUserWrapperForAchievement(CommentCreateEvent event)
  {
    return userManager.get(event.getComment().getCreatorName());
  }
}
