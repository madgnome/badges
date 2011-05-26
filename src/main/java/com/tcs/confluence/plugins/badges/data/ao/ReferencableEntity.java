package com.tcs.confluence.plugins.badges.data.ao;

import net.java.ao.Entity;
import net.java.ao.Polymorphic;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.Unique;

@Polymorphic
public interface ReferencableEntity extends Entity
{
  @NotNull
  @Unique
  public String getRef();
  public void setRef(String ref);
}
