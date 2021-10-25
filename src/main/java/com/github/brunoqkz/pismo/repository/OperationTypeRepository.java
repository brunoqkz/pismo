package com.github.brunoqkz.pismo.repository;

import com.github.brunoqkz.pismo.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {

}
