package com.ronvel.farztev.admin.cdn.impl;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import com.ronvel.farztev.admin.cdn.FileDetail;
import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class CdnRepositoryImplTest {

  private CndRepositoryImpl cdnRepository = new CndRepositoryImpl();

  @Before
  public void before() {
    ClassLoader classLoader = getClass().getClassLoader();
    File testResourceFolder = new File(classLoader.getResource("photos").getFile());
    cdnRepository.mediaFolder = testResourceFolder.getAbsolutePath();
  }

  @Test
  public void list_directories() {
    assertEquals(ImmutableList.of(new FileDetail("animals", "/animals", true),
        new FileDetail("japan", "/japan", true)), cdnRepository.list("/"));
  }


  @Test
  public void list_japan() {
    assertEquals(ImmutableList.of(
        new FileDetail("japan.jpg", "/japan/japan.jpg", false),
        new FileDetail("sea", "/japan/sea", true)), cdnRepository.list("/japan"));
  }

  @Test
  public void list_animals() {
    assertEquals(ImmutableList.of(new FileDetail("numbat.jpg", "/animals/numbat.jpg", false)),
        cdnRepository.list("/animals"));
  }


}
