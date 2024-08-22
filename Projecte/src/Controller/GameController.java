package Controller;

import Model.DatabaseModel;

import Model.GameModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import Model.PlayerModel;
import Model.RankingModel;

public class GameController { 
	
	private DatabaseModel db = new DatabaseModel(); 
	private PlayerModel pm = new PlayerModel();
	private GameModel gm = new GameModel();
	private BoardController bc = new BoardController();
	private PistesController pc = new PistesController();
	private LinkedHashMap<String, String> colorsShuffled = new LinkedHashMap<>();
	
	private RankingModel rm = new RankingModel();
 
  
	// Mètode per preguntar a l'usuari si vol jugar o no
	// Retorna true o false en funció del que respongui
	public boolean CheckPlayInput()
	{ 
		boolean askName = false;

		Scanner sc = new Scanner(System.in);
		String volJugar = sc.next();
		//
		boolean playInput = volJugar.matches("^([sSnN]){1}$");
		
		while (!playInput)
		{
			System.out.println("Format incorrecte, introdueix la resposta de nou (S/n): "); // es repeteix dos cops
			volJugar = sc.next();
			playInput = volJugar.matches("^([sSnN]){1}$");
		} 

		if (volJugar.equals("S") || volJugar.equals("s"))
			askName = true;
		
		return askName;
	}
	 
