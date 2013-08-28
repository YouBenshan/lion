package com.cj.lion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.lion.domain.StudentPicture;

@Repository
public interface StudentPictureRepository  extends JpaRepository<StudentPicture, Long>, JpaSpecificationExecutor <StudentPicture> {

}
