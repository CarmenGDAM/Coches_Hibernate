package com.practica.coches_hibernate.dao;


import com.practica.coches_hibernate.model.Coche;

import java.util.List;

public interface CocheDao {
	
	void saveCoche(Coche coche);

	Coche getCocheById(int id);
	List <Coche> getCocheByMatricula(String matricula);

	List<Coche> getAllCoches();

	void updateCoche(Coche user);

	void deleteCocheById(int id);

}
