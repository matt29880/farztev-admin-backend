package com.ronvel.farztev.admin.cdn;

import java.util.List;

public interface CdnRepository {
    List<FileDetail> list(String folderPath);
}
