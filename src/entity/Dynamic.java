/**
 * 
 */
package entity;

import java.util.Date;

/**
 * @author 佳阳
 * 
 */
public class Dynamic {
	private int id;
	private String  content;
	private String time;
	private String operate;
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
