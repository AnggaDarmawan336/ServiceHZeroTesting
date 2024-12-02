package com.hand.infra.constant;

public class FileInfoConstant {
    private FileInfoConstant(){
    }

    public static final String FILE_URL = "file_url";

    public static final String VALID_STATUS = "valid_status";

    public static final String VALID_MESSAGE = "valid_message";

    public static class ResponseCode {
        private ResponseCode() {
        }
        public static final String ERROR = "error";

        public static final String INVALID = "invalid";

        public static final String SUCCESS = "success";
    }

    public static class MsgCode {
        private MsgCode() {
        }
        public static final String FILE_NAME_EMPTY = "file name cannot be empty";

        public static final String FILE_EMPTY = "file cannot be empty";

        public static final String BUCKET_NAME_EMPTY = "Bucket Name cannot be empty";

        public static final String ERROR_PDCM_PUB_FILE_INFO_ID_MUST_ENTER = "File Attachment Id!";

        public static final String FILE_URL_NULL = "File Url Cannot Be Empty";

        public static final String UPLOAD_SUCCESS = "upload success";

        public static final String DELETE_SUCCESS = "delete success";
    }
}
