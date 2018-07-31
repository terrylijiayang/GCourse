/**
 * 
 */
package entity;

import java.util.List;

/**
 * @author 佳阳
 * 
 */
public class Course {
	private int id;
	private String intro;//课程简介
	private String principal;//课程负责人
	private List<Dynamic> dynamics;//课程动态
	private List<Source> sources;//教学资源
	private List<Admin> admins;//教学团队
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public List<Dynamic> getDynamics() {
		return dynamics;
	}
	public void setDynamics(List<Dynamic> dynamics) {
		this.dynamics = dynamics;
	}
	public List<Source> getSources() {
		return sources;
	}
	public void setSources(List<Source> sources) {
		this.sources = sources;
	}
	public List<Admin> getAdmins() {
		return admins;
	}
	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	
}
