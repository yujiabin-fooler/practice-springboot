package com.jiabin.zip.stream.practice;

import java.util.Date;

public class TaskExamineRecord {

	/**方案名称*/
	private String name ;
	/**方案id*/
	private String schemaId ;
	/**模版id*/
	private String templateId ;
	/**模板名称*/
	private String templateName ;
	/**模板占位符*/
	private String placeholder ;
	/**占位符内容json格式{org_name: xxx, name: yyy}*/
	private String datas ;
	/**部门ID（对应机构ID）*/
	private Long deptId;
	/**审核用户ID*/
	private Long userId ;
	/**审核用户名*/
	private String username ;
	/**审核语音文件（只有审核通过才会生成该文件）*/
	private String audioFile ;
	/**审核状态;0未通过, 1已通过*/
	private Integer state ;
	/**合成时间*/
	protected Date composeTime ;
	/**任务ID*/
	private String taskId ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchemaId() {
		return schemaId;
	}
	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAudioFile() {
		return audioFile;
	}
	public void setAudioFile(String audioFile) {
		this.audioFile = audioFile;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	public String getDatas() {
		return datas;
	}
	public void setDatas(String datas) {
		this.datas = datas;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getComposeTime() {
		return composeTime;
	}
	public void setComposeTime(Date composeTime) {
		this.composeTime = composeTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}