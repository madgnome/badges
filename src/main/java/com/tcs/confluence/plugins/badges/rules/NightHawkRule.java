package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.ConfluenceEvent;
import com.atlassian.confluence.event.events.security.LoginEvent;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.tcs.confluence.plugins.badges.data.ao.AchievementRefEnum;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;

import java.util.Calendar;
import java.util.Date;

public class NightHawkRule extends AbstractEventBasedRule<LoginEvent>
{
  public NightHawkRule(EventPublisher eventPublisher, UserManager userManager, AchievementManager achievementManager)
  {
    super(eventPublisher, userManager, achievementManager);
  }

  @EventListener
  public void check(LoginEvent loginEvent)
  {
    Date date = new Date(loginEvent.getTimestamp());
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    if (calendar.get(Calendar.HOUR) < 100)
    {
//      addAchievement(loginEvent);
    }
  }

  @Override
  protected AchievementRefEnum getAchievementRef()
  {
    return AchievementRefEnum.NIGHT_HAWK;
  }

  @Override
  protected UserWrapper getUserWrapperForAchievement(LoginEvent event)
  {
    return userManager.get(event.getUsername());
  }
}
