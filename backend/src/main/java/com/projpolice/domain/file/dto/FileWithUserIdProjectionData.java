package com.projpolice.domain.file.dto;

import com.projpolice.domain.file.domain.File;

// not used yet
public interface FileWithUserIdProjectionData {

    File getFile();

    Long getUserId();
}
