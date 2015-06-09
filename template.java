public class Damenproblem {
    private int n;
    private int[][] feld;
    public Damenproblem (int d) {
    ...
    }
    public void ausgabe() {
    ...
    }
    public boolean korrektPlatziert() {
    ...
    }
    static public void main (String[] arg) {
        Damenproblem dOk = new Damenproblem (4);
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