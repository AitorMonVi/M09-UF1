package src.main.java.iticbcn.xifratge;

public class AlgorismePolialfabetic extends AlgorismeFactory {
    public Xifrador creaXifrador() {
        return new XifradorPolialfabetic();
    }
}
