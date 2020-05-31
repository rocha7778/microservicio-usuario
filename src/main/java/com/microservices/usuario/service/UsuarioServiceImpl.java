package com.microservices.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.microservices.usuario.entity.Usuario;
import com.microservices.usuario.repository.UserRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UserRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Usuario getById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public Usuario getByUserName(String name) {
		return usuarioRepository.findByUsername(name);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public List<Usuario> getAllPagination(int pageSize, int pageNumber) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> usersRoot = cq.from(Usuario.class);
		usersRoot.fetch("roles",JoinType.LEFT);
		List<Usuario> usuarios = em.createQuery(cq).setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize).getResultList();
		return usuarios;
	}

	public Page<Usuario> getAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public Usuario update(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findByCriteria(Map<String, String> allParams) {
		
		String username = null;
		
		if (allParams.containsKey(("username"))) {
			username = allParams.get("username");
			username = getValidValue(username);;
		}
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> usersRoot = cq.from(Usuario.class);
		
		usersRoot.fetch("roles",JoinType.LEFT);
		
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(username!=null) {
			predicates.add(cb.equal(usersRoot.get("username"), username));
		}
		
		if (!predicates.isEmpty()) {
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		
		List<Usuario> usuarios = em.createQuery(cq).getResultList();
		
		if(usuarios==null || usuarios.isEmpty()) {
			return null;
		}
		return usuarios.get(0);
	}
	
	
	private String getValidValue(String value) {
		if(value != null  && value.trim().isEmpty()) {
			return null;
		}
		return value;
	}

}
