package com.tcs.confluence.plugins.badges.data.services;

import com.atlassian.activeobjects.tx.Transactional;
import net.java.ao.Entity;

import java.util.List;

@Transactional
public interface IDaoService<T extends Entity>
{
  T get(int id);
  List<T> all();
}
