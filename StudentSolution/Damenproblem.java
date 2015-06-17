public class Damenproblem {
    private int n;
    private int[][] feld;
    private int anzahlLoesungen;
    
    public Damenproblem (int d) {
		feld = new int[d][d];
		n = d;
		anzahlLoesungen=0;
    }
    public void ausgabe() {
	for (int row=0;row<n;row++) {
		for (int col=0;col<n-1;col++) {
			System.out.print(feld[row][col] + " ");
		}
		System.out.println(feld[row][n-1]);
	}
	System.out.println();    
    }
    public boolean korrektPlatziert() {	
		int queens=0;
		for(int col = 0; col < n; col++){
			for(int row = 0; row < n; row++){
				if(feld[row][col] == 1){
					queens++;
					if(istBedroht(row,col)) return false;
				}
			}
		}
		if (queens!=n) return false;
		return true;
    }
	// in main-Funktion aufzurufen mit platziere(0)
	void platziere(int col) {
		if (col==n) {
			anzahlLoesungen++;
			ausgabe();
		}
		else {
			for (int row=0;row<n;row++) {
				if (!istBedroht(row,col)) {
					feld[row][col]=1;
					platziere(col+1);
				}
				feld[row][col]=0;
			}
		}
	}
	public boolean istBedroht(int row, int col) {
		// Betrachtung der Bedrohungen "von links" genugt
		for (int i=0;i<col;i++) {
			// Bedrohung durch Dame in derselben Reihe
			if (feld[row][i]==1) return true;
			// Bedrohung durch Dame auf "negativer" Diagonalen
			if ((row-i-1)>=0 && feld[row-i-1][col-i-1]==1) return true;
			// Bedrohung durch Dame auf "positiver" Diagonalen
			if ((row+i+1)<n && feld[row+i+1][col-i-1]==1) return true;
		}
		for (int i=0;i<n;i++) {
			// Bedrohung durch Dame in derselben Spalte
			if ((i!=row)&&(feld[i][col]==1)) return true;
		}
		return false;
	}
    static public void main (String[] arg) {
		// Beispiel fur main-Funktion zum Testen
        Damenproblem dOk8 = new Damenproblem(8);
        dOk8.platziere(0);
        System.out.print("Es gibt " + dOk8.anzahlLoesungen + " Losungen fur das ");
        System.out.println(dOk8.n + "-Damenproblem.");
		
        Damenproblem dOk4 = new Damenproblem (4);
        dOk4.feld[1][0] = 1;
        dOk4.feld[3][1] = 1;
        dOk4.feld[0][2] = 1;
        dOk4.feld[2][3] = 1;
        if (dOk4.korrektPlatziert()) {
            System.out.println ("dOk ist eine moegliche Loesung!:");
        } else {
            System.out.println ("dOk ist keine erlaubte Loesung:");
        }
        dOk4.ausgabe();
    }
}