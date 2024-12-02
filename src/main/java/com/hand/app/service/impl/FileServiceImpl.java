package com.hand.app.service.impl;

import com.hand.api.controller.DTO.PubFileInfoDTO;
import com.hand.app.service.FileService;
import com.hand.infra.constant.FileInfoConstant;
import feign.Response;
import io.choerodon.core.exception.CommonException;
import org.hzero.boot.file.FileClient;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.starter.fileview.constant.FileConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Map<String, List<FileDTO>> fileInfoByUUID(Long organizationId, PubFileInfoDTO pubFileInfoDTO) {
        return fileClient.getAttachmentFiles(organizationId, pubFileInfoDTO.getBucketName(), pubFileInfoDTO.getAttachmentUuid())
                .stream().collect(Collectors.groupingBy(FileDTO::getStorageCode));
    }

    @Override
    public Map<String, Object> uploadFile(Long organizationId, PubFileInfoDTO pubFileInfoDTO) {
        Map<String, Object> responseMap = new HashMap<>(2);
        if (pubFileInfoDTO == null) {
            responseMap.put(FileInfoConstant.VALID_STATUS, FileInfoConstant.ResponseCode.INVALID);
            return responseMap;
        }
        // Valid Data
        if (pubFileInfoDTO.getFileName() == null ){
            responseMap.put(FileInfoConstant.VALID_STATUS, FileInfoConstant.ResponseCode.ERROR);
            responseMap.put(FileInfoConstant.VALID_MESSAGE, FileInfoConstant.MsgCode.FILE_NAME_EMPTY);
        }
        if (pubFileInfoDTO.getMultipartFile() == null) {
            responseMap.put(FileInfoConstant.VALID_STATUS, FileInfoConstant.ResponseCode.ERROR);
            responseMap.put(FileInfoConstant.VALID_MESSAGE, FileInfoConstant.MsgCode.BUCKET_NAME_EMPTY);
        }
        if (FileInfoConstant.ResponseCode.ERROR.equals(responseMap.get(FileInfoConstant.VALID_STATUS))) {
            return responseMap;
        }

        String url = null;
        try {
            url = fileClient.uploadFile(organizationId,
                    pubFileInfoDTO.getBucketName(),
                    pubFileInfoDTO.getFileDirectory(),
                    pubFileInfoDTO.getFileName(),
                    pubFileInfoDTO.getFileType(),
                    pubFileInfoDTO.getStorageCode(),
                    pubFileInfoDTO.getMultipartFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseMap.put(FileInfoConstant.FILE_URL, url);
        responseMap.put(FileInfoConstant.VALID_STATUS, FileInfoConstant.ResponseCode.SUCCESS);
        responseMap.put(FileInfoConstant.VALID_MESSAGE, FileInfoConstant.MsgCode.UPLOAD_SUCCESS);
        return responseMap;
    }

    @Override
    public HttpServletResponse downloadAttachmentFile(Long organizationId, PubFileInfoDTO pubFileInfoDTO, HttpServletResponse httpServletResponse) throws IOException {
        // VALID DATA
        if (pubFileInfoDTO == null) {
            return null;
        }
        if (pubFileInfoDTO.getBucketName() == null) {
            throw new CommonException(FileInfoConstant.MsgCode.BUCKET_NAME_EMPTY);
        }
        if (pubFileInfoDTO.getFileUrl() == null) {
            throw new CommonException(FileInfoConstant.MsgCode.FILE_URL_NULL);
        }

        InputStream inputStream = fileClient.downloadFile(organizationId,
                pubFileInfoDTO.getBucketName(),
                pubFileInfoDTO.getFileUrl());

        try (OutputStream outputStream = httpServletResponse.getOutputStream()) {
            Throwable var5 = null;
            try {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
            } catch (Throwable var15) {
                var5 = var15;
                throw var15;
            } finally {
                if (outputStream != null) {
                    if (var5 != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable var14) {
                            var5.addSuppressed(var14);
                        }
                    } else {
                        outputStream.close();
                    }
                }
            }
        }
        return httpServletResponse;
    }

    @Override
    public Map<String, String> deleteFileByUrl(Long organizationId, PubFileInfoDTO pubFileInfoDTO) {
        Map<String, String> responseMap = new HashMap<>(2);
        // Valid Data
        if (pubFileInfoDTO == null) {
            responseMap.put(FileInfoConstant.VALID_STATUS, FileInfoConstant.ResponseCode.INVALID);
            return responseMap;
        }
        if (pubFileInfoDTO.getBucketName() == null) {
            throw new CommonException(FileInfoConstant.MsgCode.BUCKET_NAME_EMPTY);
        }
        if (pubFileInfoDTO.getFileUrl() == null) {
            throw new CommonException(FileInfoConstant.MsgCode.FILE_URL_NULL);
        }
        fileClient.deleteFileByUrl(organizationId, pubFileInfoDTO.getBucketName(), Collections.singletonList(pubFileInfoDTO.getFileUrl()));
        responseMap.put(FileInfoConstant.VALID_STATUS, FileInfoConstant.ResponseCode.SUCCESS);
        responseMap.put(FileInfoConstant.VALID_MESSAGE, FileInfoConstant.MsgCode.DELETE_SUCCESS);
        return responseMap;
    }
}
