package com.idat.EvaC2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.EvaC2.model.Cliente;
import com.idat.EvaC2.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository repositorio;

	@Override
	public void guardar(Cliente cliente) {
		repositorio.save(cliente);
	}

	@Override
	public void actualizar(Cliente cliente) {
		repositorio.saveAndFlush(cliente);

	}

	@Override
	public void eliminar(Integer id) {
		repositorio.deleteById(id);

	}

	@Override
	public List<Cliente> listar() {
		return repositorio.findAll();
	}

	@Override
	public Cliente obtener(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

}
