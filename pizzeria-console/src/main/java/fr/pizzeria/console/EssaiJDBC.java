package fr.pizzeria.console;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class EssaiJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver O.K.");

		Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizzeria?useSSL=false",
				"root", "");
		System.out.println("Connexion effective !");

		// Creation d'un objet statement
		Statement statement = myConnection.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet resultat = statement.executeQuery("SELECT count(*) from pizza ");
		// Recupérer les MetaData
		ResultSetMetaData resultMeta = resultat.getMetaData();

		int nbPizza = 0;
		if (resultat.next()) {
			nbPizza = resultat.getInt(1);
		}

		PreparedStatement selectPizzaSt = myConnection.prepareStatement("SELECT * FROM PIZZA WHERE ID =?");

		// .next me permet de se placer sur les lignes du tableaux de données,
		// et les get nous donnent les donner des colonnes
		for (int i = 1; i < nbPizza; i++) {
			selectPizzaSt.setInt(1, i);
			ResultSet resultats = selectPizzaSt.executeQuery();

			System.out.println(resultats.next() + "\t" + resultats.getInt("ID") + "\t" + resultats.getString("categorie")
					+ "\t" + resultats.getString("libelle") + "\t" + resultats.getFloat("prix"));

		}
		resultat.close();
		statement.close();
		
	}

}
