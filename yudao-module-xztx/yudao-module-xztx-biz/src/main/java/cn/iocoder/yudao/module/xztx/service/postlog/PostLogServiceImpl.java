package cn.iocoder.yudao.module.xztx.service.postlog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.member.api.user.MemberUserApi;
import cn.iocoder.yudao.module.member.api.user.dto.MemberUserRespDTO;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogPageReqVO;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogSaveReqVO;
import cn.iocoder.yudao.module.xztx.controller.app.vo.AppPostLogFullVO;
import cn.iocoder.yudao.module.xztx.controller.app.vo.MemberUserInfoVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.company.XztxCompanyDO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.postlog.PostLogDO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.resume.XztxResumeDO;
import cn.iocoder.yudao.module.xztx.dal.mysql.company.XztxCompanyMapper;
import cn.iocoder.yudao.module.xztx.dal.mysql.job.XztxJobMapper;
import cn.iocoder.yudao.module.xztx.dal.mysql.postlog.PostLogMapper;
import cn.iocoder.yudao.module.xztx.dal.mysql.resume.XztxResumeMapper;
import cn.iocoder.yudao.module.xztx.service.company.XztxCompanyService;
import cn.iocoder.yudao.module.xztx.service.job.XztxJobService;
import cn.iocoder.yudao.module.xztx.service.resume.XztxResumeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 投递记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PostLogServiceImpl implements PostLogService {

    @Resource
    private PostLogMapper postLogMapper;

    @Resource
    private XztxJobService jobService;

    @Resource
    private XztxCompanyService companyService;

    @Resource
    private XztxResumeService resumeService;

    @Resource
    private MemberUserApi memberUserApi;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPostLog(Long jobId) {
        // 当前操作者id
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        PostLogDO postLog = new PostLogDO();
        // 获取投递岗位id
        XztxJobDO job = jobService.getJob(jobId);
        // 获取岗位对应的company
        Long companyId = job.getCompanyId();
        XztxCompanyDO company = companyService.getCompany(companyId);
        // 获取岗位负责人id
        Long companyUserId = company.getUserId();
        // 获取默认投递简历
        XztxResumeDO resume = resumeService.getOne(new LambdaQueryWrapper<XztxResumeDO>().eq(XztxResumeDO::getCreator, userId).eq(XztxResumeDO::getDefaultFlag, '1'));
        Long resumeId = resume.getId();
        // 设置投递记录信息
        postLog.setJobId(jobId);
        postLog.setApplyId(userId);
        postLog.setContactUserId(companyUserId);
        postLog.setResumeId(resumeId);
        postLog.setStatus(1);
        postLog.setCreateBy(userId);
        // 插入
        postLogMapper.insert(postLog);
        // 返回
        return postLog.getId();
    }

    @Override
    public void updatePostLog(PostLogSaveReqVO updateReqVO) {
        // 更新
        PostLogDO updateObj = BeanUtils.toBean(updateReqVO, PostLogDO.class);
        postLogMapper.updateById(updateObj);
    }

    @Override
    public void deletePostLog(Long id) {
        // 删除
        postLogMapper.deleteById(id);
    }

    @Override
    public boolean hasPosted(Long jobId, Long userId) {
        return postLogMapper.selectOne(new LambdaQueryWrapper<PostLogDO>().eq(PostLogDO::getJobId, jobId).eq(PostLogDO::getApplyId, userId)) != null;
    }

    @Override
    public List<AppPostLogFullVO> getPostLogList() {
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        List<AppPostLogFullVO> result = new ArrayList<>();
        List<PostLogDO> logs = postLogMapper.selectList(new LambdaQueryWrapper<PostLogDO>().eq(PostLogDO::getContactUserId, loginUserId));
        for (PostLogDO log : logs) {
            AppPostLogFullVO vo = new AppPostLogFullVO();
            vo.setId(log.getId());
            Long jobId = log.getJobId();
            XztxJobDO job = jobService.getJob(jobId);
            String jobName = job.getJobName();
            vo.setJobId(jobId);
            vo.setJobName(jobName);
            Long applyId = log.getApplyId();
            vo.setApplyId(applyId);
            vo.setContactUserId(log.getContactUserId());
            Long resumeId = log.getResumeId();
            vo.setResumeId(resumeId);
            XztxResumeDO resume = resumeService.getById(resumeId);
            vo.setResumeUrl(resume.getUrl());
            vo.setStatus(log.getStatus());
            vo.setRemark(log.getRemark());
            vo.setCreateTime(log.getCreateTime());
            vo.setUpdateTime(log.getUpdateTime());
            vo.setCreator(log.getCreator());
            vo.setUpdater(log.getUpdater());
            // 申请者信息
            MemberUserInfoVO applyUserInfo = new MemberUserInfoVO();
            MemberUserRespDTO memberUserRespDTO = memberUserApi.getUser(applyId);
            applyUserInfo.setId(memberUserRespDTO.getId());
            applyUserInfo.setNickname(memberUserRespDTO.getNickname());
            applyUserInfo.setAvatar(memberUserRespDTO.getAvatar());
            applyUserInfo.setMobile(memberUserRespDTO.getMobile());
            applyUserInfo.setSex(memberUserRespDTO.getSex());
            applyUserInfo.setName(memberUserRespDTO.getName());
            vo.setApplyUserInfo(applyUserInfo);

            result.add(vo);
        }
        return result;
    }

    @Override
    public PostLogDO getPostLog(Long id) {
        return postLogMapper.selectById(id);
    }

    @Override
    public PageResult<PostLogDO> getPostLogPage(PostLogPageReqVO pageReqVO) {
        return postLogMapper.selectPage(pageReqVO);
    }

}