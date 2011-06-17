package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.security.LoginEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.StatisticRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserStatisticDaoService;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;
import org.joda.time.Period;

import java.util.Calendar;
import java.util.Date;

public class RegularRule extends AbstractEventCountRule<LoginEvent>
{
  private static final int CONSECUTIVE_DAYS_THRESHOLD = 5;

  public RegularRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager, IUserStatisticDaoService userStatisticDaoService)
  {
    super(eventPublisher, userManager, achievementManager, userStatisticDaoService);
  }

  @Override
  protected StatisticRefEnum getStatisticRefEnum()
  {
    return StatisticRefEnum.CONSECUTIVE_LOGIN_COUNT;
  }

  @Override
  protected int getThreshold()
  {
    return CONSECUTIVE_DAYS_THRESHOLD;
  }

  @EventListener
  public void check(LoginEvent event)
  {
    innerCheck(event);
  }

  @Override
  protected AchievementRefEnum getAchievementRef()
  {
    return AchievementRefEnum.REGULAR;
  }

  @Override
  protected UserWrapper getUserWrapperForAchievement(LoginEvent event)
  {
    return userManager.get(event.getUsername());
  }

  @Override
  public StatisticAction nextStatisticAction(LoginEvent event)
  {
    UserWrapper userWrapper = getUserWrapperForAchievement(event);
    Date lastLogin = userWrapper.getLastLogin();
    if (lastLogin == null)
    {
      lastLogin = new Date(event.getTimestamp());
    }

    Period period = new Period(lastLogin.getTime(), event.getTimestamp());

    updateLastLogin(event, userWrapper);

    if (period.toStandardDays().getDays() == 1)
    {
      return StatisticAction.INCREMENT;
    }
    else if (period.toStandardDays().getDays() == 0)
    {
      return StatisticAction.NOP;
    }

    return StatisticAction.RESET;
  }

  private void updateLastLogin(LoginEvent event, UserWrapper userWrapper)
  {
    userManager.updateLastLogin(userWrapper, event.getTimestamp());
  }
}
