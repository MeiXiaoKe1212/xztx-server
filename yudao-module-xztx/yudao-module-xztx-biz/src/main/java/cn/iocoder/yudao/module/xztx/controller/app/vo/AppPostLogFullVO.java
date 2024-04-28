package cn.iocoder.yudao.module.xztx.controller.app.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 投递记录 关联 VO
 */
@Data
public class AppPostLogFullVO {

    // 投递记录id
    private Long id;

    // 岗位id
    private Long jobId;

    // 岗位名称
    private String jobName;

    // 投递者id
    private Long applyId;

    // 岗位联系人id
    private Long contactUserId;

    // 简历id
    private Long resumeId;

    // 投递状态
    private int status;

    // 投递者信息
    private MemberUserInfoVO applyUserInfo;

    // 简历url
    private String resumeUrl;

    // 备注
    private String remark;

    // 创建时间
    private LocalDateTime createTime;

    // 最后更新时间
    private LocalDateTime updateTime;

    // 创建者
    private String creator;

    // 更新者
    private String updater;

}
