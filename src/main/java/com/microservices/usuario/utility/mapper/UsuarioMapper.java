package com.microservices.usuario.utility.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservices.usuario.entity.Usuario;
import com.microservices.usuario.pojo.UsuarioDTO;

@Mapper
public interface UsuarioMapper {
	UsuarioMapper INTANCE = Mappers.getMapper(UsuarioMapper.class);
	public UsuarioDTO mapperEntityToDto (Usuario usuario);
	public Usuario mapperDtoToEntity (UsuarioDTO usuario);

}
