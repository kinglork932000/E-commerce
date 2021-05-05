package com.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinestore.domain.User;

public interface UserCartRepository extends CrudRepository<User,Long> {

}