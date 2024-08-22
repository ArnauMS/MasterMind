package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import View.GameView;


class TestGameView {

	@Test
	void testStartGame() throws IOException {
		// Explicació al gameview
		int res, expected;
		GameView gameView = new GameView();
		
		/** INICI PARTICIÓ EQUIVALENT **/
		
		// Returns possibles
		// 0 --> l'usuari ha acceptat jugar a la partida
		// 1 --> l'usuari no vol jugar
		// 2 --> l'usuari vol jugar i ha posat bé el nom (nom que no existeix) => CAS ESPERAT
		// 3 --> l'usuari vol jugar pero el nom introduit ja existeix
		// 4 --> l'usuari vol jugar pero el nom introduit no es vàlid (input no permes)
		
		
		// Al ser un mètode basat amb returns solament retornarà 0 o -1, cap altre cas serà contemplat
		 
		// VALORS VÀLIDS (valors permesos)
		expected = 0;
		res = gameView.StartGame();				
		assertEquals(res, expected);
		
		expected = -1;
		res = gameView.StartGame();				
		assertEquals(res, expected);
		
		// VALORS NO-VÀLIDS
		/*expected = -2;
		res = gameView.startGame();				
		assertEquals(res, expected);
		
		expected = 1;
		res = gameView.startGame();				
		assertEquals(res, expected);
		
		expected = 1000;
		res = gameView.startGame();				
		assertEquals(res, expected);
		
		expected = -39;
		res = gameView.startGame();				
		assertEquals(res, expected);*/
		
		
		/** FINAL PARTICIÓ EQUIVALENT + VALORS LÍMITS I FRONTERA **/
		
	}
	
	@Test
	void testGameLoop() throws IOException {
		// mirem intents actuals
		// comprovar que passa si arriba a 0
		// fem comprovacions del joc (pistes es corresponen a la jugada)
		
		GameView gm = new GameView();
		gm.GameLoop();
		
		
	}
	
	@Test
	void testEndGame() {
		// Explicació al gameview
		
	}



}
