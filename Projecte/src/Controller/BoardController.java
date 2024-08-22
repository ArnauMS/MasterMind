package Controller;

import java.util.ArrayList;
import Model.GameModel;

public class BoardController {
	private GameModel gm = new GameModel();
	private String numericRow = "";
	private ArrayList<String> userRow = new ArrayList<String>(), hintRow = new ArrayList<String>();
	 
	// Mètode per inicialitzar els valors inicials que tindràn els 3 elements principals del taulell
	public int InitBoard(int boles) 
	{
		if (boles < 4 || boles > 7)
			return 0;
		
		String templateRow = "";
		System.out.println();  
		
		// Resetejem els diferents elements del taulell de cara al testing
		userRow = new ArrayList<>();
		hintRow = new ArrayList<>();
		numericRow = "";
		
		// Fill numRow and templateRow
		for (int i = 1; i <= boles; i++)
		{
			if (i == gm.getNumBoles())
			{
				numericRow += String.format("%d", i);
				templateRow += "-";
				String modNumRow = "  %s".formatted(numericRow);
				userRow.add(modNumRow);
			}
			else
			{
				numericRow += String.format("%d ", i);
				templateRow += "- ";
			}
				 
		}
		
		// Omplir les file de l'user i les files de pistes
		for (int i = 0; i < gm.getAttempts(); i++)
		{
			userRow.add(templateRow);
			hintRow.add(templateRow);
		}
	
		this.setNumericRow(numericRow);
		return numericRow.length(); 
	
	}
	  
	// Mètode per pintar el taulell en funció de les files de user, pistes i la fila númerica
	public int DrawBoard(String nRow, ArrayList<String> uRow, ArrayList<String> hRow) 
	{   
		System.out.println();
		System.out.println("  " + nRow);
		
		if (uRow.isEmpty())
			return 0;
		
		for (int i = 0; i < uRow.size(); i++) 
		{
			int rowNum = i+1;
			System.out.println("%d ".formatted(rowNum) + uRow.get(i)+ " | " + hRow.get(i));
		}
		
		return uRow.size();
	}
	 
	public ArrayList<String> getUserRow() {
		return userRow;
	}

	public String getNumericRow() {
		return numericRow;
	}

	public void setNumericRow(String numericRow) {
		this.numericRow = numericRow;
	}

	public ArrayList<String> getHintRow() {
		return hintRow;
	}

}
