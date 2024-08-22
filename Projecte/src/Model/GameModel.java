package Model;
//farà query de les boles

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import Extra.Generador;

public class GameModel {
	private Generador gen = new Generador();
	private Map<String, String> rowSol;
	private int numBoles;
	private int attempts = 7;
	private final int initialAttempts = 7;
	
	
	// Mètode per generar una solucio aleatòria, cridant al mètode generador
	public Map<String, String> RandomRow(int boles) throws IOException {
		int numColors = 0;

		// En cas que les boles no estiguin en el rang permès
		// Eliminem la solució anterior per evitar problemes amb els tests
		if (boles < 4 || boles > 7)
		{
			this.ResetColorsGenerated();
			rowSol = Map.of();
			return null;
		}  

		numColors = boles+1;
		
		// Per ara que faci servir tots els colors per triar la solució
		Map<String, String> sol = this.gen.generaSolucio(numColors, boles);
		LinkedHashMap<String, String> aux = this.gen.getMainColors();
		
		this.setRowSol(sol);
		
		return sol; 
	}  
	
	// Mètode per obtenir la inicial d'un color que li passem per paràmetre
	// Per exemple, si color = groc, el mètode retornarà una g
	public String GetColorInitial(String color) throws IOException
	{
		// Comunicació amb la BD dels colors
		LinkedHashMap<String, String> colorsBD = gen.getColors();
		if (!colorsBD.containsKey(color))
			return null;		
		
		String initial = colorsBD.get(color);
		return initial;
	} 
	 
	
	// Funció per "refer" el llistat de colors, per tal que no estigui en el mateix ordre que la solució
	// La idea és que remeni o regiri tots els colors i que retorni un llistat però amb tot canviat
	public LinkedHashMap<String, String> ShuffleColors() throws IOException {
		LinkedHashMap<String, String> colorsGenShuffled = new LinkedHashMap<>();
		
		String[] myCols = this.getColorsGenerated();
		
		// Mitjançant la llibreria "Collections" fem ús del mètode shuffle() per poder "capgirar" els colors del llistat
		List<String> col = Arrays.asList(myCols);
		Collections.shuffle(col);
		String[] colorsShuffled = col.toArray(new String[col.size()]);
		
		for (String c : colorsShuffled)
			colorsGenShuffled.put(c, this.GetColorInitial(c));
		
		return colorsGenShuffled; 
	}
 
	public Map<String, String> getRowSol() {
		return rowSol;
	}
	
	public String[] getColorsGenerated()
	{
		return this.gen.getColorsGen();
	}
	
	// Mètode per reiniciar els colors generats, per tal que els tests no donin problemes
	public void ResetColorsGenerated()
	{
		String[] aux = new String[] {};
		this.gen.setColorsGen(aux);
	}
	
	  
	// Mètode per obtenir les inicials d'una solució generada prèviament
	public String getSolutioInitials()
	{
		String str = "";
		for (String s : this.getRowSol().values())
		{
			str += s;
		}
		return str;
	}
 
	public void setRowSol(Map<String, String> rowSol) {
		this.rowSol = rowSol;
	}

	public int getNumBoles() {
		return numBoles;
	}

	public void setNumBoles(int numBoles) {
		this.numBoles = numBoles;
	} 

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getInitialAttempts() {
		return initialAttempts;
	}
	
}
