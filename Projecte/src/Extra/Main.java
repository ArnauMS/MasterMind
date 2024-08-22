package Extra;
import java.io.IOException;

import View.GameView;

public class Main {
	 public static void main(String[] args) throws IOException {
		 GameView gv = new GameView();
		 Generador g = new Generador();
		 /*g.generaColors(5);
		 g.generaColors(3);
		 g.generaColors(9);*/
		 
		 g.generaSolucio(8, 3);
		 g.generaSolucio(8, 5);
		 g.generaSolucio(8, 6);
		 g.generaSolucio(8, 9);
		 
		 g.generaSolucio(10, 3);
		 g.generaSolucio(2, 5);
		 g.generaSolucio(5, 7);
	        //code
	    }
}
