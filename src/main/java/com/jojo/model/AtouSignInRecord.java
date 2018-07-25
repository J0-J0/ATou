package com.jojo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "atou_sign_in_record")
public class AtouSignInRecord implements Serializable {
	@Id
	private Long id;

	@Column(name = "gmt_create")
	private Date gmtCreate;

	@Column(name = "gmt_modified")
	private Date gmtModified;

	@Column(name = "gmt_end")
	private Date gmtEnd;

	@Column(name = "user_id")
	private Long userId;

	private static final long serialVersionUID = 1L;

	public static final String PROP_ID = "id";

	public static final String PROP_GMT_CREATE = "gmtCreate";

	public static final String PROP_GMT_MODIFIED = "gmtModified";

	public static final String PROP_GMT_END = "gmtEnd";

	public static final String PROP_USER_ID = "userId";

	/**
	 * 开始打卡
	 */
	public static final String START = "start";

	/**
	 * 结束打卡
	 */
	public static final String END = "end";
	
	/**
	 * 管理员校准
	 */
	public static final String CORRECT = "CORRECT";
	

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
	 * @return gmt_modified
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	/**
	 * @param gmtModified
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * @return gmt_end
	 */
	public Date getGmtEnd() {
		return gmtEnd;
	}

	/**
	 * @param gmtEnd
	 */
	public void setGmtEnd(Date gmtEnd) {
		this.gmtEnd = gmtEnd;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", gmtCreate=").append(gmtCreate);
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", gmtEnd=").append(gmtEnd);
		sb.append(", userId=").append(userId);
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
		AtouSignInRecord other = (AtouSignInRecord) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getGmtCreate() == null ? other.getGmtCreate() == null
						: this.getGmtCreate().equals(other.getGmtCreate()))
				&& (this.getGmtModified() == null ? other.getGmtModified() == null
						: this.getGmtModified().equals(other.getGmtModified()))
				&& (this.getGmtEnd() == null ? other.getGmtEnd() == null : this.getGmtEnd().equals(other.getGmtEnd()))
				&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
		result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
		result = prime * result + ((getGmtEnd() == null) ? 0 : getGmtEnd().hashCode());
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		return result;
	}
}