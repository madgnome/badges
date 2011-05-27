package com.tcs.confluence.plugins.badges.data;

import com.tcs.confluence.plugins.badges.data.ao.*;
import net.java.ao.EntityManager;
import net.java.ao.test.jdbc.DatabaseUpdater;

public class DatabaseProcessor implements DatabaseUpdater
{
  @Override
  @SuppressWarnings({"unchecked"})
  public void update(EntityManager entityManager) throws Exception
  {
    entityManager.migrate(
            Achievement.class,
            Statistic.class,
            UserAchievement.class,
            UserStatistic.class,
            UserWrapper.class);
  }
}
