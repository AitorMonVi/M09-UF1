/* */

package src.main.java.iticbcn.xifratge;

import java.util.Random;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class XifradorPolialfabetic implements Xifrador {
    // creamos variables globals
    private static final long KEY = 186349724;

    private static final char[] ALFABETO = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();
    private static char[] permutado;

    private static Random random;

    // constructor
    public XifradorPolialfabetic() {}

    // creamos los metodos a implementar
    public TextXifrat xifra(String msg, String key) throws ClauNoSuportada {
        return null;
    }
    public String desxifra(TextXifrat msg, String key) throws ClauNoSuportada {
        return null;
    }

    // creamos los metodos principales
    public void permutaAlfabet() {
        Character[] copia = new Character[ALFABETO.length];
        // rellenamos la copia
        for(int i=0; i<ALFABETO.length; i++) {
            copia[i] = ALFABETO[i];
        }
        // creamos una list a partir de copia y le pasamos el shuffle
        List<Character> alfabeto = Arrays.asList(copia);
        Collections.shuffle(alfabeto, random);
        // inicializamos permutado
        permutado = new char[ALFABETO.length];
        // rellenamos permutado con el alfabeto pasado por shuffle
        for(int i=0; i<alfabeto.size(); i++) {
            permutado[i] = alfabeto.get(i);
        }
    }

    public String xifraPoliAlfa(String msg) {
        StringBuilder xifrat = new StringBuilder();
        initRandom();
        // recorremos el mensaje
        for(int i=0; i<msg.length(); i++) {
            // por cada vuelta modificamos permutado
            permutaAlfabet();
            //print();
            char caracter = msg.charAt(i);
            if(contiene(caracter)) xifrat.append(permuta(caracter, ALFABETO, permutado, Character.isUpperCase(caracter)));
            else xifrat.append(caracter);
        }
        return xifrat.toString();
    }

    public String desxifraPoliAlfa(String msg) {
        StringBuilder desxifrat = new StringBuilder();
        initRandom();
        // recorremos el mensaje
        for(int i=0; i<msg.length(); i++) {
            // por cada vuelta modificamos permutado
            permutaAlfabet();
            //print();
            char caracter = msg.charAt(i);
            if(contiene(caracter)) desxifrat.append(permuta(caracter, permutado, ALFABETO, Character.isUpperCase(caracter)));
            else desxifrat.append(caracter);
        }
        return desxifrat.toString();
    }

    // creamos los metodos secundarios
    public void initRandom() { random = new Random(KEY); }

    public boolean contiene(char caracter) {
        for(char letra : ALFABETO) {
            if(Character.toLowerCase(caracter)==letra) return true;
        }
        return false;
    }

    public char permuta(char letra, char[] origen, char[] sustituto, boolean mayus) {
        int posicion = -1;
        // encontramos la posicion en el alfabeto original y devolvemos la posicion en sustituto
        for(int i=0; i<origen.length; i++) {
            char buscado = origen[i];
            if(buscado==Character.toLowerCase(letra)) posicion=i;
        }

        return mayus ? Character.toUpperCase(sustituto[posicion]) : sustituto[posicion];
    }
    /* 
    public static void print() {
        for(char letra : permutado) {
            System.out.print(letra+", ");
        }
        System.out.println();
    }

    // creamos el main del programa
    
    public static void main(String[] args) {
        String[] test = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila",
            "Test 04 Cäräctërs ìnmùtäblës",
            "Test 05 TeXT amB MaYUscuLes"
        };

        String[] xifrat = new String[test.length];
        // printeamos
        System.out.println("Xifratge:\n---------");
        for(int i=0; i<test.length; i++) {
            xifrat[i] = xifraPoliAlfa(test[i]);
            System.out.printf("%-34s -> %s%n", test[i], xifrat[i]);
        }
        System.out.println();

        System.out.println("Desxifratge:\n------------");
        for(int i=0; i<test.length; i++) {
            String desxifrat = desxifraPoliAlfa(xifrat[i]);
            System.out.printf("%-34s -> %s%n", xifrat[i], desxifrat);
        }
    } 
    */
}