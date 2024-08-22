package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import Controller.GameController;
import org.junit.jupiter.api.Test;

class TestGameController {
	int expected, res=0;
	GameController gc = new GameController();
	
	@Test
	void testCheckPlayInput() throws IOException {
		
		boolean exp, res;
		
		// PARTICIONS EQUIVALENTS

		// VALORS VÀLIDS (valors permesos)
		
		
		// Cas input="s"
		exp = true;
		res = gc.CheckPlayInput(); 
		assertEquals(res, exp); 
		
		// Cas input="S"
		exp = true;
		res = gc.CheckPlayInput();
		assertEquals(res, exp); 
		
		// Cas input="n"
		exp = false;
		res = gc.CheckPlayInput();			
		assertEquals(res, exp);
		
		// Cas input="N"
		exp = false;
		res = gc.CheckPlayInput();			
		assertEquals(res, exp);
		
		  
		// VALORS NO-VÀLIDS
		// Al haver el while que pregunta constantment el format, pot optar al valor False i True
		
		// Cas on posem qualsevol cosa que no sigui S/n i acabem posant una S o s després de repetir el input
		exp = true;
		res = gc.CheckPlayInput();			
		assertEquals(res, exp);
		
		// Cas on posem qualsevol cosa que no sigui S/n i acabem posant una N o n després de repetir el input
		exp = false;
		res = gc.CheckPlayInput();			
		assertEquals(res, exp);
	}
	

	@Test
	void testCheckNameInput() throws IOException
	{
		int exp, res;
		boolean toSend;
		
		// PARTICIONS EQUIVALENTS
		
		
		// VALORS VÀLIDS
		
		// El player vol jugar i ha escrit el nom correctament
		toSend = false;
		exp = -1;
		res = gc.CheckNameInput(toSend);
		assertEquals(res, exp);
		
		// El jugador vol jugar i escriu el nom correctament
		toSend = true;
		exp = 0;
		res = gc.CheckNameInput(toSend);
		assertEquals(res, exp);
		
		// Validem que el nom del player no estigui repetit (manualment)
		toSend = true;
		exp = 0;
		res = gc.CheckNameInput(toSend);
		assertEquals(res, exp);
		
		// Validem que el nom del player tingui el format correcte (manualment)
		toSend = true;
		exp = 0;
		res = gc.CheckNameInput(toSend);
		assertEquals(res, exp);
		
		// Validem que el nom del player no tingui el format correcte (manualment)
		toSend = true;
		exp = 0;
		res = gc.CheckNameInput(toSend);
		assertEquals(res, exp);
		
		
	}
	
	
	@Test
	void testUpdateAttempts()
	{
		int exp, res, attempts;
		
		// PARTICIONS EQUIVALENTS
		
		// VALORS VÀLIDS -> aquells valors que no donguin una resta negativa
		
		attempts=3;
		exp = attempts-1;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		attempts=6;
		exp = attempts-1;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		attempts=8;
		exp = attempts-1;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		attempts=1;
		exp = attempts-1;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		
		// VALORS NO-VÀLIDS
		// Valors que esdevinguin un valor negatiu al fer el calcul d'intents-1
		
		attempts=-1;
		exp = 0;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		attempts=-2;
		exp = 0;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		attempts=0;
		exp = 0;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
		
		exp=attempts=-10;
		exp = 0;
		res = gc.UpdateAttempts(attempts);
		assertEquals(res, exp);
	}
	
	@Test
	void testCheckWin()
	{
		boolean exp, res;
		String row;
		
		// PARTICIONS EQUIVALENTS
		// Les boles (número de O) venen del GameModel (son 4 per ara)
		
		// Valors Vàlids
		exp = false;
		row = "- - - -";
		res = gc.CheckWin(row);
		
		
		// cas on guanyem
		exp = true;
		row = "O O O O";
		res = gc.CheckWin(row);
		
		// mínim 4 boles, per tant no es un cas vàlid
		exp = false;
		row = "O O O";
		res = gc.CheckWin(row);
		
		
		// valor incorrecte
		exp = false;
		row = "hola";
		res = gc.CheckWin(row);
		
		
	}
	
