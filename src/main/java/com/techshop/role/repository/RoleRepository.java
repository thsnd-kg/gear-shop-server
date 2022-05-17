package com.techshop.role.repository;

import com.techshop.role.dto.RoleDto;
import com.techshop.role.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.activeFlag = 'Y'") // JPQL - Java Persistence Query Language
	List<RoleDto> findAllDto();

	int countByName(String roleName);
}
