package dz.shopy.loginservice.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
	@SequenceGenerator(name = "users_seq_gen", sequenceName = "users_seq_gen", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@NotNull
	@NotEmpty(message = "username should not be empty")
	@Column(name = "username")
	private String username;

	@NotNull
	@NotEmpty(message = "password should not be empty")
	@Pattern(regexp = "^[a-zA-Z@#$%^&+=](?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}[a-zA-Z0-9]$", message = "password doesn't respect security policy")
	@Column(name = "password")
	private String password;

	@JsonIgnore
	@Column(name = "creation_date")
	private Date creationDate;

	@JsonIgnore
	@Column(name = "enabled")
	private Boolean enabled;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user_detail", referencedColumnName = "id")
	private UserDetail userDetails;

	// case of there is no added attribute in the relation
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<Roles> roles;
}
