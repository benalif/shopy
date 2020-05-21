package dz.shopy.loginservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_gen")
	@SequenceGenerator(name = "address_seq_gen", sequenceName = "address_seq_gen", allocationSize = 1)
	private Long id;

	@Column(name = "address")
	@NotNull
	@NotEmpty(message = "address should not be empty")
	private String address;

	@Column(name = "phone")
	@NotNull
	@NotEmpty(message = "phone number should not be empty")
	private String phoneNumber;

	public Address(Long id) {
		super();
		this.id = id;
	}

	public Address(String address, String phoneNumber) {
		super();
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public Address() {
		super();
	}
}
