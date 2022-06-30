package com.desafio.ey.persistence;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.desafio.ey.model.UserDTO;

public interface UserRepository extends CrudRepository<UserDTO, Long>{

	Optional<UserDTO> findByEmail(String email);
}
