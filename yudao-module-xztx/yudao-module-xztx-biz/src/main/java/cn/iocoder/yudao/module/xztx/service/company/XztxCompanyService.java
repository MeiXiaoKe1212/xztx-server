package cn.iocoder.yudao.module.xztx.service.company;

import java.util.*;

import cn.iocoder.yudao.module.xztx.controller.admin.company.vo.*;
import cn.iocoder.yudao.module.xztx.dal.dataobject.company.XztxCompanyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.validation.Valid;

/**
 * 携智同行 企业 Service 接口
 *
 * @author 芋道源码
 */
public interface XztxCompanyService {

    /**
     * 创建携智同行 企业
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompany(@Valid XztxCompanySaveReqVO createReqVO);

    /**
     * 更新携智同行 企业
     *
     * @param updateReqVO 更新信息
     */
    void updateCompany(@Valid XztxCompanySaveReqVO updateReqVO);

    /**
     * 删除携智同行 企业
     *
     * @param id 编号
     */
    void deleteCompany(Long id);

    /**
     * 获得携智同行 企业
     *
     * @param id 编号
     * @return 携智同行 企业
     */
    XztxCompanyDO getCompany(Long id);

    /**
     * 获得携智同行 企业分页
     *
     * @param pageReqVO 分页查询
     * @return 携智同行 企业分页
     */
    PageResult<XztxCompanyDO> getCompanyPage(XztxCompanyPageReqVO pageReqVO);

    /**
     * 根据联系人手机号查询企业
     *
     * @param phone 手机号
     * @return XztxCompanyDO
     */
    XztxCompanyDO getCompanyByPhone(String phone);


}