/**
 * 
 */
package entity;

import java.util.List;

/**
 * @author 佳阳
 * 
 */
public class Role {
	private int id;
	private String name;
	private List<Model> models;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return name;
	}
	public void setRole(String name) {
		this.name = name;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	
}
