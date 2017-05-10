package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.fichier.DaoFichierFactory;
import fr.pizzeria.dao.jdbc.DaoJDBCFactory;
import fr.pizzeria.dao.memoire.DaoMemoryFactory;

import com.github.lalyos.jfiglet.FigletFont;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args)
	{
		
		// stockage en fichier
		//DaoFactory factory=new DaoFichierFactory();
// stockage en base de donnée
		DaoFactory factory=new DaoJDBCFactory();
		//IPizzaDao pizzaDaoMenu=new PizzaDaoImpl();
		//IPizzaDao pizzaDaoMenu=new PizzaDaoImplFichier();
		
		String asciiArt = FigletFont.convertOneLine("Pizza Ascii");
	    System.out.println(asciiArt);
		
		Menu menu=new Menu("##############################PIZZAAAAAAAAAAA########################",factory.getPizzaDao());
		int resultScan=0;
		
		do
		{
			menu.afficherMenu();
			Scanner scan=new Scanner(System.in);
			
			resultScan=scan.nextInt();
			menu.getNumOption(resultScan);
			
		}while(resultScan!=99);
		
		System.out.println("Au revoir :)");
		
		
	}
	
}
