package Tests;


import Extra.MockInput;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controller.BoardController;
import Controller.GameController;
import Model.GameModel;

class TestGame {
	BoardController board = new BoardController();
	GameController game = new GameController();
	GameModel gameM = new GameModel();
	MockInput mi = new MockInput();
	ArrayList<String> userRow = new ArrayList<String>();
	ArrayList<String> hintRow = new ArrayList<String>();
	
	@Test
	void testUpdateRow() throws IOException {
		board.InitBoard(5);
		userRow = board.getUserRow();
		hintRow = board.getHintRow();
		gameM.setAttempts(0);
		game.UpdateRow(userRow, hintRow, "4");
	}
	
}
