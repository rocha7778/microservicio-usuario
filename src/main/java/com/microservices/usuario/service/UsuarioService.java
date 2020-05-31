package com.microservices.usuario.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.microservices.usuario.entity.Usuario;

public interface UsuarioService {
	
	public Usuario getById(Long id);
	public Usuario getByUserName(String name);
	public Usuario save(Usuario usuario);
	public List<Usuario> getAll();
	public void deleteById(Long id);
	public List<Usuario> getAllPagination(int pageSize, int pageNumber);
	public Page<Usuario> getAll(Pageable pageable);
	public Usuario update(Usuario usuario);
	public Usuario findByCriteria(Map<String, String> allParams);

}
