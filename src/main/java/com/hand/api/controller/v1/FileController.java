package com.hand.api.controller.v1;


import com.hand.app.service.FileService;
import com.hand.config.SwaggerTags;
import feign.Response;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.core.util.Results;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Api(tags = SwaggerTags.FILE)
@RestController("FileController.v1")
@RequestMapping("/v1/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "File Sent")
    @PostMapping
    public ResponseEntity<String> uplioadFile(
            @RequestParam Long organizationId,
            @RequestParam String bucketName,
            @RequestParam String directory,
            @RequestParam String storageCode,
            @RequestParam("file")MultipartFile file,
            @RequestParam(value = "fileName", required = false) String fileName
            ) {


        try {
            fileService.uploadAttachment(organizationId, bucketName, directory, storageCode, fileName, file);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("File upload failed: " + e.getMessage());
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "File Get")
    @GetMapping
    public ResponseEntity<?> getFile(
            @RequestParam Long oraganizationId,
            @RequestParam String bucketName,
            @RequestParam String attachmentUUID
    ){
        try {
            List<FileDTO> files = fileService.getAllFIles(oraganizationId, bucketName, attachmentUUID);
            return Results.success(files);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "File Download")
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam Long organizationId,
            @RequestParam String fileKey) {
       try {
           InputStreamResource fileInputStream = fileService.downloadFile(organizationId, fileKey);
           return ResponseEntity.ok()
                   .body(fileInputStream);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }


    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "File Delete")
    @DeleteMapping
    public ResponseEntity<?> deleteFile(
            @RequestParam Long oraganizationId,
            @RequestParam String bucketName,
            @RequestParam String attachmentUUID,
            @RequestParam String url
    ){
        try {
            fileService.deleteFile(oraganizationId,
                    bucketName,
                    Collections.singletonList(attachmentUUID),
                    Collections.singletonList(url));
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Permission(level = ResourceLevel.SITE)
    @ApiOperation(value = "File Watermark")
    @GetMapping("/watermark")
    public Response watermarkFile(
            @RequestParam Long oraganizationId,
            @RequestParam String bucketName,
            @RequestParam String attachmentUUID,
            @RequestParam String url
    ){
        try {
            return fileService.watermarkByUrl(oraganizationId, bucketName, attachmentUUID, url, UUID.randomUUID().toString());
        } catch (Exception e) {
            return null;
        }
    }
}

