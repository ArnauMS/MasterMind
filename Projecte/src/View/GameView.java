package View;
//Vista del tot el joc: intents, taula, boles que es van col·locan, estat actual del joc,

import java.io.IOException;
import java.util.Scanner;
import Controller.BoardController;
import Controller.GameController;

public class GameView {
	private GameController gc = new GameController();
	private BoardController bc = new BoardController();
	private RankingView rv = new RankingView();
	
	public Integer StartGame() throws IOException {
		System.out.println("***** MASTERMIND *****");
		System.out.println("\n");
		System.out.println("Benvingut al nostre joc!!");
		System.out.println("\n");
		
		System.out.println("Vols jugar? (S/n) "); 
		
		boolean res = gc.CheckPlayInput();
		int inp = gc.CheckNameInput(res);
		gc.SetupGame(inp);

		return inp; 
	}
	 
	public Integer GameLoop() throws IOException {
		this.StartGame();
		System.out.println();
		 
		System.out.println("Instruccions: ");
		gc.DrawIntroduction();
		bc.InitBoard(gc.GetBoles()); 
		gc.DrawColors();
		bc.DrawBoard(bc.getNumericRow(), bc.getUserRow(), bc.getHintRow());
		gc.UpdateRow(bc.getUserRow(), bc.getHintRow(), bc.getNumericRow());
		 
		this.EndGame();
		
		return 0; 
	}
	 
	public int EndGame() throws IOException {
		rv.drawRanking(gc.GetBoles());
		
		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
		System.out.println("Vols jugar de nou? (S/n)");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		if (gc.RestartGame(s))
			this.GameLoop();
		
		sc.close();
		  
		return 0;
	}
	
}
