package cn.iocoder.yudao.module.xztx.dal.mysql.company;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.xztx.dal.dataobject.company.XztxCompanyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.xztx.controller.admin.company.vo.*;

/**
 * 携智同行 企业 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface XztxCompanyMapper extends BaseMapperX<XztxCompanyDO> {

    default PageResult<XztxCompanyDO> selectPage(XztxCompanyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XztxCompanyDO>()
                .likeIfPresent(XztxCompanyDO::getName, reqVO.getName())
                .eqIfPresent(XztxCompanyDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(XztxCompanyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(XztxCompanyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(XztxCompanyDO::getId));
    }

}