package com.microservices.usuario;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.usuario.pojo.UsuarioDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class MicroservicioUsuarioApplicationTests {

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void mapperTest() {
		UsuarioDTO  usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(1L);
		
		
	}

}
