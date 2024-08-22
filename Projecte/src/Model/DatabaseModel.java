package Model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.LinkedHashMap;

import Model.DatabaseModel;

	
public class DatabaseModel{
	private DatabaseModel db;
	private LinkedHashMap<String, String> colorsDB = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> rankingsDB = new LinkedHashMap<String, String>();
	File arxiu = new File("Ranking.txt");
	
	// Setter de la db
    public void setDatabase(DatabaseModel db)
    {
    	this.db = db;
    }

	// Funcio que es connecta a la db (en aquest cas sempre retorna simulem que 
    // sempre es connecta)
	public boolean connectBD() {
		return true;
	}
 
	// Funcio encarregada de fer insercions de tots els particiants del joc a la 
	// taula de ranking de la nostra db. Si retorna true es que tot esta correcte
	// i si retorna false es que alguna cosa ha fallat.
	public boolean insertBD(String query) throws IOException {
		// Regex permet: "char comma{1} num{1} comma{1} num{1}"
		if (!query.matches("^([a-zA-Z])+(,\\s){1}+([0-9]){1}+(,\\s){1}+([0-9]){1}$"))
			return false;
		
		String[] queryParsed = query.split(",");
		String nom = queryParsed[0].trim();
		String boles = queryParsed[1].trim();
		String intents = queryParsed[2].trim();
		boolean existeixNom = false;
		
		// Comprovem si el nom existeix a la taula
		LinkedHashMap<String, String> content = db.getRankingsDB();
		if (content.containsKey(nom))
			existeixNom = true;
		
		// Comprovació de les boles
			if (Integer.parseInt(boles) < 4 || Integer.parseInt(boles) > 7) {
				if (Integer.parseInt(boles) == 0 && Integer.parseInt(intents) != 0) {
					return false;
			}
			// Comprovació dels intents
			if (Integer.parseInt(intents) < 1 || Integer.parseInt(intents) > 6) {
				if (Integer.parseInt(boles) != 0 && Integer.parseInt(intents) == 0) {
					return false;
				} 
			}
		}
		if(!existeixNom) {
			FileOutputStream fitxerSortida = new FileOutputStream("Ranking.txt",true);
			OutputStreamWriter objectiuSortida = new OutputStreamWriter(fitxerSortida);
			BufferedWriter insertar = new BufferedWriter(objectiuSortida);
			insertar.write(query+"\n");
			insertar.close();
		} else {
			System.out.println("El nom introduit ja existeix. Canvia'l.");
			return false;
		}
	return true;
	}  
	
	// Funcio encarregada de fer actualitzacions a la taula de ranking de la 
	// nostra db. Si retorna true es que tot esta correcte i si retorna false 
	// es que alguna cosa ha fallat.
	public boolean updateBD(String query) throws IOException {
		// Format esperat "nom, boles, intents"
		
		// Comprovació de paràmetres
		if ( query.isEmpty())
			return false;

		// Regex permet: "char (ilimitat pero nomes lletres) comma{1} num{1} comma{1} num{1}"
		if (!query.matches("^([a-zA-Z])+(,\\s){1}+([0-9]){1}+(,\\s){1}+([0-9]){1}$"))
			return false;
		
		
		String[] queryParsed = query.split(",");
		String nom = queryParsed[0].trim();
		String boles = queryParsed[1].trim();
		String intents = queryParsed[2].trim();
		
		// Comprovem si el nom existeix a la taula
		LinkedHashMap<String, String> content = db.getRankingsDB();
		if (!content.containsKey(nom))
			return false;
		
		// Comprovació de les boles
		if (Integer.parseInt(boles) < 4 || Integer.parseInt(boles) > 7)
			return false;
		
		// Comprovació dels intents
		if (Integer.parseInt(intents) < 1 || Integer.parseInt(intents) > 6)
			return false;

		FileReader fileReader = new FileReader("Ranking.txt");
		BufferedReader reader = new BufferedReader(fileReader);
		String linea = reader.readLine();
		StringBuffer buffer = new StringBuffer();
		String toReplace = "", nou="";
		String[] lineSplit = null;
		
		while(linea != null) {
			buffer.append(linea + System.lineSeparator());
			lineSplit = linea.split(",");

			if (lineSplit[0].contains(nom)) {
				toReplace = linea;
				String newBoles = boles.trim();
				String newIntents = intents.trim();
				nou = lineSplit[0] + ", " + newBoles + ", " + newIntents;
			}
			linea = reader.readLine();
		}
		String fileContent = buffer.toString();
		fileContent = fileContent.replaceAll(toReplace, nou);
		FileWriter writer = new FileWriter("Ranking.txt");
		writer.append(fileContent);
		writer.flush();
		return true;
	}

