/**
 * 
 */
package org.nerdronix.springnetty.server;

import java.io.Serializable;

/**
 * @author Jason.Zhu
 * @date   2014-5-28
 * @email jasonzhu@augmentum.com.cn
 */
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String data;
	
	
	
	
	/**
	 * 
	 */
	public Message() {
	}
	/**
	 * @param id
	 * @param data
	 */
	public Message(int id, String data) {
		super();
		this.id = id;
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", data=" + data + "]";
	}
	
	
	
	
}
