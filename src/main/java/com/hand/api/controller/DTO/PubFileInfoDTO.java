package com.hand.api.controller.DTO;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

public class PubFileInfoDTO {

    @ApiModelProperty(value = "Bucket Name")
    private String bucketName;

    @ApiModelProperty(value = "File URL")
    private String fileUrl;

    @ApiModelProperty(value = "Attachment UUID")
    private String attachmentUuid;

    @ApiModelProperty(value = "File Directory")
    private String fileDirectory;

    @ApiModelProperty(value = "File Name")
    private String fileName;

    @ApiModelProperty(value = "File Type")
    private String fileType;

    @ApiModelProperty(value = "Storage Code")
    private String storageCode;

    @ApiModelProperty(value = "File Source")
    private MultipartFile multipartFile;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getAttachmentUuid() {
        return attachmentUuid;
    }

    public void setAttachmentUuid(String attachmentUuid) {
        this.attachmentUuid = attachmentUuid;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
