package com.idat.EvaC2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idat.EvaC2.model.Cliente;
import com.idat.EvaC2.service.ClienteService;

@RestController
@RequestMapping("/cliente/v1")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar(){//INTERACTUA CON HTTP
		return new ResponseEntity<List<Cliente>>(service.listar(), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Cliente cliente){
		service.guardar(cliente);
		return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(path = "/listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> obtenerPorId(@PathVariable Integer id){
		
		Cliente cliente = service.obtener(id);//VALIDO QUE EXISTA
		if(cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}else {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.NOT_FOUND);
		}		
	}
	
	@RequestMapping(path = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Cliente cliente){
		
		Cliente c = service.obtener(cliente.getIdCliente());//VALIDO QUE EXISTA
		if(c != null) {
			service.actualizar(cliente);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		
		Cliente cliente = service.obtener(id);//VALIDO QUE EXISTA
		if(cliente != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}

}
