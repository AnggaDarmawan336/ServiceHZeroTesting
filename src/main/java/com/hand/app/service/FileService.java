package com.hand.app.service;

import feign.Response;
import org.hzero.boot.file.dto.FileDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    void uploadAttachment(
            Long organizationId,
            String bucketName,
            String directory,
            String storageCode,
            String fileName,
            MultipartFile file
    );

    List<FileDTO> getAllFIles(Long organizationId, String bucketName, String uuid);

    InputStreamResource downloadFile(Long organizationId, String fileKey);

    void deleteFile(Long organizationId,
                    String bucketName,
                    List<String> atttachmentUUID,
                    List<String> url
    );

    Response watermarkByUrl(Long organizationId, String bucketName, String storageCode, String url, String watermarkCode);
}