	@Test
	void testRestartGame()
	{
		boolean exp, res;
		
		// PARTICIÓ EQUIVALENT
		
		
		// Valors vàlids
		
		// L'input de l'usuari es "n"
		exp = false;
		res = gc.RestartGame("n");
		assertEquals(res, exp);
		
		
		// L'input de l'usuari es "N"
		exp = false;
		res = gc.RestartGame("N");
		assertEquals(res, exp);
		
		
		// L'input de l'usuari es "S"
		exp = true;
		res = gc.RestartGame("S");
		assertEquals(res, exp);
		
		
		// L'input de l'usuari es "s"
		exp = true;
		res = gc.RestartGame("s");
		assertEquals(res, exp);
		
		
		// Valors no-vàlids
		
		// Al preguntar per l'input fins que sigui vàlid, comproven tant quan el segon input sigui S/s com N/n
		
		// el segon input és manual
		exp = true;
		res = gc.RestartGame("sip");
		assertEquals(res, exp);
		
		
		// el segon input és manual
		exp = false;
		res = gc.RestartGame("nop");
		assertEquals(res, exp);
		
	}
	
	
	@Test
	void testSetupGame() throws IOException
	{
		int res, exp;
		
		// PARTICIONS EQUIVALENTS
		// Únicament es permet el valor 0, sino retorna -1
		
		
		// Valors vàlids
		exp = 0;
		res = gc.SetupGame(0);
		assertEquals(res, exp);
		
		
		// Valors no-vàlids -> tots aquells que no siguin 0
		exp = -1;
		res = gc.SetupGame(-1);
		assertEquals(res, exp);
		
		exp = -1;
		res = gc.SetupGame(5);
		assertEquals(res, exp);
		
		exp = -1;
		res = gc.SetupGame(2);
		assertEquals(res, exp);
		
		exp = -1;
		res = gc.SetupGame(1);
		assertEquals(res, exp);
		
		
	}
	
	 
	@Test
	void testUpdateRow() throws IOException {
		ArrayList<String> userRow = new ArrayList<>(), hintRow = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		String numRow;
		int exp, res;
		numRow = "1 2 3 4";
		//gc.UpdateRow(null, null, null);
		
		
		// Suposem que boles=4
		
		// Inicialització dels valors
		
		
		// PARTICIONS EQUIVALENTS
		
		
		
		// VALORS VÀLIDS
		
		// Pel paràmetre de userRow
		
		// Únic cas vàlid quan userRow es de mida 7
		
		userRow.clear();
		hintRow.clear();
		for (int i = 0; i < 7;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 7;
		res = gc.UpdateRow(userRow, hintRow, numRow).size();
		assertEquals(res, exp);
		
		
		// VALORS NO-VÀLIDS
		
		
		// Casos no vàlids --> aquells on userRow no tingui la mida dels intents
		userRow.clear();
		hintRow.clear();
		for (int i = 0; i < 5;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 0;
		result = gc.UpdateRow(userRow, hintRow, numRow);
		assertEquals(result, null);
		
		
		userRow.clear();
		hintRow.clear();
		for (int i = 0; i < 2;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 0;
		result = gc.UpdateRow(userRow, hintRow, numRow);
		assertEquals(result, null);
		
		
		
		
		// Pel paràmetre de hintRow
		
		
		// VALORS VÀLIDS
		
		// Pel paràmetre de hintRow
		
		// Únic cas vàlid quan hintRow es de mida 7
		
		hintRow.clear();
		userRow.clear();
		gc.UpdateAttempts(8);
		for (int i = 0; i < 7;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 7;
		
		res = gc.UpdateRow(userRow, hintRow, numRow).size();
		assertEquals(res, exp);
		
		
		// VALORS NO-VÀLIDS
		
		// Casos no vàlids --> aquells on userRow no tingui la mida dels intents
		userRow.clear();
		hintRow.clear();
		for (int i = 0; i < 5;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 0;
		result = gc.UpdateRow(userRow, hintRow, numRow);
		assertEquals(result, null);
		
		
		userRow.clear();
		hintRow.clear();
		for (int i = 0; i < 2;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 0;
		result = gc.UpdateRow(userRow, hintRow, numRow);
		assertEquals(result, null); 
		
		
		
		// VALORS VÀLIDS
		
		// Pel paràmetre de numRow
		
		// Té numeros fins el num de boles
		// per ex si boles=4 -> numRow = "1 2 3 4"
		
		hintRow.clear();
		userRow.clear();
		gc.UpdateAttempts(8);
		for (int i = 0; i < 7;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 7;
		
		res = gc.UpdateRow(userRow, hintRow, numRow).size();
		assertEquals(res, exp);

		
		
		// cas amb nRow = "1 2 3 4 5 6"
		numRow = "1 2 3 4 5 6";
		hintRow.clear();
		userRow.clear();
		gc.UpdateAttempts(8);
		for (int i = 0; i < 7;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		exp = 7;
		assertEquals(res, exp);
		
		
		numRow = "1 2 3 4 5";
		res = gc.UpdateRow(userRow, hintRow, numRow).size();
		hintRow.clear();
		userRow.clear();
		gc.UpdateAttempts(8);
		for (int i = 0; i < 7;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		
		assertEquals(res, exp);
		
		
		
		// Valors no-vàlids
		
		numRow = "";
		result = gc.UpdateRow(userRow, hintRow, numRow);
		hintRow.clear();
		userRow.clear();
		gc.UpdateAttempts(8);
		for (int i = 0; i < 7;i++)
		{
			userRow.add("- - - -");
			hintRow.add("- - - -");
		}
		
		assertEquals(res, exp);
		
	}
	
	

	

} 
