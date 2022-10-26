package com.idat.EvaC2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idat.EvaC2.dto.UsuarioDTORequest;
import com.idat.EvaC2.dto.UsuarioDTOResponse;
import com.idat.EvaC2.security.TokenUtil;
import com.idat.EvaC2.security.UserDetailService;

@RestController
public class UsuarioController {
	
	@Autowired
	private TokenUtil util;
	@Autowired
	private UserDetailService service;
	
	@RequestMapping(path = "/crearToken", method = RequestMethod.POST)
	public ResponseEntity<?> crearToken(@RequestBody UsuarioDTORequest request){
		
		//ACCEDER A MI CAPA SERVICIO PARA BUSCAR EL USUARIO
		UserDetails user = service.loadUserByUsername(request.getUsuario());
		//SI EXISTE LO DEVUELVO COMO TOKEN
		return ResponseEntity.ok(new UsuarioDTOResponse(util.generateToken(user.getUsername())));
	}

}
