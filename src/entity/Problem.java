/**
 * 
 */
package entity;

/**
 * @author 佳阳
 * 
 */
public class Problem {
	private int id;
	private String name;//问题名字
	private String content;//解答
	private String operatorName;//操作员名字
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	
}