	// Funcio encarregada de retornar un LinkedHashMap amb els participants que
	// compleixin amb els atributs que es reben com a parametre. En aquest cas nomès
	// volem mirar els participants que han participat amb un número concret de boles.
	// Tot i aixi hem afegit buscar també pel nom.
	public LinkedHashMap<String, String> queryBD(String query) throws IOException {
		// Format de la query: "boles=4" o "nom=Eric"
		LinkedHashMap<String, String> contingut = new LinkedHashMap<String,String>();
		LinkedHashMap<String, String> rankingsAux = new LinkedHashMap<String,String>();
		
		// Regex permet: "char igual{1} num{1} o char igual{1} char"
		if (!query.matches("^([a-zA-Z])+[=]{1}+([0-9]){1}") && !query.matches("[a-zA-Z]+[=]{1}+[a-zA-Z]+"))
			return contingut;
		
		String[] queryParsed = query.split("=");
		String nomVariable = queryParsed[0];
		String variableBuscar = queryParsed[1];
		
		// Comprovació de les boles
		if(nomVariable.equals("boles")) {
			if ((Integer.parseInt(variableBuscar) < 4 || Integer.parseInt(variableBuscar) > 7) && (Integer.parseInt(variableBuscar) != 0))
				return contingut;
		}
		
		// proces de llegir la taula ranking per buscar segons les boles o nom
		FileReader fileReader = new FileReader("Ranking.txt");
		BufferedReader reader = new BufferedReader(fileReader);
		String linea;
		while((linea = reader.readLine()) != null) {
			String[] lineSplit = linea.split("[\\r\\n]+");
			String[] contentSplit = lineSplit[0].split(", ");
			if(nomVariable.equals("boles")) {
				if(contentSplit[1].contains(variableBuscar)) {
					contingut.put(contentSplit[0], contentSplit[1]);
					rankingsAux.put(contentSplit[0], contentSplit[2]);
				}
			} else {
				if(nomVariable.equals("nom")) {
					if(contentSplit[0].contains(variableBuscar)) {
						contingut.put(contentSplit[0], contentSplit[1]);
						rankingsAux.put(contentSplit[0], contentSplit[2]);
					}
				}
			}
		}
		
		rankingsAux.forEach((key, value) -> 
		contingut.merge(key, value, (v1, v2) -> v1.equalsIgnoreCase(v2)
				? v1
				: v1 + ", " + v2));
	return contingut;
	}
	
	// Funcio encarregada de llegir el fitxer que se li passa per parametre. En el
	// nostre cas volem llegir la taula de ranking i la taula de colors
	public LinkedHashMap<String, String> readTable(String filename) throws IOException {
		LinkedHashMap<String, String> informacio = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> rankingsAux = new LinkedHashMap<String,String>();
		Scanner entrada = new Scanner(filename);
		FileReader fileReader = new FileReader(filename);
		BufferedReader reader = new BufferedReader(fileReader);
		String linea;
		while((linea = reader.readLine()) != null) {
			String[] lineSplit = linea.split("[\\r\\n]+");
			String[] contentSplit = lineSplit[0].split(", ");
			switch (filename) {
			case "Colors.txt": 
				colorsDB.put(contentSplit[0], contentSplit[1]);
				informacio = colorsDB;
				break;
			case "Ranking.txt": 
				rankingsDB.put(contentSplit[0], contentSplit[1]);
				rankingsAux.put(contentSplit[0], contentSplit[2]);
				break;
			default: break;
			}
		}
		if(filename == "Ranking.txt") {
			rankingsAux.forEach((key, value) -> 
			rankingsDB.merge(key, value, (v1, v2) -> v1.equalsIgnoreCase(v2)
					? v1
					: v1 + ", " + v2));
				informacio = rankingsDB;
			}			
		return informacio; 
		}
		
	public LinkedHashMap<String, String> getRankingsDB() throws IOException {
		return readTable("Ranking.txt");
	}

	public LinkedHashMap<String, String> getColorsDB() throws IOException {
		return readTable("Colors.txt");
	}
}
