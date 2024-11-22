package com.hand.app.service.impl;

import com.hand.app.service.FileService;
import feign.Response;
import org.hzero.boot.file.FileClient;
import org.hzero.boot.file.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileClient fileClient;

    @Override
    public void uploadAttachment(Long organizationId,
                             String bucketName,
                             String directory,
                             String storageCode,
                             String fileName,
                             MultipartFile file) {
        try {
            String fileNameUpload = (fileName != null && !fileName.isEmpty()) ? fileName : file.getOriginalFilename();

            String fileType = file.getContentType();
            byte[] fileData = file.getBytes();

            String uuid = UUID.randomUUID().toString().substring(0,31);

            fileClient.uploadAttachment(organizationId, bucketName, directory, uuid, fileNameUpload, fileType, storageCode, fileData);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<FileDTO> getAllFIles(Long organizationId, String bucketName, String uuid) {
        return fileClient.getAttachmentFiles(organizationId, bucketName, uuid);
    }

    @Override
    public InputStreamResource downloadFile(Long organizationId, String fileKey) {
        try {
            InputStream fileInputStream = fileClient.downloadFile(organizationId, fileKey);
            return new InputStreamResource(fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteFile(Long oraganizationId,
                           String bucketName,
                           List<String> attachmentUUID,
                           List<String> url)
    {
        fileClient.deleteFile(oraganizationId, bucketName, attachmentUUID);
    }

    @Override
    public Response watermarkByUrl(Long organizationId, String bucketName, String storageCode, String url, String watermarkCode) {
        return fileClient.watermarkByKey(organizationId, bucketName, storageCode, url, Long.parseLong(watermarkCode));
    }
}
