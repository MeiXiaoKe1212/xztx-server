package cn.iocoder.yudao.module.xztx.service.postlog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogPageReqVO;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogSaveReqVO;
import cn.iocoder.yudao.module.xztx.controller.app.vo.AppPostLogFullVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.postlog.PostLogDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 投递记录 Service 接口
 *
 * @author 芋道源码
 */
public interface PostLogService {

    /**
     * 创建投递记录
     *
     * @param jobId 投递的岗位id
     * @return 编号
     */
    Long createPostLog(Long jobId);

    /**
     * 更新投递记录
     *
     * @param updateReqVO 更新信息
     */
    void updatePostLog(@Valid PostLogSaveReqVO updateReqVO);

    /**
     * 删除投递记录
     *
     * @param id 编号
     */
    void deletePostLog(Long id);

    /**
     * 判断是否投递过该岗位
     *
     * @param jobId  岗位id
     * @param userId 用户id
     * @return 是否投递过
     */
    boolean hasPosted(Long jobId, Long userId);


    /**
     * 获得投递记录列表
     *
     * @return 投递记录列表
     */
    List<AppPostLogFullVO> getPostLogList();

    /**
     * 获得投递记录
     *
     * @param id 编号
     * @return 投递记录
     */
    PostLogDO getPostLog(Long id);

    /**
     * 获得投递记录分页
     *
     * @param pageReqVO 分页查询
     * @return 投递记录分页
     */
    PageResult<PostLogDO> getPostLogPage(PostLogPageReqVO pageReqVO);

}