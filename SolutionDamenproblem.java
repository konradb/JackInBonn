import de.uni_due.s3.jack2.backend.checkers.tracingchecker.framework.*;
import de.uni_due.s3.jack2.backend.checkers.tracingchecker.framework.TracingFramework.Test;

public class SolutionDamenproblem {
    private int n;
    private int[][] feld;
	
    public SolutionDamenproblem (int d) {
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
	
	private int[] intermediateResults = new int[4];	
	
	@Test(name="test aufgabe1")
	public void testAufgabe1(){
		SolutionDamenproblem dOk = new SolutionDamenproblem(8);
		Damenproblem studentdOk = new Damenproblem(8);
		if (studentdOk != null) {
			intermediateResults[0]=2;
		} else {
			TracingFramework.printError("Die Methode sum erzeugt weder das richtige Muster noch die richtigen Zahlen. <table border=\"1\" style=\"width:100%;border:1px dotted;border-collapse:collapse;\"><tr><td>Deine Ausgabe</td><td>Erwartete Ausgabe</td></tr><tr><td><pre> null </pre></td><td><pre>not null</pre></td></tr></table>");
		}
	}
	
	@Test(name="test aufgabe2")
	public void testAufgabe2(){
		SolutionDamenproblem dOk = new SolutionDamenproblem (4);
        dOk.feld[1][0] = 1;
        dOk.feld[3][1] = 1;
        dOk.feld[0][2] = 1;
        dOk.feld[2][3] = 1;
		
		Damenproblem studentdOk = new Damenproblem (4);
        studentdOk.feld[1][0] = 1;
        studentdOk.feld[3][1] = 1;
        studentdOk.feld[0][2] = 1;
        studentdOk.feld[2][3] = 1;
		
		dOk.ausgabe();
		studentdOk.ausgabe();
		
		if (studentdOk != null) {
			intermediateResults[1]=4;
		} else {
			TracingFramework.printError("Die Methode sum erzeugt weder das richtige Muster noch die richtigen Zahlen. <table border=\"1\" style=\"width:100%;border:1px dotted;border-collapse:collapse;\"><tr><td>Deine Ausgabe</td><td>Erwartete Ausgabe</td></tr><tr><td><pre>"+studentdOk.ausgabe()+" </pre></td><td><pre>"+dOk.ausgabe()+"</pre></td></tr></table>");
		}
	}
	
	@Test(name="test aufgabe3")
	public void testAufgabe3(){
		SolutionDamenproblem dOk = new SolutionDamenproblem (4);
        dOk.feld[1][0] = 1;
        dOk.feld[3][1] = 1;
        dOk.feld[0][2] = 1;
        dOk.feld[2][3] = 1;
		
		Damenproblem studentdOk = new Damenproblem (4);
        studentdOk.feld[1][0] = 1;
        studentdOk.feld[3][1] = 1;
        studentdOk.feld[0][2] = 1;
        studentdOk.feld[2][3] = 1;
        		
		
		if (studentdOk.korrektPlatziert() == dOk.korrektPlatziert()) {
			intermediateResults[2]=7;
		} else {
			if (dOk.korrektPlatziert())) {
				TracingFramework.printError("Die Methode sum erzeugt weder das richtige Muster noch die richtigen Zahlen. <table border=\"1\" style=\"width:100%;border:1px dotted;border-collapse:collapse;\"><tr><td>Deine Ausgabe</td><td>Erwartete Ausgabe</td></tr><tr><td><pre>" + studentdOk.korrektPlatziert() + "</pre></td><td><pre>" + dOk.korrektPlatziert() + " ,Das ist eine moegliche Loesung!</pre></td></tr></table>");
			} else {
				TracingFramework.printError("Die Methode sum erzeugt weder das richtige Muster noch die richtigen Zahlen. <table border=\"1\" style=\"width:100%;border:1px dotted;border-collapse:collapse;\"><tr><td>Deine Ausgabe</td><td>Erwartete Ausgabe</td></tr><tr><td><pre>" + studentdOk.korrektPlatziert() + "</pre></td><td><pre>" + dOk.korrektPlatziert() + " ,Das ist keine erlaubte Loesung!</pre></td></tr></table>");
			}
		}
	}
	
	@Test(name="test aufgabe4")
	public void testAufgabe4(){
		SolutionDamenproblem dOk = new SolutionDamenproblem(8);
        dOk.platziere(0);
        System.out.print("Es gibt " + dOk.anzahlLoesungen + " Losungen fur das ");
        System.out.println(dOk.n + "-SolutionDamenproblem.");
		
		Damenproblem studentdOk = new Damenproblem(8);	
		studentdOk.platziere(0);
        System.out.print("Es gibt " + studentdOk.anzahlLoesungen + " Losungen fur das ");
        System.out.println(studentdOk.n + "-SolutionDamenproblem.");
		
		if ((dOk.platziere(0) != studentdOk.platziere(0)) || (dOk.anzahlLoesungen != studentdOk.anzahlLoesungen) || (dOk.n != studentdOk.n)) {
			TracingFramework.printError("Die Methode sum erzeugt weder das richtige Muster noch die richtigen Zahlen. <table border=\"1\" style=\"width:100%;border:1px dotted;border-collapse:collapse;\"><tr><td>Deine Ausgabe</td><td>Erwartete Ausgabe</td></tr><tr><td><pre>Es gibt " + studentdOk.anzahlLoesungen + " Losungen fur das " + studentdOk.n + "-SolutionDamenproblem.</pre></td><td><pre>Es gibt " + dOk.anzahlLoesungen + " Losungen fur das " + dOk.n + "-SolutionDamenproblem.</pre></td></tr></table>");
		} else {
			intermediateResults[3]=7;
		}
	}
	
	public int getResult(){
		int sum = 0;
		for (int i:intermediateResults){
			sum+=i;
		}
		return sum;
	}
	
    static public void main (String[] arg) {
		// Beispiel fur main-Funktion zum Testen
        SolutionDamenproblem dOk = new SolutionDamenproblem(8);
        dOk.platziere(0);
        System.out.print("Es gibt " + dOk.anzahlLoesungen + " Losungen fur das ");
        System.out.println(dOk.n + "-SolutionDamenproblem.");
		
        SolutionDamenproblem dOk = new SolutionDamenproblem (4);
        dOk.feld[1][0] = 1;
        dOk.feld[3][1] = 1;
        dOk.feld[0][2] = 1;
        dOk.feld[2][3] = 1;
        if (dOk.korrektPlatziert()) {
            System.out.println ("dOk ist eine moegliche Loesung!:");
        } else {
            System.out.println ("dOk ist keine erlaubte Loesung:");
        }
        dOk.ausgabe();
    }
}