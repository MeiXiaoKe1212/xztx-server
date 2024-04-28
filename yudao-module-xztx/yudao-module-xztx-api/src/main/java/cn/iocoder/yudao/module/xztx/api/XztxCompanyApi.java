package cn.iocoder.yudao.module.xztx.api;

import cn.iocoder.yudao.module.xztx.api.dto.CompanyForCreateDTO;

public interface XztxCompanyApi {

    /**
     * 根据手机号查询是否存在
     *
     * @param phone
     * @return
     */
    boolean existAccount(String phone);

    /**
     * 创建company
     *
     * @param dto
     * @return
     */
    Long createCompany(CompanyForCreateDTO dto);
}
