package com.hand.domain.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hzero.core.util.Regexs;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel("用户信息")
@ModifyAudit
@VersionAudit
@Table(name = "todo_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AuditDomain {
    @Id
    @GeneratedValue
    private Long id;
    @Length(max = 30)
    @NotBlank
    @ApiModelProperty("员工姓名")
    private String employeeName;
    @Length(max = 30)
    @NotBlank
    @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect")
    @ApiModelProperty("员工编号")
    private String employeeNumber;
    @Length(max = 60)
    @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect")
    @ApiModelProperty("员工编号")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Length(max = 30) @NotBlank String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(@Length(max = 30) @NotBlank String employeeName) {
        this.employeeName = employeeName;
    }

    public @Length(max = 30) @NotBlank @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(@Length(max = 30) @NotBlank @Pattern(regexp = Regexs.CODE, message = "htdo.warn.user.numberFormatIncorrect") String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public @Length(max = 60) @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect") String getEmail() {
        return email;
    }

    public void setEmail(@Length(max = 60) @Pattern(regexp = Regexs.EMAIL, message = "htdo.warn.user.emailFormatIncorrect") String email) {
        this.email = email;
    }
}
