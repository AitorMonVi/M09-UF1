/* 
 * Este programa recibe un texto y es capaz de codificarlo y descodificarlo
 */

public class Rot13 {
    public static void main(String[] args) {
        //char[] letras = abecedario(true);
        //print(letras);
        String[] test = {"Hola", "Adios", "Prueba de cifrado!", "Que tal?", "ÀÁ, ÈÉ, ÍÏ, ÒÓ, ÚÜ, Ç."};
        for(String text : test) {
            System.out.println("Texto inicial: " + text);
            text = xifraRot13(text);
            System.out.println("Texto cifrado: " + text);
            text = desxifraRot13(text);
            System.out.println("Texto descifrado: " + text);
            System.out.println();
        }    
    }
    //Este metodo codifica la texto que recibe con rot13
    public static String xifraRot13(String text) {
        // inicializamos los dos abecedarios junto con el texto que devolveremos
        char[] min = abecedario(false);
        char[] may = abecedario(true);
        String xifrat = "";
        // recorremos el texto recibido para ir modificando los caracteres
        for(int i=0; i<text.length(); i++) {
            char caracter = text.charAt(i); // declaramos el caracter a modificar
            // si el carecter esta en mayusculas
            if(Character.isUpperCase(caracter)) {
                // envia al metodo la array de letras mayusculas y envia true pq queremos cifrar
                xifrat+=caracterXifrat(may, caracter, true);
            }
            // si el caracter esta en minusculas
            else if(Character.isLowerCase(caracter)) {
                // envia al metodo la array de letras minusculas y envia true pq queremos cifrar
                xifrat+=caracterXifrat(min, caracter, true);
            }
            // si no es una letra lo devolvera sin ser modificado
            else xifrat+=caracter;
        }
        // devuelve el texto despues de haberlo cifrado
        return xifrat;
    }
    //
    public static String desxifraRot13(String text) {
        // inicializamos los dos abecedarios junto con el texto que devolveremos
        char[] min = abecedario(false);
        char[] may = abecedario(true);
        String xifrat = "";
        // recorremos el texto recibido para ir modificando los caracteres
        for(int i=0; i<text.length(); i++) {
            char caracter = text.charAt(i); // declaramos el caracter a modificar
            // si el carecter esta en mayusculas
            if(Character.isUpperCase(caracter)) {
                // envia al metodo la array de letras mayusculas y envia false pq queremos descifrar
                xifrat+=caracterXifrat(may, caracter, false);
            }
            // si el caracter esta en minusculas
            else if(Character.isLowerCase(caracter)) {
                // envia al metodo la array de letras minusculas y envia false pq queremos descifrar
                xifrat+=caracterXifrat(min, caracter, false);
            }
            // si no es una letra lo devolvera sin ser modificado
            else xifrat+=caracter;
        }
        // devuelve el texto despues de haberlo descifrado
        return xifrat;
    }
    //Este metodo modifica el caracter que recibe dependiendo de si se quiere cifrar 
    public static char caracterXifrat(char[] letras, char caracter, boolean cifrado) {
        // inicializa el caracter que se devolvera y la posicion que tendra el caracter recibido en la array
        char xifrat;
        int pos = 0 ;
        // si cifrado es true
        if(cifrado){
            // recorre la array para encontrar la posicion del caracter
            for(int i=0; i<letras.length; i++) {
                if(letras[i]==caracter) pos = i;
            }
            // como quiere cifrar sumamos 13
            pos+=13;
            // si pos es mas grande que la array
            if(pos>=letras.length) {
                // restamos la longitud de la array
                pos-=letras.length;
            }
            // declaramos que el caracter cifrado es la posicion pos en la array
            xifrat=letras[pos];
        }
        // si cifrado es false
        else {
            // recorre la array para encontrar la posicion del caracter
            for(int i=0; i<letras.length; i++) {
                if(letras[i]==caracter) pos = i;
            }
            // como quiere cifrar restamos 13
            pos-=13;
            // si pos es mas pequeño que zero
            if(pos<0) {
                // sumamos la longitud de la array
                pos+=letras.length;
            }
            // declaramos que el caracter cifrado es la posicion pos en la array
            xifrat=letras[pos];
        }
        // devolvemos el caracter ya modificado
        return xifrat;
    }
    //Este metodo devuelve un lista con las letras del abecedario en mayusculas o minusculas
    public static char[] abecedario(boolean mayusculas) {
        // generamos la array con todas las letras del abecedario
        char[] letras = "aàábcçdeèéfghiíïjklmnñoòópqrstuúüvwxyz".toCharArray();
        if(mayusculas) { // si la array tiene que ser mayusculas
            // declaramos la array de mayusculas
            char[] may = new char[letras.length];
            // recorremos letras
            for(int i=0; i<letras.length; i++) {
                // asignamos en may los caracteres de letras en mayusculas
                may[i] = Character.toUpperCase(letras[i]);
            }
            // devolvemos la array de mayusculas
            return may;
        }
        // si no se quiere en mayusculas devolvemos tal cual
        return letras;
    }
    //Este metodo printea arrays de char
    public static void print(char[] letras) {
        for(char letra : letras) {
            System.out.print(letra + ", ");
        }
        System.out.println();
    }
}