package cn.iocoder.yudao.module.xztx.dal.mysql.job;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.xztx.controller.admin.job.vo.*;

/**
 * 招聘岗位 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface XztxJobMapper extends BaseMapperX<XztxJobDO> {

    default PageResult<XztxJobDO> selectPage(JobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<XztxJobDO>()
                .likeIfPresent(XztxJobDO::getJobName, reqVO.getJobName())
                .eqIfPresent(XztxJobDO::getExp, reqVO.getExp())
                .eqIfPresent(XztxJobDO::getJobStatus, reqVO.getJobStatus())
                .betweenIfPresent(XztxJobDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(XztxJobDO::getId));
    }

}