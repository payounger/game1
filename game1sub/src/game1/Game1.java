package game1;
import java.util.Arrays;
import javalib.funworld.World;
import javalib.colors.Black;
import javalib.colors.Blue;
import javalib.colors.Red;

public class Game1 {

    public static int main(String[] args) {
        int sqSide;
        int gridSize;
        DataStruct[] worldArray;
        int halfSide;
        
        sqSide = 30;
        gridSize = 7;
        worldArray = new DataStruct[(gridSize*gridSize)-1];
        halfSide = (sqSide/2);
        

        
        //Should initialize an array filled entirely with empty DataStructs
        //that have their x and y coordinates done properly. Also the player
        //should be spawned at x=3, y=6.
        
        public DataStruct[] initialize(DataStruct[] array){
            
        for(int i=0; i<((gridSize*gridSize)-1); i++){
            
            for(int x=0; x<gridSize; x++){
                
                for(int y=0; y<gridSize; y++){
                    
                    if(x==3 && y==6){
                        array[i] = new DataStruct(x,y,2,0);
                            }else
                    
            array[i] = new DataStruct(x, y, 0, 0);
                    }
            }
        }
        return array;
    }
        
        public DataStruct playerLocation(DataStruct[] array){
            DataStruct target;
            int targetKey;
            for(int i=0; i<((gridSize*gridSize)-1); i++){
                target = array[i];
                targetKey = target.getKey();
                if(targetKey == 2){
                    return target;
                } //else should throw an excepttion but this should never be reached
            }
        }
        
        public DataStruct enemyLocations(DataStruct[] array){
            DataStruct target;
            int targetKey;
            for(int i=0; i<((gridSize*gridSize)-1); i++){
                target = array[i];
                targetKey = target.getKey();
                if(targetKey == 1){
                    return target;
                    }
                }
            }
        
        public worldImage composeWorld(DataStruct[] array){
            Posn currentPosn;
            for(int i=0; i<((gridSize*gridSize)-1); i++){
                currentPosn = ((array[i].getX()+1)*halfSide), ((array[i].getY()+1)*halfSide);
                if(array[i].getKey()==0){
                    return new FrameImage(currentPosn, sqSide, sqSide, new Black());
                }else if(array[i].getKey()==1){
                    return new RectangleImage(currentPosn, sqSide, sqSide, new Red());
                }else if(array[i].getKey()==2){
                        return new RectangleImage(currentPosn, sqSide, sqSide);
            }
        }
        
        
        
        
        
        //0 = blank
        //1 = enemy block
        //2 = player block
        
        

    }
    
}
