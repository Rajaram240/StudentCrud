package com.DAO;

import org.springframework.stereotype.Repository;

import com.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository

public interface StudentDAO extends JpaRepository<Student, Long>{

}
