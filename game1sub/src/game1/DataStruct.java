package game1;


public class DataStruct {

    private int x;
    private int y;
    private int key;
    private int delay;
    
    public DataStruct(int x, int y, int key, int delay){
        this.x = x;
        this.y = y;
        this.key = key;
        this.delay = delay;
        
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getKey(){
        return key;
    }
    
    public int getDelay(){
        return delay;
    }
    
    public DataStruct setKey(int i){
        DataStruct newby;
        newby = new DataStruct(this.getX(), this.getY(), i, this.getDelay());
        return newby;
    }
    
}
