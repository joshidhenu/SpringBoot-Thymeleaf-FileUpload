package com.fileupload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public List<User> findAllByOrderByIdAsc();

}
