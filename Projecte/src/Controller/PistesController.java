package Controller;

public class PistesController {
	
	// Mètode per verificar quines pistes s'han de mostrar al taulell en funció, segons el userRow
	public String VerifyRow(String userRow, String hintRow, String solutionRow, int numBoles) {
		// Comparem cada fila per tal de pintar les pistes que toquen
		// Parsejem els diferents paràmetres per que no tinguin espais
		String userParsed = userRow.replaceAll("\s", "");
		String solutionParsed = solutionRow.replaceAll("\s", "");
		String hintParsed = hintRow.replaceAll("\s", "");
		char[] chHint = hintParsed.toCharArray();
		
		boolean samePos = false; 
		
		// Iterem sobre el número de boles (columnes) que té el taulell actuall
		for (int i = 0; i < numBoles; i++)
		{ 
			char userLetter = userParsed.charAt(i);
			String userLetterStr = Character.toString(userLetter);
			char solLetter = solutionParsed.charAt(i);

			// Cas 1: lletra del user està a la solució però no a la mateixa posició
			if (userLetter != solLetter) {
				if(solutionRow.contains(userLetterStr)) {
					// Same letter, different pos
					chHint[i] = 'X';
					samePos = false; 
				} 
			}
			// Cas 2: lletra del usuari coincideix amb la solució i a la mateixa posició
			else { 
					// Same letter, same pos
					chHint[i] = 'O';
					samePos = true;
			}
		}
		// Cas 3: si la lletra no existeix a la solució, no es fa res, es deixa el "-" com està
		hintRow = String.valueOf(chHint).replaceAll("", " ").trim();
		return hintRow;
	}
}
