package fr.pizzeria.dao.jdbc;

import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.exception.CreditException;
import fr.pizzeria.dao.exception.DebitException;
import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;

/**
 * Hello world!
 *
 */
public class PizzaDaoJDBC implements IPizzaDao {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver O.K.");
		} catch (ClassNotFoundException e) {
			System.out.println("Probleme de driver");
			e.printStackTrace();
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {

		// je declare une liste avec des objet uniqumeent de typoe Pizza et la
		// liste s'appel pizza
		List<Pizza> pizza = new ArrayList<>();

		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
					"root", "");
			// System.out.println("Connexion effective !");
			Statement statement = myConnection.createStatement();
			// je crecupere tous les champs de la table pizza avec le select
			// *....
			ResultSet resultat = statement.executeQuery("SELECT * from pizza ");

			while (resultat.next()) {

				// System.out.println("\t" + resultat.getInt("ID") + "\t" +
				// resultat.getString("categorie") + "\t"
				// + resultat.getString("libelle") + "\t" +
				// resultat.getFloat("prix"));

				// Pizza p = new Pizza(resultat.getString("code"),
				// resultat.getString("libelle"), resultat.getFloat("prix"),
				// CategoriePizza.valueOf(resultat.getString("categorie")));

				Pizza p = new Pizza(resultat.getString("code"), resultat.getString("libelle"),
						resultat.getFloat("prix"), CategoriePizza.valueOf(resultat.getString("categorie")));
				pizza.add(p);

			}

			resultat.close();
			statement.close();
			return pizza;
		} catch (SQLException e) {
			System.out.println("probleme connection");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Client> findAllClient() {
		List<Client> client = new ArrayList<>();

		try {
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
					"root", "");
			System.out.println("Connexion effective !");
			Statement statement = myConnection.createStatement();
			// je crecupere tous les champs de la table pizza avec le select
			// *....
			ResultSet resultat = statement.executeQuery("SELECT * from client ");

			while (resultat.next()) {
				// System.out.println("\t" + resultat.getInt("ID") + "\t" +
				// resultat.getString("categorie") + "\t"
				// + resultat.getString("libelle") + "\t" +
				// resultat.getFloat("prix"));

				// Pizza p = new Pizza(resultat.getString("code"),
				// resultat.getString("libelle"), resultat.getFloat("prix"),
				// CategoriePizza.valueOf(resultat.getString("categorie")));
				Client c = new Client();
				client.add(c);

			}

			resultat.close();
			statement.close();
			return client;
		} catch (SQLException e) {
			System.out.println("probleme connection");
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {

		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false", "root", "");
			System.out.println("Connexion effective !");
			PreparedStatement saveNewPizza = myConnection
					.prepareStatement("INSERT INTO pizza (categorie,libelle,prix,code) VALUES (?,?,?,?)");
			// valoriser les différents paramétre "?" grâce à l'objet pizza

			saveNewPizza.setString(1, pizza.getCategorie());
			saveNewPizza.setString(2, pizza.getNom());
			saveNewPizza.setDouble(3, pizza.getPrix());
			saveNewPizza.setString(4, pizza.getCode());
			int nbPizzaInsere = saveNewPizza.executeUpdate();
			System.out.println(nbPizzaInsere + " pizza inséré");

			saveNewPizza.close();
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		Connection myConnection;
		try {
			
		
			
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false", "root", "");

			PreparedStatement updatePizzaSt = myConnection
					.prepareStatement("UPDATE pizza set code = ?, libelle = ?, prix = ?, categorie = ? where code = ?");
			
			updatePizzaSt.setString(1, pizza.getCode().toUpperCase());
			updatePizzaSt.setString(2, pizza.getNom());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			
		
		    updatePizzaSt.setString(4, pizza.getCategorie());
			//CategoriePizza categorie = pizza.getCategorie();

			//updatePizzaSt.setString(4, "VIANDE");
		    
		    
		    updatePizzaSt.setString(5, codePizza);

		    updatePizzaSt.executeUpdate();
			
			updatePizzaSt.close();
			myConnection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {

		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false", "root", "");
			System.out.println("Connexion effective !");
			PreparedStatement supression = myConnection
					.prepareStatement("DELETE FROM `pizza` WHERE `pizza`.`code` = ?");
			// valoriser les différents paramétre "?" grâce à l'objet pizza

			supression.setString(1, codePizza);

			int nbPizzaInsere = supression.executeUpdate();
			System.out.println(nbPizzaInsere + " pizza supprimé");

			supression.close();
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void crediterCompteClient(int id, double montant) throws CreditException {
		// TODO Auto-generated method stub

	}

	@Override
	public void debiterCompteClient(int id, double montant) throws DebitException {
		// TODO Auto-generated method stub

	}
}
