package Model;
// farà la query nom

import java.io.IOException;

import Model.DatabaseModel;

public class PlayerModel {
	private DatabaseModel db = new DatabaseModel();
	
	private String playerName = "";
	 
	public boolean insertNom(String nom) throws IOException {
		// Comprovem que el nom existeix o no a la bd llavors mostrem error o no	
		// si nom no existeix retornem un OK, etc

		db.setDatabase(db);
		return db.insertBD(nom + ", 0, 0");
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
