package com.ronvel.farztev.admin.cdn;

import java.io.IOException;
import java.util.List;

public interface CdnRepository {
    List<FileDetail> list(String folderPath) throws IOException;
}
