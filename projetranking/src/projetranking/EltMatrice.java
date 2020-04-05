package projetranking;
public class EltMatrice {
    public final int i;
    public final int j;
    public final double val;

    public EltMatrice(int start, int end, double probability) {
        this.i = start;
        this.j = end;
        this.val = probability;
    }


    @Override
    public String toString() {
        return i + ":" + j + ":" + val;
    }
}