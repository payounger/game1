package game1;

public class PinholeCalculator {
    private int halfSide;

    public class Pair{
        private final int x;
        private final int y;
        
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    Pair calcPin(DataStruct[] array, int i){
        int x = array[i].getX()+1;
        int y = array[i].getY()+1;
        return new Pair(x*halfSide, y*halfSide);
    }
    //((array[i].getX()+1)*halfSide), ((array[i].getY()+1)*halfSide)
}
