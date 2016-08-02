package org.article.repo;

import org.article.domain.User;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> { }
