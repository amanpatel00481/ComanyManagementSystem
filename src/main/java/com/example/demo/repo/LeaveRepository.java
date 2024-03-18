package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

}
