package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    // creamos los metodos de la clase
    public KeyPair generaParellClausRSA() throws Exception {
        // creamos el generador de la KeyPair
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        // devolvemos el KayPair
        return generator.genKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        // generamos el cipher que nos ayudara a codificar
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        // devolvemos el mesnaje 
        return cipher.doFinal(msg.getBytes());
    }

    public String desxifraRSA(byte[] msg, PrivateKey ClauPrivada) throws Exception {
        // generamos el cipher que nos ayudara a codificar
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);
        // devolvemos el mesnaje
        return new String(cipher.doFinal(msg));
    }
}
