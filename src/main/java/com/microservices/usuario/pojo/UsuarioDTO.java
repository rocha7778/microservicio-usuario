package com.microservices.usuario.pojo;

import java.io.Serializable;
import java.util.List;

import com.microservices.usuario.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = -4502159641735567719L;
	private Long id;
	private String username;
	private Boolean enabled;
	private String name;
	private String apellido;
	private String email;
	private List<Role> roles;

}
