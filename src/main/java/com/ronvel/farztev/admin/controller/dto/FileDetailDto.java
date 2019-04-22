package com.ronvel.farztev.admin.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileDetailDto {
    private String name;
    private String path;
    private boolean isDirectory;
}

