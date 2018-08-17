package com.jojo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

@Table(name = "atou_user")
public class AtouUser implements Serializable {
	
	@Id
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	@Column(name = "gmt_create")
	private Date gmtCreate;

	@Column(name = "gmt_modified")
	private Date gmtModified;

	private String nickname;

	private String username;

	private String wxid;

	private Integer level;

	@Column(name = "total_time")
	private Long totalTime;

	@Column(name = "total_click")
	private Integer totalClick;

	private static final long serialVersionUID = 1L;

	public static final String PROP_ID = "id";

	public static final String PROP_GMT_CREATE = "gmtCreate";

	public static final String PROP_GMT_MODIFIED = "gmtModified";

	public static final String PROP_NICKNAME = "nickname";

	public static final String PROP_USERNAME = "username";

	public static final String PROP_WXID = "wxid";

	public static final String PROP_LEVEL = "level";

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
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return wxid
	 */
	public String getWxid() {
		return wxid;
	}

	/**
	 * @param wxid
	 */
	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	/**
	 * @return level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return total_time
	 */
	public Long getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime
	 */
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return total_click
	 */
	public Integer getTotalClick() {
		return totalClick;
	}

	/**
	 * @param totalClick
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
		sb.append(", gmtModified=").append(gmtModified);
		sb.append(", nickname=").append(nickname);
		sb.append(", username=").append(username);
		sb.append(", wxid=").append(wxid);
		sb.append(", level=").append(level);
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
		AtouUser other = (AtouUser) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getGmtCreate() == null ? other.getGmtCreate() == null
						: this.getGmtCreate().equals(other.getGmtCreate()))
				&& (this.getGmtModified() == null ? other.getGmtModified() == null
						: this.getGmtModified().equals(other.getGmtModified()))
				&& (this.getNickname() == null ? other.getNickname() == null
						: this.getNickname().equals(other.getNickname()))
				&& (this.getUsername() == null ? other.getUsername() == null
						: this.getUsername().equals(other.getUsername()))
				&& (this.getWxid() == null ? other.getWxid() == null : this.getWxid().equals(other.getWxid()))
				&& (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
				&& (this.getTotalTime() == null ? other.getTotalTime() == null
						: this.getTotalTime().equals(other.getTotalTime()))
				&& (this.getTotalClick() == null ? other.getTotalClick() == null
						: this.getTotalClick().equals(other.getTotalClick()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
		result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
		result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
		result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
		result = prime * result + ((getWxid() == null) ? 0 : getWxid().hashCode());
		result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
		result = prime * result + ((getTotalTime() == null) ? 0 : getTotalTime().hashCode());
		result = prime * result + ((getTotalClick() == null) ? 0 : getTotalClick().hashCode());
		return result;
	}
}