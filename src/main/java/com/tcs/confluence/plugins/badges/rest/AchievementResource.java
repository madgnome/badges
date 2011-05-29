package com.tcs.confluence.plugins.badges.rest;

import com.atlassian.confluence.user.AuthenticatedUserThreadLocal;
import com.atlassian.user.User;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.UserAchievement;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.data.bean.AchievementBean;
import com.tcs.confluence.plugins.badges.data.services.IUserAchievementDaoService;
import com.tcs.confluence.plugins.badges.data.services.IUserWrapperDaoService;
import com.tcs.confluence.plugins.badges.services.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/achievements")
public class AchievementResource extends AbstractBaseResource
{
  private final UserManager userManager;
  private final IUserAchievementDaoService userAchievementDaoService;

  public AchievementResource(IUserAchievementDaoService userAchievementDaoService, UserManager userManager)
  {
    super();
    this.userAchievementDaoService = userAchievementDaoService;
    this.userManager = userManager;
  }

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public Response getUserAchievements()
  {
    UserWrapper userWrapper = userManager.getCurrentUserWrapper();

    List<AchievementBean> achievements = new ArrayList<AchievementBean>();

    // TODO Replace userWrapper.getAchievements() by userWrapper.getNewAchievements()
    for (Achievement achievement : userWrapper.getNewAchievements())
    {
      achievements.add(AchievementBean.fromAchievement(achievement));
    }

    return Response.ok(achievements).build();
  }

  @PUT
  @Path("{id}")
  public Response updateUserAchievementStatus(@PathParam("id") int achievementId,
                                              @FormParam("notified") boolean notified)
  {
    UserWrapper userWrapper = userManager.getCurrentUserWrapper();

    userAchievementDaoService.setNotified(achievementId, userWrapper, notified);

    return Response.ok().build();
  }

}
