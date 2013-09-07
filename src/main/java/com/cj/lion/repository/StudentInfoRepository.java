package com.cj.lion.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.lion.domain.StudentInfo;

@Repository
public interface StudentInfoRepository extends
		JpaRepository<StudentInfo, Long>, JpaSpecificationExecutor<StudentInfo> {
	Page<StudentInfo> findByStoredIsFalse(Pageable pageable);
}
