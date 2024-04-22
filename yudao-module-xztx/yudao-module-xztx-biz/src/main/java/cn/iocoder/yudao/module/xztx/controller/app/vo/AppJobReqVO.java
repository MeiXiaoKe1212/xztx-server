package cn.iocoder.yudao.module.xztx.controller.app.vo;

/**
 * app端 Job条件查询
 */
public class AppJobReqVO {

    /**
     * 工作类型（例：1全职、2兼职、3实习）
     */
    private Integer jobType;

    /**
     * 行业类别（传入Number）
     */
    private Integer jobIndustryType;

    /**
     * 省 id
     */
    private String provinceId;

    /**
     * 市 id
     */
    private String cityId;

    /**
     * 县/区 id
     */
    private String threeCityId;

    /**
     * 是否招聘应届生
     */
    private char isCollegeStudent;

    /**
     * 学历
     */
    private String edu;


    /**
     * 残疾类别id字符串
     */
    private String disTypeIds;
}
