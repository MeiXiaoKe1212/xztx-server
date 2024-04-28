package cn.iocoder.yudao.module.xztx.api.dto;

import lombok.Data;

import java.util.List;
@Data
public class CompanyForCreateDTO {

    private String name;

    private String contactPhone;

    private String contactPerson;

    private String contactEmail;

    private List<Integer> locationList;

    private String scale;

    private Integer status;

    private Long userId;

}
