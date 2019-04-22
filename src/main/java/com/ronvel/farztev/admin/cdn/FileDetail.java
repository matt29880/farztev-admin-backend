package com.ronvel.farztev.admin.cdn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDetail {
    private String name;
    private String path;
    private boolean isDirectory;
}
