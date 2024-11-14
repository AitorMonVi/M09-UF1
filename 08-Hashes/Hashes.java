/* */

// imports encode
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

//imports interval
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.Duration;

public class Hashes {
    // variables globales
    private int npass;

    // metodos principales
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] hash = md.digest(pw.getBytes());

            HexFormat hex = HexFormat.of();
            return hex.formatHex(hash);
        } catch(NoSuchAlgorithmException e) {
            return "Error al calcular hash SHA-512";
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                pw.toCharArray(),
                salt.getBytes(),
                1000,
                128
            );

            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            byte[] hash = key.generateSecret(spec).getEncoded();

            HexFormat hex = HexFormat.of();
            return hex.formatHex(hash);
        } catch(NoSuchAlgorithmException e) {
            return "Error al calcular hash PBKDF2";
        } catch(InvalidKeySpecException e) {
            return "Error al calcular hash PBKDF2";
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {
        this.npass = 0;
        final char[] CAHRSET = "abcdefABCDEF1234567890!".toCharArray();
        char[] password = new char[6];
        // creamos diferentes combinaciones con el CHARSET para poder encontrar la contraseña
        // TODO: crear bucle que vaya rotando la contraseña

        return null;
    }

    public String getInterval(long t1, long t2) {
        LocalDateTime start = LocalDateTime.ofInstant(Instant.ofEpochMilli(t1), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(t2), ZoneId.systemDefault());
        
        // restamos las dos horas 
        Duration diff = Duration.between(start, end);
        long milis = diff.toMillis();

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", 
                             toDays(milis), toHours(milis), toMinutes(milis), toSeconds(milis), milis % 1000);
    }

    // metodos secundarios
    // creamos un metodo que nos devuelve un String apartird de un char[]
    public String toString(char[] array) {
        String respuesta = "";
        for(char caracter : array) {
            if(caracter!='\u0000') respuesta+=caracter;
        }
        return respuesta;
    }
    
    // creamos metodos que nos devolveran un long en diferentes tiempos
    public long toDays(long milis) { return milis / (24 * 60 * 60 * 1000); }
    public long toHours(long milis) { return milis / (60 * 60 * 1000) % 24; }
    public long toMinutes(long milis) { return milis / (60 * 1000) % 60; }
    public long toSeconds(long milis) { return milis / 1000 % 60; }

    // main
    public static void main(String[] args) {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                             h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}