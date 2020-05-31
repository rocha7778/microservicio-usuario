package com.microservices.usuario.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.usuario.entity.Usuario;
import com.microservices.usuario.pojo.UsuarioDTO;
import com.microservices.usuario.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService userService;
	
	
	@GetMapping("/users")
	public List<UsuarioDTO> getAll() {
		return userService.getAll().stream().map(u->getDto(u)).collect(Collectors.toList());
	}
	
	@GetMapping("/users/page-number/{page-number}/page-size/{page-size}")
	public List<UsuarioDTO> getAllPagination(@PathVariable("page-number") int pageNumber , @PathVariable("page-size") int pageSize ) {
		return userService.getAllPagination(pageSize, pageNumber).stream().map(u->getDto(u)).collect(Collectors.toList());
	}
	
	@GetMapping("/users/paginations")
	public Page<Usuario> getAllPagination(Pageable pageable ) {
		return userService.getAll(pageable);
	}
	
	
	@GetMapping("/users/{id}")
	public  ResponseEntity<UsuarioDTO> getByID(@PathVariable Long id) {
		
		Usuario u = userService.getById(id);
		if(u==null || u.getId()==null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioDTO>(getDto(userService.getById(id)),HttpStatus.OK);
	}
	
	
	@GetMapping("/users/internal/name/{name}")
	public ResponseEntity<Usuario> getByUserName(@PathVariable String name) {

		Usuario u = userService.getByUserName(name);

		if (u == null || u.getId() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(u, HttpStatus.OK);
	}
	
	
	@GetMapping("/users/filters")
	public  ResponseEntity<UsuarioDTO> findUserByCriteria(@RequestParam Map<String, String> allParams){
		
		Usuario u = userService.findByCriteria(allParams);
		
		if(u==null || u.getId()==null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UsuarioDTO>(getDto(u),HttpStatus.OK);
		
	}
	
	
	

	@PostMapping("/users")
	public UsuarioDTO post(@RequestBody Usuario usuario) {
		return getDto(userService.save(usuario));
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UsuarioDTO> post(@PathVariable Long id,@RequestBody Usuario usuario) {
		
		Usuario u = userService.getById(id);
		if(u==null || u.getId()==null) {
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
		usuario.setId(id);
		return new ResponseEntity<UsuarioDTO>(getDto(userService.save(usuario)),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/users/{id}")
	public void deleteByID(@PathVariable Long id) {
		userService.deleteById(id);
	}
	
	protected UsuarioDTO getDto(final Usuario entity) {

		final UsuarioDTO dto = new UsuarioDTO();
		dto.setId(entity.getId());
		dto.setApellido(entity.getApellido());
		dto.setEmail(entity.getEmail());
		dto.setEnabled(entity.getEnabled());
		dto.setName(entity.getName());
		dto.setUsername(entity.getUsername());
		dto.setRoles(entity.getRoles());
		
		return dto;
	}
	
	
	protected UsuarioDTO getEntity(final Usuario entity) {

		final UsuarioDTO dto = new UsuarioDTO();
		dto.setId(entity.getId());
		dto.setApellido(entity.getApellido());
		dto.setEmail(entity.getEmail());
		dto.setEnabled(entity.getEnabled());
		dto.setName(entity.getName());
		dto.setUsername(entity.getUsername());
		dto.setRoles(entity.getRoles());
		
		return dto;
	}
	

}
