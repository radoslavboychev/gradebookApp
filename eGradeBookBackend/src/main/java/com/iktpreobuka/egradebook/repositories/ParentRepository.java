package com.iktpreobuka.egradebook.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.iktpreobuka.egradebook.entities.userEntities.ParentEntity;

public interface ParentRepository extends PagingAndSortingRepository<ParentEntity, Long> {

}
