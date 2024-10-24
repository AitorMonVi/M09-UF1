package src.main.java.iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {
    public Xifrador creaXifrador() {
        return new XifradorAES();
    }
}
