package com.hand.app.service;

import com.google.common.collect.Maps;
import com.hand.api.controller.DTO.PubFileInfoDTO;
import feign.Response;
import org.hzero.boot.file.dto.FileDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    Map<String, List<FileDTO>> fileInfoByUUID(Long organizationId, PubFileInfoDTO pubFileInfoDTO);

    Map<String, Object> uploadFile(Long organizationId, PubFileInfoDTO pubFileInfoDTO);

    HttpServletResponse downloadAttachmentFile(Long organizationId,
                                               PubFileInfoDTO pubFileInfoDTO,
                                               HttpServletResponse httpServletResponse) throws IOException;

    Map<String, String> deleteFileByUrl(Long organizationId, PubFileInfoDTO pubFileInfoDTO);
}
