package com.practica.coches_hibernate.dao;


import com.practica.coches_hibernate.model.Coches;
import com.practica.coches_hibernate.util.HibertaneConf;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.naming.Referenceable;

import java.util.List;

public class CocheDaoImpl implements CocheDao {


	//DIFERENCIAS entre SessionFactory y session
	//******************************************
	//SessionFactory es una clase de fábrica para Session objetos.
	// Está disponible para toda la aplicación, mientras que Session
	// solo está disponible para una transacción en particular.
	//SI nuestra aplicación tiene más de una base de datos,
	// entonces deberíamos crear tantas fábricas de sesiones (SessionFactory) como nuestro número de bases de datos.

	//Session es de corta duración mientras que SessionFactory los objetos son de larga duración.

	//Una sesión se utiliza para obtener una conexión física con una base de datos.
	// El objeto Session es liviano y está diseñado para crear una instancia cada vez que se necesita
	// una interacción con la base de datos.
	// Los objetos persistentes se guardan y recuperan a través de un objeto de sesión.

	//
	//Los objetos de sesión no deben mantenerse abiertos durante mucho tiempo
	// porque generalmente no son seguros para subprocesos y deben crearse y destruirse según sea necesario.
	// La función principal de la sesión es ofrecer, crear, leer y eliminar operaciones
	// para instancias de clases de entidad asignadas.
	private SessionFactory factory = HibertaneConf.getFactory();
	/*
	 * Para trabajar con una base de datos usamos las transacciones.
	 *  En hibernate es tan sencillo como:

		1.- Crear una nueva transacción llamando al método beginTransaction() de la sesión:
		2.- Hacer un commit de la transacción actual llamando al método commit() de la transacción actual de la sesión:
		3.- Hacer un rollback de la transacción actual llamando al método rollback() de la transacción actual de la sesión:
	 * 
	 */

	//Si es necesario realizar una transacción con la base de datos,
	// se obtiene un objeto de sesión de corta duración de la instancia de Session Factory adecuada y,
	// una vez completada la transacción, la instancia ya no está disponible.

	/*
	
	Diferencia entre los métodos save, update y saveOrUpdate() de hibernate
	
	save

		El método Guardar almacena un objeto en la base de datos. Eso significa que inserta una entrada si el identificador no existe; de ​​lo contrario, arrojará un error. 
		Si la clave principal ya está presente en la tabla, no se puede insertar.

		El método save() devuelve el identificador generado, por lo que debe ejecutar inmediatamente la instrucción SQL INSERT.
		Ejemplo

		EmployeeEntity employee = new EmployeeEntity();
		employee.setEmail("demo-user@email.com");
		employee.setFirstName("demo");
		employee.setLastName("user");

		Long id = session.save(employee);

		Esto ejecutará la instrucción SQL INSERT.

			Hibernate: insert into Employee (ID, email, firstName, lastName) values (default, ?, ?, ?)

	update

		El método de actualización en hibernación se utiliza para actualizar el objeto mediante el identificador. Si el identificador falta o no existe, se generará una excepción.

	saveOrUpdate()

		Este método llama a save() o update() según la operación. Si el identificador existe, llamará al método de actualización; de lo contrario, se llamará al método de guardar. 



	https://howtodoinjava.com/hibernate/hibernate-save-and-saveorupdate/
		
 */

	@Override
	public void saveCoche(Coches coche) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.save(coche);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}

	@Override
	public Coches getCocheById(int id) {
		Transaction transaction = null;
		Coches coche = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			coche = session.get(Coches.class, id);
			transaction.commit();


		} catch (Exception e) {

			if(transaction != null)
				transaction.rollback();
		}
		return coche;
	}

	@Override
	public List<Coches> getCocheByMatricula(String matricula) {
		Transaction transaction = null;
		List<Coches> coches = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			coches = session.createQuery("from Coches where matricula like '"+matricula+"'").list();
			transaction.commit();

		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
		return coches;
	}

	@Override
	public List<Coches> getAllCoches() {
		Transaction transaction = null;
		List coches = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			coches = session.createQuery("from Coches").list();
			transaction.commit();

		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
		return coches;
	}

	@Override
	public void updateCoche(Coches coche) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			//Cuando los datos son persistentes(es decir pueden existir ya esos registros), usaremos saveOrUpdate
			//En caso de que el registro no exista hara un save (insert) y en caso de que si exista asumirá que debe actualizarlo (update)
			session.saveOrUpdate(coche);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}

	@Override
	public void deleteCocheById(int id) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Coches coche = session.get(Coches.class, id);
			session.delete(coche);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}


}
