package com.tcs.confluence.plugins.badges.ui.actions;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.web.model.WebPanel;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.tcs.confluence.plugins.badges.data.ao.Achievement;
import com.tcs.confluence.plugins.badges.data.ao.UserWrapper;
import com.tcs.confluence.plugins.badges.services.AchievementManager;
import com.tcs.confluence.plugins.badges.services.UserManager;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

public class ProfileBadgesPanel implements WebPanel
{
  private final TemplateRenderer templateRenderer;
  private final UserManager userManager;
  private final AchievementManager achievementManager;
  private final SettingsManager settingsManager;
  private final WebResourceManager webResourceManager;

  public ProfileBadgesPanel(TemplateRenderer templateRenderer, UserManager userManager, AchievementManager achievementManager, SettingsManager settingsManager, WebResourceManager webResourceManager)
  {
    this.templateRenderer = templateRenderer;
    this.userManager = userManager;
    this.achievementManager = achievementManager;
    this.settingsManager = settingsManager;
    this.webResourceManager = webResourceManager;
  }

  @Override
  public String getHtml(Map<String, Object> params)
  {
    final StringWriter writer = new StringWriter();
    try
    {
      params.put("baseUrl", settingsManager.getGlobalSettings().getBaseUrl());

      UserWrapper userWrapper = userManager.getCurrentUserWrapper();
      List<Achievement> achievements = achievementManager.allAchievements();
      params.put("userWrapper", userWrapper);
      params.put("achievements", achievements);

      webResourceManager.requireResource("com.tcs.confluence.plugins.badges:user-achievements-details");
      templateRenderer.render("/templates/badges/profilebadges.vm", params, writer);
    }
    catch (IOException e)
    {
    }

    return writer.toString();
  }
}
