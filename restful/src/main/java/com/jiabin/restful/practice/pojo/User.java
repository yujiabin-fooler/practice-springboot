package com.jiabin.restful.practice.pojo;
/**
 * 
* Title: User
* Description:用户pojo类
* Version:1.0.0
 */
public class User {
	 /** 编号 */
	 private int id;
	 /** 姓名 */
	 private String name;
	 
	 /** 年龄 */
	 private int state;
	 
	 public User(){
	 }
	 /**
	  *  构造方法
	  * @param id  编号
	  * @param name  姓名
	  */
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**  
	 * 获取编号  
	 * @return id 
	 */
	public int getId() {
		return id;
	}

	/**  
	 * 设置编号  
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**  
	 * 获取姓名  
	 * @return name 
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置姓名  
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**  
	 * 获取年龄  
	 * @return  state
	 */
	public int getState() {
		return state;
	}
	/**  
	 * 设置年龄  
	 * @param int state
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	

}
