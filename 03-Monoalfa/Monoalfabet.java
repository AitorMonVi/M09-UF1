/* */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabet {

    // inizializamos los dos alfabetos
    private static final char[] ALFABETO = {'a', 'à', 'á', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
    
    private static final char[] PERMUTADO = permutaAlfabet(ALFABETO);

    public static char[] permutaAlfabet(char[] alfa) {
        Character[] copia = new Character[alfa.length];
        for(int i=0; i<alfa.length; i++) {
            copia[i] = alfa[i];
        }
        List<Character> permutat = Arrays.asList(copia);
        Collections.shuffle(permutat);
        char[] resultado = new char[permutat.size()];
        for(int i=0; i<permutat.size(); i++) {
            resultado[i] = permutat.get(i);
        }
        return resultado;
    }

    public static String xifraMonoAlfa(String cadena) {
        // inicializamos el texto que devolveremos
        StringBuilder texto = new StringBuilder();
        // comenzamos a recorrer la cadena
        for(int i=0; i<cadena.length(); i++) {
            char caracter = cadena.charAt(i);
            // comprueba que el alfabeto tiene este caracter
            if(contiene(caracter)) texto.append(caracterPermutado(caracter, ALFABETO, PERMUTADO));
            // no lo tiene entonces no permutamos
            else texto.append(caracter);
        }
        return texto.toString();
    }

    public static String desxifraMonoAlfa(String cadena) {
         // inicializamos el texto que devolveremos
         StringBuilder texto = new StringBuilder();
         // comenzamos a recorrer la cadena
         for(int i=0; i<cadena.length(); i++) {
             char caracter = cadena.charAt(i);
             // comprueba que el alfabeto tiene este caracter
             if(contiene(caracter)) texto.append(caracterPermutado(caracter, PERMUTADO, ALFABETO));
             // no lo tiene entonces no permutamos
             else texto.append(caracter);
         }
         return texto.toString();
    }

    // A partir de esta linia existen funciones de ayuda para las funciones anteriores 
    public static char caracterPermutado(char caracter, char[] alfabeto, char[] permutado) {
        // nos guardamos si es mayuscula o no
        boolean mayus = Character.isUpperCase(caracter) ? true : false;
        // volvemos minuscula para analizar mejor
        caracter = Character.toLowerCase(caracter);
        // iniciamos la posicion
        int posicion = 0;
        // recorremos el alfabeto para ver que posicoin coincide
        for(int i=0; i<alfabeto.length; i++) {
            char buscado = alfabeto[i];
            // si coincide nos guardamos esta posicion
            if(buscado==caracter) posicion = i;
        }
        // si es mayusculas devolvemos en mayusuclas sino en minusculas
        return mayus ? Character.toUpperCase(permutado[posicion]) : permutado[posicion];
    }

    public static boolean contiene(char caracter) {
        // pasamos el caracter a minuscula para analizar
        caracter = Character.toLowerCase(caracter);
        // recorremos alfabeto
        for(char letra : ALFABETO) {
            if(letra==caracter) return true; // ha encontrado el caracter
        }
        // no contiene este caracter
        return false;
    }
    public static void main(String[] args) {
        
        String[] tests = {"Hola", "à ï ?,.ú", "AdIos pEPe", "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz"};

        for(String test : tests) {
            System.out.println(test);

            System.out.println(xifraMonoAlfa(test));

            System.out.println(desxifraMonoAlfa(xifraMonoAlfa(test)));
            System.out.println();
        }
        
    }       
}