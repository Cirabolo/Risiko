package Game;
//[] serve ad indicare la funzione di quello che andrò ad aggiungere
//MANCA: carte, spostamento, assegnazione obiettivi e lista obiettivi + check vittoria
//Aggiungi meccanismo che permette di dire che ha vinto attraverso i punti assegnati a ciascun territorio. Opzione valida in caso non si voglia terminare la partita prima che qualcuno abbia raggiunto l'obiettivo

import java.util.*;
@SuppressWarnings("unused")
public class Risk {

	public static void main(String[] args) 
	{
		//FASE INIZIALE
		int[] g = {0, 0, 0, 0, 0, 0, 0}; //da 0-5 giocatori che giocano e che non giocano; 6 = numero totale
		giocatori(g); //riempio chi gioca con la funzione
		
		
		int[][] D = new int[6][21];
		
		for (int i=0; i<6; i++)
		{
			if (g[i]==0) //se il giocatore non gioca riempi la sua riga in D con -1
				for (int j=0; j<21; j++)
					D[i][j] = -1;
			else //altrimenti riempio la sua riga in D con -1
				for (int j=0; j<21; j++)
					D[i][j] = 0;
		}
		
		distribuzioneTerritori(g, D);
		
		//ora devo spostare i territori pescati "nelle mani dei giocatori", nella matrice territori che verrà utilizzata durante la fase di gioco
		int[][] T = new int[2][42];
		conversioneTerritori(g, D, T); //fase A (più fasi B e C)
		
		//B e C (A si può gestire indipendentemente da B e C
		
		int t; //numero territori di ciascun giocatore: riutilizzabile
		int a; //n° armate totali per giocatore non ancora sottratte
		int[] ar = new int[6]; //vettore armate rimaste
		
			//in base al numero di giocatori assegno tot carriarmati (non ancora sottratti)
		if (g[6]==3)
			a = 35;
		else
		{
			if (g[6]==4)
				a = 30;
			else
			{
				if (g[6]==5)
					a = 25;
				else
				{
					if (g[6]==6)
						a = 20;
				}
			}
		}

			
		for (int i=0; i<6; i++)
		{
			if (g[i]==0)
			{
				
			}
			else
			{
				int q = 0;
				
				for (int j=0; j<21; j++)
				{
					if(D[i][j]>0)
						q = q + 1;
					else
					{
						
					}
				}
				
				ar[i] = a - q; //stabilisco quanti carriarmati posso mettere, avendo un carroarmato per ogni territorio posseduto (verrà aggiunto automaticamente nella tabella territori)
				
			}
		}
				
		//NB: gestisci bene questo passaggio: mettili tutti insieme: fai tutti e tre per un giocatore e poi passa al prossimo. Usa la variabile t

		//[distribuzione obiettivi attraverso un array unidimensionale]
		//[lista e check obiettivi che utilizza l'array unidimensionale estratto]
		//[fase dove i giocatori scelgono dove distribuire i propri carriarmati]
		
		
		
		
		//FASE A TURNI
		
		
		//posso fare iniziare il turno a caso
		Random random = new Random();
		int P = random.nextInt(6);
		int turn = 1;
		int newTurn;
			if (P==0)
				newTurn = 5;
			else
				newTurn = P - 1;
		
		boolean win = false;
		
		while (win = false)
		{
			
			if (turn > 1)
			{
				//AGGIUNTA CARRIARMATI: check carriarmati + check continenti + carte attivate
			}
				
			
			//FASE 1 = conta n° armate da aggiungere + processo attraverso il quale vengono aggiunte (devo impedire che il giocatore rimescoli i carriarmati a suo piacere: utilizzo un altro vettore)

			
			//FASE 2 e 3
			boolean bp = true;
			while (bp = true) 				//bp rimane falso a meno che non concludo con un pulsante oppure attraverso uno spostamento
			{
				int A = -1;
				int B = -1;
				A = ID territorio cliccato; //SE CLICCO UN MIO TERRITORIO A (0-41)
				if (T[0][A]==P)
					{
						//clicco un territorio B
						if (B==A) //se riclicco a OPPURE una crocetta che compare sul territorio A
							A = -1; //annullo il territorio selezionato
						else
						{
							if (T[0][A]==T[0][B]) //CLICCO UN MIO TERRITORIO DIVERSO DA QUELLO ATTIVATO
							{
								if (T[1][A]==1)
									//[messaggio di errore]
								else
								{
									//[IF: check che per sicurezza chiede se voglio eseguire lo spostamento e dicendo che concluderà il turno]
										spostamento(T, A, B);
										bp = false;
								}
							}
							else B //CLICCO UN TERRITORIO NON MIO
							{
								attaccoDifesa(T, A, B);
							}
						}
					}
				else
				{
					
				}
			}

			if (P==newTurn)
				turn = turn + 1;
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int[] giocatori(int[] g) //mi permette di scegliere giocatori in imput
	{
		boolean ok1 = false;
		boolean ok2 = false;
		while (ok1 = false)
		{
			while (ok2 = false) // chiedo attraverso un interfaccia quali giocatori avere
				{	
				//[interfaccia con input giocatori] (es)Se Rosso gioca, il valore nel vettore corrispondente al rosso = 0 -> 1.
				for (int i=0; i <6; i++)
				{
					g[6] = g[6] + g[i]; //uso g[6] come sommatoria. Sommo tutti i giocatori che giocano (=1) nell'ultima posizione del vettore
				}
				if (g[6] >= 3) //se ho almeno giocatori posso giocare
					ok2 = true;
				else // altrimenti no
					ok2 = false;
				}
			
			//[conferma? - interfaccia permette di scegliere sì o no cliccando; si => ok1 = true, no => ok1 = false]
			
		}	
	}
	
	
	
	
	
	public static void distribuzioneTerritori(int[] g, int[][] D)
	{
		//NB: ID territori va da 1 a 42, non da 0 a 41. Questo per evitare conflitti con le righe di giocatori che giocano (=0) e di quelli che non giocano (=-1)
		boolean ok = false; //true se si ha pescato tutto il mazzo
		int r = 42; //territori rimasti da pescare
		int t = 0; //n° cardinale territorio posseduto (è il numero della colonna di D
		int[] vt = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,  1, 1};
		
		while(ok = false)
		{
			for (int i=0; i<6; i++)
			{
				if (g[i]==-1) //se sono alla riga di un giocatore che non gioca, allora non faccio niente
				{
					
				}
				else //fase di pesca di un territorio
				{	
					boolean p = false;
					while (p = false)
					{
						Random random = new Random(); 
						int x;
						x = random.nextInt(42);
						
						if (vt[x]==0) //se ho già pescato quel territorio semplicemente ritorno all'inizio del while e ripesco
						{
							
						}
						else //se pesco un territorio che non avevo già pescato prima
						{
							D[i][t] = x + 1; //(non è x perché l'ID territorio in D[][], a differenza di come è in vt[], va da 1 a 42, e non da 0 a 41) assegno il territorio pescato al giocatore che pesca, all'interno della matrice distribuzione
							vt[x] = 0; //tolgo la carta dal mazzo
							p = true; //dico che ho pescato la carta
							r = r - 1; //diminuisco di uno le carte rimaste
						}
					}
				}
				
				if (r==0) //può succedere che i territori da pescare finiscano a "metà giocatori"
				i = 5;
			}
				
			if (r==0)
			{
				ok = true;
			}
			
			t=t+1;
		}
	}
	
	
	public static void conversioneTerritori(int[] g, int[][] D, int [][]T) //includi fase B e C (vedi il quaderno)
	{
		//A
		int c = 42;
		while (c > 0)
		{
			for (int i=0; i<6; i++)
			{
				for (int j=0; j<21; j++)
				{
					if (D[i][j]>0)
					{
						T[0][D[i][j]] = i;
					}
					else
					{
						
					}
				}
			}
		}

	}
	
	
	
	
	
	
	public static void spostamento(int[][] T, int A, int B) //fai attenzione ai roofs
	{
		
	}
	
	
	
	
	
	
	public static void attaccoDifesa(int[][] T, int A, int B) //il menù avrà sempre la possibilità di uscire attraverso una CROCETTA
	{
		//calcolo numero armate disponibili
		int a = T[1][A] - 1;
		int b = T[1][B];
		
		//casi in cui le armate siano più di 3
		if (a>=3)
			a = 3;
		if (b>=3)
			b = 3;

		int vA[] = {0, 0, 0};
		int vB[] = {0, 0, 0};
		int lancioA = 0;
		int lancioB = 0;
		int ndadiA; //1-3 attraverso interfaccia
		int ndadiB; //1-3 attraverso interfaccia
		int rimastiA = ndadiA;
		//[SCELTA DADI DA LANCIARE => TRUPPE DA SCHIERARE attraverso interfaccia]
		
		int x = 0; //posizione nel vettore vA[] e vB[]
		Random random = new Random();
		
		while (ndadiA>0)
		{
			vA[x] = random.nextInt(6) + 1;
			x = x + 1;
			ndadiA = ndadiA - 1;
		}
		
		x = 0;
		
		while (ndadiB>0)
		{
			vB[x] = random.nextInt(6) + 1;
			x = x + 1;
			ndadiB = ndadiB - 1;
		}
		
		
		//[RIORDINO I VETTORI DA MAGGIORE AL MINORE]
		//confronto i vettroi
		
		if (vB[0] >= vA[0])
			{
			T[1][A] = T[1][A] - 1;
			rimastiA = rimastiA - 1;
			}
		else
			T[1][B] = T[1][B] - 1;
		
		if (vB[1]==0 || vA[1]==0)
		{
			
		}
		else
		{
			if (vB[1] >= vA[1])
			{
				T[1][A] = T[1][A] - 1;
				rimastiA = rimastiA - 1;
			}
			else
				T[1][B] = T[1][B] - 1;
		}
		
		if (vB[2]==0 || vA[2]==0)
		{
			
		}
		else
		{
			if (vB[2] >= vA[2])
			{
				T[1][A] = T[1][A] - 1;
				rimastiA = rimastiA - 1;
			}
			else
				T[1][B] = T[1][B] - 1;
		}
		
		//in caso di vittoria
		if (T[1][B]==0) //se ho sconfitto B (NB: è impossibile sconfiggere B e rimastiA = 0)
		{
			T[0][B] = T[0][A]; //il possessore di A diventa possessore di B
			chiedo al giocatore quanti carriarmati vuole spostare (rimastiA + (un numero compreso tra 0 e (T[1][A] - rimastiA - 1)))
		}
	}
}
