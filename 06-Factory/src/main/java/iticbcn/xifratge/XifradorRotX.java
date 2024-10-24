/*
 * Este programa contiene tres metodos, dos se encargan
 * de cifrar o descifrar un texto que reciban y el 
 * tercero recibira un texto y le aplicara todos lsos 
 * cifrados de rotacion posibles para intentar averiguar
 * cual es el cifrado que se ha utilizado en los dos 
 * otros metodos.
 */

 package src.main.java.iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    private static final char[] LOW = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();
    private static final char[] UPP = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ".toCharArray();

    // constructor
    public XifradorRotX() {}

    // creamos los metodos a implementar
    public TextXifrat xifra(String msg, String key) throws ClauNoSuportada {
        return null;
    }
    public String desxifra(TextXifrat msg, String key) throws ClauNoSuportada {
        return null;
    }

    // Este metodo a partir de un numero que reciba cifrara el texto
    public String xifraRotX(String msg, int codificacio) {
        if(codificacio>LOW.length) return "No es una codificacion valida";
        // creamos el string que tendremos que devolver
        StringBuilder resultado = new StringBuilder();
        // analizamos todos los caracteres del texot
        for(int i=0; i<msg.length(); i++) {
            char caracter = msg.charAt(i);
            // si el caracter es una letra mayuscula
            if(dentroAlfabeto(caracter, UPP)) resultado.append(xifraCaracter(trobaPos(caracter, UPP), codificacio, UPP));
            // si el caracter es una letra minuscula
            else if(dentroAlfabeto(caracter, LOW)) resultado.append(xifraCaracter(trobaPos(caracter, LOW), codificacio, LOW));
            // si no es una caracter dentro de nuestro alfabeto
            else resultado.append(caracter);
        }
        return resultado.toString();
    }

    // Este metodo a partir de un numero que reciba descifrara el texto
    public String desxifraRotX(String msg, int codificacio) {
        if(codificacio>LOW.length) return "No es una codificacion valida";
        // creamos el string que tendremos que devolver
        StringBuilder resultado = new StringBuilder();
        // analizamos todos los caracteres del texot
        for(int i=0; i<msg.length(); i++) {
            char caracter = msg.charAt(i);
            // si el caracter es una letra mayuscula
            if(dentroAlfabeto(caracter, UPP)) resultado.append(desxifraCaracter(trobaPos(caracter, UPP), codificacio, UPP));
            // si el caracter es una letra minuscula
            else if(dentroAlfabeto(caracter, LOW)) resultado.append(desxifraCaracter(trobaPos(caracter, LOW), codificacio, LOW));
            // si no es una caracter dentro de nuestro alfabeto
            else resultado.append(caracter);
        }
        return resultado.toString();
    }

    // Este metodo a partir de un numero que reciba cifrara el texto
    public void forçaBrutaRotX(String msg) {
        for(int i=0; i<LOW.length; i++) {
            System.out.println(xifraRotX(msg, i));
        }
    }

    // A partir de esta linia se situan todos los metodos de ayuda

    public boolean dentroAlfabeto(char caracter, char[] alfabeto) {
        for(int i=0; i<alfabeto.length; i++) {
            if(caracter==alfabeto[i]) return true;
        }
        return false;
    }

    public char xifraCaracter(int pos, int mov, char[] alfabeto) {
        int posFinal = pos + mov;
        if(posFinal>=alfabeto.length) posFinal-=alfabeto.length;
        return alfabeto[posFinal];
    }

    public char desxifraCaracter(int pos, int mov, char[] alfabeto) {
        int posFinal = pos - mov;
        if(posFinal<0) posFinal+=alfabeto.length;
        return alfabeto[posFinal];
    }

    public int trobaPos(char caracter, char[] alfabeto) {
        for(int i=0; i<alfabeto.length; i++) {
            if(caracter==alfabeto[i]) return i;
        }
        return -1;
    }

    // El main de este programa contendra test para los tres metodos codificados anteriormente
    /*
    public static void main(String[] args) {
        String[] test = {"Hola", "Prueba de cifrado!", "?.! âÀá íüÒé"};
        int[] valores = {1, 5, 7};
        for(String texto : test) {
            System.out.println("El texto inicial: " + texto);
            System.out.println();
            for(int codificacion : valores) {
                texto = xifraRotX(texto, codificacion);
                System.out.println("El texto codificado en rot("+ codificacion +"): " + texto);
                texto = desxifraRotX(texto, codificacion);
                System.out.println("El texto descodificado("+ codificacion +"): " + texto);
            }
            System.out.println("El texto codificado cifrado a fuerza bruta");
            forçaBrutaRotX(texto);
            System.out.println();
        }
    }
    */
}