package com.microservices.usuario.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservices.usuario.entity.Usuario;

public interface UserRepository extends PagingAndSortingRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u JOIN FETCH u.roles WHERE u.username =?1")
	public Usuario findByUsername(String username);

}
