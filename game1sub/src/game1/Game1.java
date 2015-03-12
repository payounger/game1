package game1;
import java.util.Arrays;
import javalib.funworld.World;
import javalib.colors.Black;
import javalib.colors.Blue;
import javalib.colors.Red;
import javalib.worldimages.FrameImage;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import game1.Pair;

public class Game1 extends World{
    
        public final int sqSide = 30;
        public final int gridSize = 7;
        public final DataStruct[]worldArray = new DataStruct[(gridSize*gridSize)-1];
        public final int halfSide = (sqSide/2);
        
        public WorldImage makeImage() {
        return composeWorld(worldArray);
        
    }
        public Game1(DataStruct[] array){
            super();
            this. = array;
        }

	public BlobWorldFun(Blob blob) {
		super();
		this.blob = blob;
	}
        
        public void playerInput(String ke){
            if(ke.equals("right")){
                
            }else if(ke.equals("left")){
                
            }
            
        }
        
        
    public void main(String[] args) {
                Game1 world;
                
                
                world = world.initialize(worldArray);
                world.makeImage();
                
            }
        
        
        //Should initialize an array filled entirely with empty DataStructs
        //that have their x and y coordinates done properly. Also the player
        //should be spawned at x=3, y=6.
        
        private DataStruct[] initialize(DataStruct[] array){
            
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
        
        
        //returns the 
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
        
        public WorldImage composeWorld(DataStruct[] array){
            Posn currentPosn;
            for(int i=0; i<((gridSize*gridSize)-1); i++){
                Pair pairy = calcPin(array[i], i);
                int pairyX = pairy.getX();
                int pairyY = pairy.getY();
                currentPosn = new Posn(pairyX, pairyY);
                if(array[i].getKey()==0){
                    return new FrameImage(currentPosn, sqSide, sqSide, new Black());
                }else if(array[i].getKey()==1){
                    return new RectangleImage(currentPosn, sqSide, sqSide, new Red());
                }else //if (array[i].getKey()==2){
                        return new RectangleImage(currentPosn, sqSide, sqSide, new Blue());
            }
        }
        
        public Pair calcPin(DataStruct Struct, int i){
        int x = Struct.getX()+1;
        int y = Struct.getY()+1;
        return new Pair(x*halfSide, y*halfSide);
    }

    

}
        
        
        
            
        
        
        
        
        //0 = blank
        //1 = enemy block
        //2 = player block
        
        

    }
    
}