	// Mètode per preguntar el nom del jugador i guardar-lo, en cas que vulgui jugar
	// A través del paràmetre "wantPlay"
	public int CheckNameInput(boolean wantPlay) throws IOException {
		if (wantPlay) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEntra el teu nom: ");
			String userName = sc.nextLine();
			
			// Mínim dues lletres i màxim 7
			boolean format = userName.matches("^([a-zA-Z]){2,7}$");
			while (!format)
			{
				System.out.println("Format incorrecte, introdueix el nom de nou: ");
				userName = sc.next();
				format = userName.matches("^([a-zA-Z]){2,7}$");
			} 
			
			// Format correcte, passem a validar que el nom no existeixi a la BD
			String q = "nom=" + userName;
			Map<String, String> query = db.queryBD(q);

			// Si la query està buida, afegim el nou registre
			while (!query.isEmpty()) {
				System.out.println("El nom ja existeix, introdueix un altre: ");
				userName = sc.nextLine();
				q = "nom=" + userName;
				query = db.queryBD(q);
			} 
			pm.setPlayerName(userName);
			pm.insertNom(userName);
			
		} else {
			System.out.println("Ens sap greu que no vulguis jugar, que vagi bé!");
			return -1;
		}
		return 0;
	}
	
	 
	
	// Setteig de les boles i començament de la partida
	public int SetupGame(int value) throws IOException 
	{
		if (value == 0) 
		{
			System.out.println("Quantes boles vols que tinguin les files? (4,5,6,7)");
			Scanner sc = new Scanner(System.in);
			String numBoles = sc.next(); 
			
			
			boolean format = numBoles.matches("^([4567]){1}$");
			while(!format)
			{
				System.out.println("Format incorrecte, introdueix un número del 4 al 7, ambdós inclosos");
				numBoles = sc.next();
				format = numBoles.matches("^([4567]){1}$");
			} 
			
			// format correcte
			gm.setNumBoles(Integer.parseInt(numBoles));
			
			// El player disposarà de 7 intents per endevinar la solució
			gm.setAttempts(7);
			
			// Generem la solució
			gm.RandomRow(gm.getNumBoles()); 

			// Shuffle colors
			this.colorsShuffled = gm.ShuffleColors();
			
			return value;
		}
		
		return -1;
	} 
	 
	
	public void DrawIntroduction() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String toPrint = "El joc consisteix en endevinar els colors i la seva posició d'una fila de %s boles".formatted(gm.getNumBoles());
		System.out.println();
		System.out.println(toPrint);
		System.out.println();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("El format de les pistes serà el següent:");
		System.out.println("Un  (-) equival a encara no has omplert el color dins de la fila a la posició indicada");
		System.out.println("Una (X) equival a que el color està bé, però no en la posició que toca");
		System.out.println("Una (O) equival a que el color i la posició son correctes");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Per a cada posició de la fila et preguntarem quin color vols posar");
		System.out.println("IMPORTANT: Els colors no es poden repetir!");
		System.out.println();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Disposes de 7 intents per endevinar la solució");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
		System.out.println("El joc ha començat!!");
	}
		
		
	// Mètode per updatejar els intents de la partida, on se li passa per paràmetre els intents actuals
	public int UpdateAttempts(int attempts) 
	{
		if (attempts >= 1)
			gm.setAttempts(attempts - 1);
		else
			return 0;
		
		return gm.getAttempts(); 
	} 
	
	// Mètode que en funció de la fila de pistes que li passem (hintRow)
	// Verifica si hem guanyat o no la partida
	public boolean CheckWin(String rowToCheck) {
		String noSpaceRow = rowToCheck.replaceAll(" ", "");
		boolean reg = noSpaceRow.matches("^([O]){%d}$".formatted(gm.getNumBoles()));

		if (reg) 
			return true;
		
		return false;
	}
	
	
	// Mètode per actualitzar una fila que li passem
	// En base a userRow, hintRow i numRow, actualiza la userRow i la retorna per tal de pintar el nou taulell posteriorment
	public ArrayList<String> UpdateRow(ArrayList<String> userRow, ArrayList<String> hintRow, String numRow) throws IOException 
	{ 
		if ((userRow.size() != gm.getAttempts()))
			return null;
		
		
		if ((hintRow.size() != gm.getAttempts()))
			return null;
		
		
		if (numRow.isEmpty())
			return null;
		 
		System.out.println();
		boolean rowFull = false, canContinue = true;
		boolean hasWon = false;

		for (int i = 0; i < gm.getInitialAttempts(); i++) 
		{
			String iniJoined = "";
			for (String c : this.getColorsShuffled().values()) iniJoined += c;
			System.out.println("Initials: " + iniJoined);
			
			for (int j = 1; j <= gm.getNumBoles(); j++)
			{  
				Scanner sc = new Scanner(System.in);
				System.out.println("Introdueix quin color vols posar a la fila %d columna %d: ".formatted(i+1,  j));
				String userLetter = sc.next();
				
				
				boolean format = userLetter.matches("^([%s]){1}".formatted(iniJoined));
				while (!format)
				{ 
					sc = new Scanner(System.in);
					System.out.println("Format incorrecte, introdueix una de les inicials dels colors disponibles: ");
					userLetter = sc.next();
					format = userLetter.matches("^([%s]){1}".formatted(iniJoined));
				}
					    
				//sc.close();
				// Format OK -> afegim la lletra
				userRow.set(i, userRow.get(i).replaceFirst("[-]", userLetter));
				iniJoined = iniJoined.replace(userLetter, "");
				System.out.println();
				System.out.println("Initials restants: " + iniJoined); 
				
				// Comprovem la fila
				String rowSol = gm.getSolutioInitials();
				String hintToShow = hintRow.get(i);
				String newHints = pc.VerifyRow(userRow.get(i), hintToShow, rowSol, gm.getNumBoles());
				hintRow.set(i, newHints);
			} 
			rowFull = true; 
			
			if (rowFull)
			{
				bc.DrawBoard(numRow, userRow, hintRow);
				
				String hintR = hintRow.get(i).replaceAll("\s", "");
				hasWon = this.CheckWin(hintR);
				
				// Nom, Boles, Attempts
				// No entra si guanyem a la primera
				this.UpdateAttempts(gm.getAttempts()); 
				int newAttempts = gm.getInitialAttempts() - gm.getAttempts();
				
				String q = "%s, %d, %d".formatted(pm.getPlayerName(), this.GetBoles(), newAttempts);
				rm.updateRanking(q);
				 
				if (hasWon)
				{
					System.out.println("Has guanyat la partida!");
					System.out.println(new String(new char[70]).replace("\0", "\r\n"));
					canContinue = false;
					break;
					
				}
				else if (!hasWon && gm.getAttempts() == 0)
				{
					System.out.println("Has perdut la partida!");
					System.out.println(new String(new char[70]).replace("\0", "\r\n"));
					canContinue = false; 
					break;
				}
				rowFull = false; 
			}
		} 
		
		// En cas que el jugador no hagi perdut, li seguim restant intents
		if (canContinue)
		{
			System.out.println();
			System.out.println("Intents restants: " + gm.getAttempts());
		} 
		
		return userRow;
	}   
	
	// Mètode que verifica si en funció d'un input de l'usuari
	// Aquest vol seguir jugant o no
	public boolean RestartGame(String userInput) 
	{  
		Scanner sc = new Scanner(System.in);
		boolean restart = userInput.matches("^([sSnN]){1}$");
		
		while (!restart) 
		{
			System.out.println("Format incorrecte, introdueix la resposta de nou (S/n): "); // es repeteix dos cops
			userInput = sc.next();
			restart = userInput.matches("^([sSnN]){1}$");
		}
		
		if (userInput.equals("S") || userInput.equals("s"))
			return true; 
		else 
			return false;
		
	}
	
	// Mètode per pintar els colors disponibles
	public void DrawColors()
	{
		LinkedHashMap<String, String> aux = this.getColorsShuffled();
		
		System.out.println("Colors disponibles: ");
		for (String c : aux.keySet())
		{
			System.out.println(c + ", " +  aux.get(c)); 
		}
	}
	
	// Getter exclusiu de la vista
	public int GetBoles() {
		return gm.getNumBoles();
	}
	
	public LinkedHashMap<String, String> getColorsShuffled() {
		return colorsShuffled;
	}
	
}
