/* */

package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import iticbcn.xifratge.ClauNoSuportada;
import iticbcn.xifratge.TextXifrat;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class XifradorAES implements Xifrador {
    // creamos las variables globales
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    //private static final String CLAU = "BjgMUnjlÑopRsTe";
    
    private static final int MIDA_IV = 16;

    // constructor
    public XifradorAES() {}

    // creamos los metodos a implementar
    public TextXifrat xifra(String msg, String key) throws ClauNoSuportada {
        TextXifrat xifrat;
        try {
            xifrat = new TextXifrat(xifraAES(msg, key));
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
        return xifrat;
    }
    public String desxifra(TextXifrat msg, String key) throws ClauNoSuportada {
        try {
            return desxifraAES(msg.getBytes(), key);
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }

    // creamos los metodos de cifrado
    public byte[] xifraAES(String msg, String password) throws Exception {
        // coge los bytes del mensage
        byte[] msgByte = msg.getBytes();

        // Genera IvParameterSpec
        byte[] iv = new byte[MIDA_IV];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);;
        digest.update(password.getBytes("UTF-8"));
        byte[] key = new byte[MIDA_IV];
        System.arraycopy(digest.digest(), 0, key, 0, key.length);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
        byte[] cifrado = cipher.doFinal(msgByte);

        // Combinar IV y parte del cifrado
        byte[] cifradoIv = new byte[MIDA_IV + cifrado.length];
        System.arraycopy(iv, 0, cifradoIv, 0, MIDA_IV);
        System.arraycopy(cifrado, 0, cifradoIv, MIDA_IV, cifrado.length);
        
        // devuelve iv + mesnage encriptado                    
        return cifradoIv;
    }

    public String desxifraAES(byte[] msg, String password) throws Exception {
        // extrae IV
        byte[] descifraIv = new byte[MIDA_IV];
        System.arraycopy(msg, 0, descifraIv, 0, descifraIv.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(descifraIv);

        // extrae la parte cifrada
        int espacioCifrado = msg.length - MIDA_IV;
        byte[] msgByte = new byte[espacioCifrado];
        System.arraycopy(msg, MIDA_IV, msgByte, 0, espacioCifrado);

        // hacemos el hash de la clave
        byte[] clave = new byte[MIDA_IV];
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        digest.update(password.getBytes());
        System.arraycopy(digest.digest(), 0, clave, 0, clave.length);
        SecretKeySpec keySpec = new SecretKeySpec(clave, ALGORISME_XIFRAT);

        // desciframos
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] descifrado = cipher.doFinal(msgByte);

        // devovemos el String descifrado
        return new String(descifrado);
    }

    // creamos el main del programa
    /*
    public static void main(String[] args) {
        String[] msgs = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo esta tu cuñado",
            "Àgora ïlla Ôtto"
        };

        for(int i=0; i<msgs.length; i++) {
            String msg = msgs[i];

            byte[] xifrat = null;
            String desxifrat = "";

            try{
                xifrat=xifraAES(msg, CLAU);
                desxifrat=desxifraAES(xifrat, CLAU);
            } catch(Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
                //System.out.println("Error de xifrat: ");
                //e.printStackTrace();
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(xifrat));
            System.out.println("DEC: " + desxifrat);
        }
    }
    */
}