package com.hand.infra.constant;

public class WorkFlowConstant {
    private WorkFlowConstant(){

    }

    public static class DocStatus{
        public static final String COMPLETED = "COMPLETED";

        private DocStatus(){}
    }

    public static final String DRAFT = "draft";
    public static final String PROCESSING = "processing";
    public static final String APPROVED = "approved";
    public static final String REJECTED = "rejected";
}
