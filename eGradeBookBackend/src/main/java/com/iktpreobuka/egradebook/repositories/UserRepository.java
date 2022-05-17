package com.iktpreobuka.egradebook.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.egradebook.entities.RoleEntity;
import com.iktpreobuka.egradebook.entities.userEntities.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {

	public List<UserEntity> findAllByDeleted(Integer deleted);

	public List<UserEntity> findAllByDeletedAndRole(Integer deleted, RoleEntity role);

	public Optional<UserEntity> findByDeletedAndRoleAndUsername(Integer deleted, RoleEntity role, String Username);

	public Optional<UserEntity> findByDeletedAndUsername(Integer deleted, String username);

	public Optional<UserEntity> findByEmail(String email);

	public Optional<UserEntity> findByJmbg(String jmbg);

	public Optional<UserEntity> findByUsername(String username);

}
