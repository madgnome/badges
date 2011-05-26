package com.tcs.confluence.plugins.badges.data.services.impl;

import com.atlassian.user.User;
import com.atlassian.user.impl.DefaultUser;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.services.IUserWrapperDaoService;
import net.java.ao.sql.ActiveObjectSqlException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserWrapperDaoServiceTest extends AbstractServiceTest
{
  private IUserWrapperDaoService userWrapperDaoService;

  @Before
  public void setUp() throws Exception
  {
    userWrapperDaoService = new UserWrapperDaoService(createActiveObjects());
  }

  @Test
  public void createShouldCreateAndReturnUserWrapper() throws Exception
  {
    String name = "bob";
    User user = new DefaultUser(name, "Sp onge Bob", "sponge@bob.com");
    UserWrapper userWrapper = userWrapperDaoService.create(user);
    assertNotNull(userWrapper);
    entityManager.flushAll();

    UserWrapper newUserWrapper = userWrapperDaoService.get(name);
    assertNotNull(newUserWrapper);
    assertEquals(name, newUserWrapper.getUserName());
  }

  @Test(expected = ActiveObjectSqlException.class)
  public void shouldNotCreateTwoUserWrapperWithSameName()
  {
    String name = "bob";
    User user = new DefaultUser(name, "Sponge Bob", "sponge@bob.com");
    userWrapperDaoService.create(user);
    entityManager.flushAll();
    
    userWrapperDaoService.create(user);
  }

  @Test
  public void getShouldReturnNullIfAnyStatisticWithRef()
  {
    assertNull(userWrapperDaoService.get("bob"));
  }

  @Test
  public void getShouldReturnUserWrapperWithName()
  {
    String name = "bob";
    User user = new DefaultUser(name, "Sponge Bob", "sponge@bob.com");
    userWrapperDaoService.create(user);
    entityManager.flushAll();

    UserWrapper userWrapper = userWrapperDaoService.get(name);
    assertNotNull(userWrapper);
    assertEquals(name, userWrapper.getUserName());
  }

  @Test
  public void getOrCreateShouldCreateIfAny()
  {
    String name = "bob";
    User user = new DefaultUser(name, "Sponge Bob", "sponge@bob.com");
    assertNull(userWrapperDaoService.get(user));
    UserWrapper userWrapper = userWrapperDaoService.getOrCreate(user);
    assertNotNull(userWrapper);
    entityManager.flushAll();

    assertNotNull(userWrapperDaoService.get(name));
  }

  @Test
  public void getOrCreateShouldReturnIfRefExists()
  {
    String name = "bob";
    User user = new DefaultUser(name, "Sponge Bob", "sponge@bob.com");
    UserWrapper existingWrapper = userWrapperDaoService.create(user);

    UserWrapper userWrapper = userWrapperDaoService.getOrCreate(user);
    assertNotNull(userWrapper);
    assertEquals(existingWrapper, userWrapper);
  }

  @Test
  public void getOrCreateShouldCreateFirstThenReturn()
  {
    String name = "bob";
    User user = new DefaultUser(name, "Sponge Bob", "sponge@bob.com");
    assertNull(userWrapperDaoService.get(user));
    UserWrapper existingUserWrapper = userWrapperDaoService.getOrCreate(user);
    assertNotNull(existingUserWrapper);

    UserWrapper userWrapper = userWrapperDaoService.getOrCreate(user);
    assertNotNull(userWrapper);
    assertEquals(existingUserWrapper, userWrapper);
  }

  @Test
  public void allShouldReturnAllUserWrapper()
  {
    userWrapperDaoService.create(new DefaultUser("bob", "Sponge Bob", "sponge@bob.com"));
    userWrapperDaoService.create(new DefaultUser("patrick", "Star Patrick", "star@patrick.com"));
    userWrapperDaoService.create(new DefaultUser("eugene", "Krabs Eugene", "krabs@eugene.com"));

    List<UserWrapper> userWrappers = userWrapperDaoService.all();
    assertEquals(3, userWrappers.size());
  }
}
