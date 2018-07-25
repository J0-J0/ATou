package com.jojo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "atou_statistics")
public class AtouStatistics implements Serializable {
    @Id
    private Long id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "course_id")
    private Long courseId;

    /**
     * 存毫秒数
     */
    @Column(name = "total_time")
    private Long totalTime;

    /**
     * 总点击
     */
    @Column(name = "total_click")
    private Integer totalClick;

    private static final long serialVersionUID = 1L;

    public static final String PROP_ID = "id";

    public static final String PROP_GMT_CREATE = "gmtCreate";

    public static final String PROP_USER_ID = "userId";

    public static final String PROP_COURSE_ID = "courseId";

    public static final String PROP_TOTAL_TIME = "totalTime";

    public static final String PROP_TOTAL_CLICK = "totalClick";

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return course_id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取存毫秒数
     *
     * @return total_time - 存毫秒数
     */
    public Long getTotalTime() {
        return totalTime;
    }

    /**
     * 设置存毫秒数
     *
     * @param totalTime 存毫秒数
     */
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * 获取总点击
     *
     * @return total_click - 总点击
     */
    public Integer getTotalClick() {
        return totalClick;
    }

    /**
     * 设置总点击
     *
     * @param totalClick 总点击
     */
    public void setTotalClick(Integer totalClick) {
        this.totalClick = totalClick;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", userId=").append(userId);
        sb.append(", courseId=").append(courseId);
        sb.append(", totalTime=").append(totalTime);
        sb.append(", totalClick=").append(totalClick);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AtouStatistics other = (AtouStatistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
            && (this.getTotalTime() == null ? other.getTotalTime() == null : this.getTotalTime().equals(other.getTotalTime()))
            && (this.getTotalClick() == null ? other.getTotalClick() == null : this.getTotalClick().equals(other.getTotalClick()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getTotalTime() == null) ? 0 : getTotalTime().hashCode());
        result = prime * result + ((getTotalClick() == null) ? 0 : getTotalClick().hashCode());
        return result;
    }
}