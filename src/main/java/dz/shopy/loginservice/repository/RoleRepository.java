package dz.shopy.loginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.shopy.loginservice.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {

}
