package com.tcs.confluence.plugins.badges.data.bean;

import com.tcs.confluence.plugins.badges.data.ao.Achievement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AchievementBean
{
  private int id;
  private String ref;
  private String name;
  private String imageRef;
  private String catchPhrase;
  private String description;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getRef()
  {
    return ref;
  }

  public void setRef(String ref)
  {
    this.ref = ref;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getImageRef()
  {
    return imageRef;
  }

  public void setImageRef(String imageRef)
  {
    this.imageRef = imageRef;
  }

  public String getCatchPhrase()
  {
    return catchPhrase;
  }

  public void setCatchPhrase(String catchPhrase)
  {
    this.catchPhrase = catchPhrase;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public static AchievementBean fromAchievement(Achievement achievement)
  {
    AchievementBean achievementBean = new AchievementBean();
    achievementBean.setId(achievement.getID());
    achievementBean.setRef(achievement.getRef());
    achievementBean.setName(achievement.getName());
    achievementBean.setImageRef(achievement.getImageRef());
    achievementBean.setCatchPhrase(achievement.getCatchPhrase());
    achievementBean.setDescription(achievement.getDescription());

    return achievementBean;
  }
}