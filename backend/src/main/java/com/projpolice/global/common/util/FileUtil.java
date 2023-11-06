package com.projpolice.global.common.util;

import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import com.projpolice.global.common.error.exception.FileException;
import com.projpolice.global.common.error.info.ExceptionInfo;

public class FileUtil {
    /**
     * MultipartFile을 InputStream으로 변환해주는 함수
     * @param file MultipartFile
     * @return InputStream 형태의 파일
     */
    static public InputStream generateStreamFromFile(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            throw new FileException(ExceptionInfo.FAILED_FILE_CONVERSION);
        }
        return inputStream;
    }

    /**
     *  GetObjectResponse Object 를 Resource 로 변환
     *
     * @param getObjectResponse GetObjectResponse
     * @return Resource
     */
    public static Resource toResource(GetObjectResponse getObjectResponse) {
        return new InputStreamResource(getObjectResponse.getInputStream());
    }
}
