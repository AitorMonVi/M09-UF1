package src.main.java.iticbcn.xifratge;

public class TextXifrat {
    // atributos
    private byte[] info;

    // constructor
    public TextXifrat(byte[] info) {
        this.info = info;
    }

    // metodos
    @Override
    public String toString() {
        return new String(info);
    }

    public byte[] getBytes() { return info.clone(); }
}
