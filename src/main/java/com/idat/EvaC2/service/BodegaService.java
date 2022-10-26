package com.idat.EvaC2.service;

import java.util.List;

import com.idat.EvaC2.model.Bodega;

public interface BodegaService {
	
	void guardar(Bodega bodega);
	void actualizar(Bodega bodega);
	void eliminar(Integer id);
	List<Bodega> listar();
	Bodega obtener(Integer id);

}
