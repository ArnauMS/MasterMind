package Extra;
import Extra.Generador;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.ThreadLocalRandom;
import Model.DatabaseModel;

public class Generador {
	private LinkedHashMap<String, String> colorsDB = new LinkedHashMap<String,String>();
	private String[] colorsGen;
	private LinkedHashMap<String, String> solucio = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> mainColors = new LinkedHashMap<String, String>();
    
	private Generador gen;
    private DatabaseModel db = new DatabaseModel();
    
    public void setGenerador(Generador gen)
    {
    	this.gen = gen;
    }
    
    // Genera un llistat de colors randomitzats en funció del paràmetre que li passem
    // El paràmetre és el número de boles (colors diferents)
	public String[] generaColors(int numColors) throws IOException {
		this.setGenerador(gen);
		this.db.setDatabase(db);
		
		colorsDB = this.db.readTable("Colors.txt");
		this.colorsGen = new String[] {};
		int count = 0;
		 
		if (numColors < 5 || numColors > 8) 
			return this.colorsGen;
		
		this.colorsGen = new String[numColors];
		while (count != numColors)
		{
			int randPos = ThreadLocalRandom.current().nextInt(0, 7 + 1);
			String randColor = (String) colorsDB.keySet().toArray()[randPos];
			
			if (!Arrays.toString(colorsGen).contains(randColor))
			{
				colorsGen[count] = randColor;
				count++;
			} 
		}
		
		for (String colorName : colorsGen)
		{
			String initial = colorsDB.get(colorName);
			this.mainColors.put(colorName, initial);
		}
		
		return colorsGen;
	}
	 
	
	// Mètode per generar una solució per les partides
	// Rep com a paràmetre el número de colors amb els quals es generàra la solució
	// Rep també el número de boles (llargada de la solució)
	public LinkedHashMap<String, String> generaSolucio(int numColors, int boles) throws IOException {
		this.solucio = new LinkedHashMap<String, String>(); 
		
		if (numColors < 5 || numColors > 8) 
			return this.getSolucio();
		 
		if (boles < 4 || boles > 7)
			return this.getSolucio();
		
		if (boles >= numColors)
			return this.getSolucio();
		  
		this.setGenerador(gen);
		this.db.setDatabase(db);
		colorsDB = this.db.readTable("Colors.txt");
		colorsGen = this.generaColors(numColors);
		this.setColorsGen(colorsGen);
		String[] colors = this.getColorsGen();
		
		int it = 0;
		while (solucio.size() != boles) 
		{
			String initial = colorsDB.get(colors[it]);
			solucio.put(colors[it], initial);
			
			it++;
		}
		this.setSolucio(solucio);
		 
		// Colors possibles
		this.setColorsGen(colors);
		return this.getSolucio();
	}
	
	
	// Mètode per retornar tots els colors al llegir la BD (fitxer) dels colors
    public LinkedHashMap<String, String> getColors() throws IOException {
    	colorsDB = this.db.readTable("Colors.txt");
		return colorsDB;
	}

	public LinkedHashMap<String, String> getSolucio() {
		return solucio;
	}
 
	public void setSolucio(LinkedHashMap<String, String> solucio) {
		this.solucio = solucio;
	}
	
    public String[] getColorsGen() {
		return colorsGen; 
	}

	public void setColorsGen(String[] colorsGen) {
		this.colorsGen = colorsGen;
	}
	
	public LinkedHashMap<String, String> getMainColors() {
		return mainColors;
	}

}
