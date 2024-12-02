package com.hand.api.controller.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Component
public class WorkFlowClientDTO {
    private Long organizationId;
    private String flowKey;
    private String businessKey;
    private String dimension;
    private String starter;
    private Map<String, Object> variableMap;
    private Long paymentAmount;

    public Long getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public WorkFlowClientDTO() {
    }

    @JsonCreator
    public WorkFlowClientDTO(
            @JsonProperty("organizationId") Long organizationId,
            @JsonProperty("flowKey") String flowKey,
            @JsonProperty("businessKey") String businessKey,
            @JsonProperty("dimension") String dimension,
            @JsonProperty("starter") String starter,
            @JsonProperty("variableMap") Map<String, Object> variableMap,
            @JsonProperty("paymentAmount") Long paymentAmount) {
        this.organizationId = organizationId;
        this.flowKey = flowKey;
        this.businessKey = businessKey;
        this.dimension = dimension;
        this.starter = starter;
        this.variableMap = variableMap;
        this.paymentAmount = paymentAmount;
    }


    public WorkFlowClientDTO(Map<String, Object> variableMap) {
        this.variableMap = variableMap;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public void setVariableMap(Map<String, Object> variableMap) {
        this.variableMap = variableMap;
    }

    public Map<String, Object> getVariableMap() {
        return variableMap;
    }
}
