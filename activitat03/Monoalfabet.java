/* */

public class Monoalfabet {

    // inizializamos los dos alfabetos
    private static final char[] alfabetUpp = "aàábcçdèéfghíïjklmnñòópqrstúüvwxyz".toCharArray();

    private static final char[] alfabetLow = "aàábcçdèéfghíïjklmnñòópqrstúüvwxyz".toUpperCase().toCharArray();

    public static char[] permutaAlfabet(char[] alfabet) {return null;}

    public static String xifraMonoAlfa(String cadena) {return null;}

    public static String desxifraMonoAlfa(String cadena) {return null;}

    // A partir de esta linia existen funciones de ayuda para las funciones anteriores 
    public static void main(String[] args) {
        for(char caracter : alfabetUpp) {
            System.out.print(caracter);
        }
        System.out.println();
        for(char caracter : alfabetLow) {
            System.out.print(caracter);
        }
    }
}