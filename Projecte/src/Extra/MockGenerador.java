package Extra;
import java.util.LinkedHashMap;

public class MockGenerador extends Generador {
	Generador gen = new Generador();
	private LinkedHashMap<String, String> mapAux = new LinkedHashMap<String,String>();

	
	// Mètode específic per retornar els valors que volem, en funció dels colors, de cara al testing
	public String[] generaColors(int numColors) {
		switch (numColors) {
		case 5: 
			String[] llista = {"a","b","c","d","e"};
			return llista;
		case 6: 
			String[] llista2 = {"a","b","c","d","e","f"};
			return llista2;
		case 7:
			String[] llista3 = {"a","b","c","d","e","f","g"};
			return llista3;
		case 8: 
			String[] llista4 = {"a","b","c","d","e","f","g","h"};
			return llista4;
		default:
			String[] llista5 = {};
			return llista5;
		}
	}
	
	// Mètode específic per testejar la funció generaSolucio(), de cara al testing
	public LinkedHashMap<String, String> generaSolucio(int numColors, int boles) {
		if (numColors <= boles) {
			mapAux.clear();
			return mapAux;
		}
		if (numColors >= 5 && numColors <= 8) {
			switch (boles) {
			case 4: 
				mapAux.clear();
				for(int i = 0; i < 4; i++) {
					mapAux.put("Color"+i, "Inicial"+i);
				}
				return mapAux;
			case 5: 
				mapAux.clear();
				for(int i = 0; i < 5; i++) {
					mapAux.put("Color"+i, "Inicial"+i);
				}
				return mapAux;
			case 6: 
				mapAux.clear();
				for(int i = 0; i < 6; i++) {
					mapAux.put("Color"+i, "Inicial"+i);
				}
				return mapAux;
			case 7:
				mapAux.clear();
				for(int i = 0; i < 7; i++) {
					mapAux.put("Color"+i, "Inicial"+i);
				}
				return mapAux;
			default:
				mapAux.clear();
				return mapAux;
			}
		} else {
			mapAux.clear();
			return mapAux;
		} 
	}
	
    public void setGenerador(Generador gen)
    {
    	this.gen = gen;
    }
}
