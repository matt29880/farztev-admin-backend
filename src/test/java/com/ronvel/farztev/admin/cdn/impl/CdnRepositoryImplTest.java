package com.ronvel.farztev.admin.cdn.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.ronvel.farztev.admin.cdn.FileDetail;

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
	List<FileDetail> fileDetailsList = cdnRepository.list("/");
    assertEquals(List.of(
    		new FileDetail("animals", "/animals", true),
    		new FileDetail("japan", "/japan", true),
    		new FileDetail("no-image.jpg", "/no-image.jpg", false)), fileDetailsList);
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
