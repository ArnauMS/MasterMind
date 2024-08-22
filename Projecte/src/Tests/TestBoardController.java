package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import Controller.BoardController;

class TestBoardController {
	BoardController bc = new BoardController();

	@Test
	void testDrawBoard() {
		int expected, res;
		String nRow = "";
		ArrayList<String> uRow=new ArrayList<>(), hRow = new ArrayList<>();
		bc.InitBoard(4);
		
		// PROVA DE CAIXA BLANCA
		
		// LOOP TESTING (d'un loop simple)
		
		// N_MAX = 7 = NUM_INTENTS
		 
		
		// Evitar el loop (n=0)
		expected = 0;
		res = bc.DrawBoard(nRow, uRow, hRow);	
		assertEquals(expected, res);
		
		
		// Passar 1 cop (n=1)
		expected = 1;
		uRow=new ArrayList<>();
		for (int i = 0; i < expected; i++)
			uRow.add("-");
		
		System.out.println("Size " + uRow.size());
		System.out.println("Size 2 " + hRow.size());
		res = bc.DrawBoard(bc.getNumericRow(), uRow, bc.getHintRow());	
		assertEquals(expected, res);
		
		// Passar 2 cops (n=2)
		expected = 2;
		uRow=new ArrayList<>();
		for (int i = 0; i < expected; i++)
			uRow.add("-");
		
		res = bc.DrawBoard(bc.getNumericRow(), uRow, bc.getHintRow());	
		assertEquals(expected, res);

		// Passar m < n vegades
		// 4 < 7
		expected = 4;
		uRow=new ArrayList<>();
		for (int i = 0; i < expected; i++)
			uRow.add("-");
		
		res = bc.DrawBoard(bc.getNumericRow(), uRow, bc.getHintRow());	
		assertEquals(expected, res);
		
		// Passar n-1 vegades
		// n-1 = 6
		expected = 6;
		uRow=new ArrayList<>();
		for (int i = 0; i < expected; i++)
			uRow.add("-");
		
		res = bc.DrawBoard(bc.getNumericRow(), uRow, bc.getHintRow());	
		assertEquals(expected, res);
		
		
		// Passar n vegades
		// n=7
		expected = 7;
		uRow=new ArrayList<>();
		for (int i = 0; i < expected; i++)
			uRow.add("-");
		
		res = bc.DrawBoard(bc.getNumericRow(), uRow, bc.getHintRow());	
		assertEquals(expected, res);
		
	}
	
	

	@Test
	void testInitBoard() {
		int exp, res;
		
		// PROVES DE CAIXA BLANCA
		
		
		// PARTICIÓ EQUIVALENT
		
		// Valors Vàlids
		
		// boles=4
		exp = 4;
		res = bc.InitBoard(4);
		assertEquals(res-exp, exp);
		
		// boles=5
		exp = 5;
		res = bc.InitBoard(5);
		assertEquals(res-exp, exp);

		
		// boles=6
		exp = 6;
		res = bc.InitBoard(6);
		assertEquals(res-exp, exp);
		
		
		// boles=7
		exp = 7;
		res = bc.InitBoard(7);
		assertEquals(res-exp, exp);
		
		
		
		// Valors No-Vàlids
		
		// boles=8
		exp = 0;
		res = bc.InitBoard(8);
		assertEquals(res, exp);
		
		
		// boles=0
		exp = 0;
		res = bc.InitBoard(0);
		assertEquals(res, exp);
		
		
		// boles=-1
		exp = 0;
		res = bc.InitBoard(-1);
		assertEquals(res, exp);
		
		
		// VALORS LÍMITS I FRONTERES
		
		
		// Valor frontera esquerra
		exp = 4;
		res = bc.InitBoard(4);
		assertEquals(res-exp, exp);
		
		// Valor límit + valor interior
		exp = 5;
		res = bc.InitBoard(5);
		assertEquals(res-exp, exp);

		// Valor límit + valor interior
		exp = 6;
		res = bc.InitBoard(6);
		assertEquals(res-exp, exp);
		
		
		// Valor frontera dreta
		exp = 7;
		res = bc.InitBoard(7);
		assertEquals(res-exp, exp);
				
		// Valor exterior
		exp = 0;
		res = bc.InitBoard(3);
		assertEquals(res-exp, exp);
		
		// Valor exterior
		exp = 0;
		res = bc.InitBoard(8);
		assertEquals(res-exp, exp);
		
	}
	

}
