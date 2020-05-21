package dz.shopy.loginservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
public class UserDetail {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_seq_gen")
	@SequenceGenerator(name = "user_details_seq_gen", sequenceName = "user_details_seq_gen", allocationSize = 1)
	private Long id;

	@Column(name = "first_name")
	@NotNull
	@NotEmpty(message = "firstname should not be empty")
	private String firstname;

	@Column(name = "last_name")
	@NotNull
	@NotEmpty(message = "lastname should not be empty")
	private String lastname;

	@NotNull
	@NotEmpty(message = "email should not be empty")
	@Pattern(regexp = "^(.+)@(.+)$", message = "please to give a valid email")
	@Column(name = "email")
	private String email;

	@NotNull
	@NotEmpty(message = "phoneNumber should not be empty")
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@JsonIgnoreProperties({ "id" })
	private Address address;

}
