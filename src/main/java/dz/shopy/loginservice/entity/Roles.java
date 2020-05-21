package dz.shopy.loginservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Roles {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	public Roles(Long id) {
		super();
		this.id = id;
	}

	public Roles() {
		super();
	}

	// case of there no additional attributes in relation
//	@ManyToMany(mappedBy = "roles")
//	private Set<User> users;

//	@OneToMany(mappedBy = "roleId")
//	private Set<UserRole> userRoles;
	
	
	
	
}
