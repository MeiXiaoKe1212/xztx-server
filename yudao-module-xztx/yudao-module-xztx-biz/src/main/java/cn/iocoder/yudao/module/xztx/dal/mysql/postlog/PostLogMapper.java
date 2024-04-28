package cn.iocoder.yudao.module.xztx.dal.mysql.postlog;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.xztx.dal.dataobject.postlog.PostLogDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.*;

/**
 * 投递记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PostLogMapper extends BaseMapperX<PostLogDO> {

    default PageResult<PostLogDO> selectPage(PostLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PostLogDO>()
                .eqIfPresent(PostLogDO::getContactUserId, reqVO.getCompanyUserId())
                .eqIfPresent(PostLogDO::getResumeId, reqVO.getResumeId())
                .eqIfPresent(PostLogDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PostLogDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PostLogDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PostLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PostLogDO::getId));
    }

}