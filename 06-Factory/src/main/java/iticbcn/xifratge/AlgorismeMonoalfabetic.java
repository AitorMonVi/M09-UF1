package src.main.java.iticbcn.xifratge;

public class AlgorismeMonoalfabetic extends AlgorismeFactory {
    public Xifrador creaXifrador() {
        return new XifradorMonoalfabetic();
    }
}
