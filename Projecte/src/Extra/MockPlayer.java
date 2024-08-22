package Extra;

import Model.PlayerModel;

public class MockPlayer extends PlayerModel {
	
	// Funcio que simula la insercio d'un nom a la taula del ranking
	public boolean insertNom(String nom) {
		if(nom.equals("Eric")) {
			return true;
		} else {
			return false;
		}
	}
}
