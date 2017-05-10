package fr.pizzeria.dao.jdbc;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.IPizzaDao;

public class DaoJDBCFactory implements DaoFactory {

	// Utilisation ndu polymorphisme, Dans la declaraztion d'un nouveau objet, à
	// gauche on peux utiliser une classe d'un plus haut niveai d'abstraction .
	private IPizzaDao pizzaDao = new PizzaDaoJDBC();

	@Override
	public IPizzaDao getPizzaDao() {
		// TODO Auto-generated method stub
		return pizzaDao;
	}

}
