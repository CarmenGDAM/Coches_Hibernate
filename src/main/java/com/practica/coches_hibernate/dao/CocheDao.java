package com.practica.coches_hibernate.dao;


import com.practica.coches_hibernate.model.Coches;

import java.util.List;

public interface CocheDao {
	
	void saveCoche(Coches coche);

	Coches getCocheById(int id);
	List <Coches> getCocheByMatricula(String matricula);

	List<Coches> getAllCoches();

	void updateCoche(Coches user);

	void deleteCocheById(int id);

}
