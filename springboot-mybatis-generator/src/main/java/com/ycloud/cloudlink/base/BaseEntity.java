package com.ycloud.cloudlink.base;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 *
 * @author XiangYu.Geng
 * @date 2021-03-10
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键Id */
    private Long id;

    /** 创建时间(插入操作自动填充) */
    private LocalDateTime createdAt;

    /** 更新时间(更新操作自动填充) */
    private LocalDateTime updatedAt;

    /** 删除状态：null-正常,非null-已删除 */
    private LocalDateTime deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
