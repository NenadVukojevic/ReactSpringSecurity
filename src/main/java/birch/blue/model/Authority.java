package birch.blue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "ROLES")
public class Authority implements GrantedAuthority{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private Integer id;
	
	@Column(name = "ROLENAME")
	private String roleName;
	
	@Column(name = "DESCRIPTION")
	private String roleDescription;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return roleName;
	}

	public Authority() {
		super();
	}

	public Authority(Integer id, String roleName, String roleDescription) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	

}
