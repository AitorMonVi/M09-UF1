package iticbcn.xifratge;

@SuppressWarnings("serial")
public class ClauNoSuportada extends Exception {
    public ClauNoSuportada() {
        super("Clave no soportada");
    }

    public ClauNoSuportada(String msg) {
        super(msg);
    }
}
