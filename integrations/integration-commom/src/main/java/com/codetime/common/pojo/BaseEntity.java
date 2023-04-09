package com.codetime.common.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * 基类
 *
 * @author test-code
 * @since 2023-4-8
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;
//    /**
//     * 创建时间
//     */
//    @Builder.Default
//    private LocalDateTime createTime = LocalDateTime.now();
//
//    /**
//     * 最后更新时间
//     */
//    @Builder.Default
//    private LocalDateTime updateTime = LocalDateTime.now();

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 是否删除
     */
    private Boolean deleted;

}
