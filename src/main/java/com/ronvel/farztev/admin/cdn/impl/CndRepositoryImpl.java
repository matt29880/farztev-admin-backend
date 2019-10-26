package com.ronvel.farztev.admin.cdn.impl;

import com.ronvel.farztev.admin.cdn.CdnRepository;
import com.ronvel.farztev.admin.cdn.FileDetail;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

// This CDN implementation was used for test purposes
//@Repository
public class CndRepositoryImpl implements CdnRepository {

  @Value("${application.media.photo.location}")
  String mediaFolder;

  @Override
  public List<FileDetail> list(String folderPath) {
    String absolutePath = mediaFolder + folderPath;
    return Stream.of(new File(absolutePath).listFiles())
        .map(file -> mapToFileDetail(folderPath, file))
        .collect(Collectors.toList());
  }

  private FileDetail mapToFileDetail(String parentPath, File file) {
    return FileDetail.builder()
        .name(file.getName())
        .path(createPath(parentPath, file))
        .isDirectory(file.isDirectory())
        .build();
  }

  private String createPath(String parentPath, File file) {
    if (parentPath.equals(File.separator)) {
      return parentPath + file.getName();
    } else {
      return parentPath + File.separator + file.getName();
    }
  }
}
