package com.tcs.confluence.plugins.badges.rules;

import com.atlassian.confluence.event.events.security.LoginEvent;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.services.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.PowerMockUtils;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.Assert;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LoginEvent.class)
public class RegularRuleTest
{
  @Test
  public void shouldNotIncrementIfSameDay() throws Exception
  {
    final Date lastLoginDate = new Date(2011, 06, 12, 3, 12);
    final Date currentLoginDate = new Date(2011, 06, 12, 10, 39);

    UserWrapper userWrapper = mock(UserWrapper.class);
    when(userWrapper.getLastLogin()).thenReturn(lastLoginDate);

    UserManager userManager = mock(UserManager.class);
    final String username = "bob";
    when(userManager.get(username)).thenReturn(userWrapper);
    RegularRule regularRule = new RegularRule(null, userManager, null, null);

    final LoginEvent event = PowerMockito.mock(LoginEvent.class);
    when(event.getTimestamp()).thenReturn(currentLoginDate.getTime());
    when(event.getUsername()).thenReturn(username);

    assertEquals(StatisticAction.NOP ,regularRule.nextStatisticAction(event));
  }

  @Test
  public void shouldIncrementIfNextDay() throws Exception
  {
    final Date lastLoginDate = new Date(2011, 06, 12, 0, 0);
    final Date currentLoginDate = new Date(2011, 06, 13, 23, 59);

    UserWrapper userWrapper = mock(UserWrapper.class);
    when(userWrapper.getLastLogin()).thenReturn(lastLoginDate);

    UserManager userManager = mock(UserManager.class);
    final String username = "bob";
    when(userManager.get(username)).thenReturn(userWrapper);
    RegularRule regularRule = new RegularRule(null, userManager, null, null);

    final LoginEvent event = PowerMockito.mock(LoginEvent.class);
    when(event.getTimestamp()).thenReturn(currentLoginDate.getTime());
    when(event.getUsername()).thenReturn(username);

    assertEquals(StatisticAction.INCREMENT, regularRule.nextStatisticAction(event));
  }

  @Test
  public void shouldNotIncrementIfNotConsecutive() throws Exception
  {
    final Date lastLoginDate = new Date(2011, 06, 12, 0, 0);
    final Date currentLoginDate = new Date(2011, 06, 14, 0, 0);

    UserWrapper userWrapper = mock(UserWrapper.class);
    when(userWrapper.getLastLogin()).thenReturn(lastLoginDate);

    UserManager userManager = mock(UserManager.class);
    final String username = "bob";
    when(userManager.get(username)).thenReturn(userWrapper);
    RegularRule regularRule = new RegularRule(null, userManager, null, null);

    final LoginEvent event = PowerMockito.mock(LoginEvent.class);
    when(event.getTimestamp()).thenReturn(currentLoginDate.getTime());
    when(event.getUsername()).thenReturn(username);

    assertEquals(StatisticAction.RESET, regularRule.nextStatisticAction(event));
  }
}
