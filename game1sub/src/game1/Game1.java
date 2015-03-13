package game1;

import javalib.funworld.World;
import javalib.colors.*;
import javalib.worldimages.*;
import java.util.ArrayList;
import java.util.Random;

public class Game1 extends World {

    public static final int sqSide = 100;
    public static final int gridSize = 7;
    public DataStruct[] worldArray;
    public static final int halfSide = (sqSide / 2);
    public final Posn sqCenter = new Posn(halfSide, halfSide);
    public int ticker;
    public int score;
    public int spawnInterval = 2;
    public int enemyMoveInterval = 2;
    public static int numTests = 15;

    public static void main(String[] args) {
        Game1 world;
        world = new Game1(new DataStruct[(gridSize * gridSize)], 0, 0);
        boolean tP = true;

        System.out.println("Testing calcPin");
       for (int i = 0; i < numTests; i++) {
            Random randyX = new Random();
            int x = randyX.nextInt(gridSize);
            Random randyY = new Random();
            int y = randyY.nextInt(gridSize);
            //System.out.println("random x is "+x+" random y is "+y);
            DataStruct randStruct;
            randStruct = new DataStruct(x, y, 0);
            Posn targetPosn;
            targetPosn = new Posn(((x * sqSide) + halfSide), ((y * sqSide) + halfSide));
            tP &= world.calcPin(randStruct).x == targetPosn.x && world.calcPin(randStruct).y == targetPosn.y;
            if (tP == false) {
                System.out.println("test failed for calcPin");
                tP = true;
            }
        }
//        System.out.println("Testing enemyLocations");
//        for (int i = 0; i < numTests; i++) {
//            world = new Game1(new DataStruct[(gridSize * gridSize)], 0, 0);
//            Random randyX = new Random();
//            int x = randyX.nextInt(gridSize);
//            Random randyY = new Random();
//            int y = randyY.nextInt(gridSize);
//            Posn randPosn;
//            randPosn = new Posn(x, y);
//            DataStruct randStruct;
//            randStruct = new DataStruct(randPosn.x, randPosn.y, 1);
//            ArrayList<DataStruct> targetArrayList = new ArrayList<DataStruct>();
//            DataStruct targetStruct = new DataStruct(x, y, 1);
//            targetArrayList.add(targetStruct);
//            tP &= world.enemyLocations().equals(targetArrayList);
//            if (tP == false) {
//                System.out.println("test failed for enemyLocations");
//                tP = true;
//            }
//        }
        System.out.println("Testing initialize");
        for (int i = 0; i < numTests; i++) {
            World testWorld;
            DataStruct[] initResult;
            DataStruct[] targetStructArray = new DataStruct[(gridSize * gridSize - 1)];
            for (int x = 0; x < gridSize; x++) {
                for (int y = 0; y < gridSize; y++) {
                    int q = y * (gridSize - 1) + x;
                    if (x == (gridSize - 1) / 2 && y == gridSize - 1) {
                        targetStructArray[i] = new DataStruct(x, y, 2);
                    } else {
                        targetStructArray[i] = new DataStruct(x, y, 0);
                    }
                }
            }
            /*I dont know how to do this bit here. Initialize only modifies the
            worldArray,which is non-static, how can I compare it to something 
            that I genreate in my tests which are in my main funciton which must
            be static?*/
            //tP &= worldArray.equals(targetStructArray); 
            if (tP == false) {
                System.out.println("test failed for initialize");
                tP = true;
            }
        }
//        System.out.println("Testing makeImage");
//        for (int i = 0; i < numTests; i++){
//            
//        }
            

            world.initialize();
            world.spawnEnemies();
            world.bigBang(sqSide * gridSize, sqSide * gridSize, .15);
        
    }

    public WorldImage makeImage() {
        Posn currentPosn;
        int height = sqSide * gridSize;
        int width = height;
        WorldImage scene = new RectangleImage(new Posn(width / 2, height / 2), width, height, new White());
        for (int i = 0; i < worldArray.length; i++) {
            currentPosn = calcPin(worldArray[i]);
            //System.out.println("i is " + i + " array x is " + array[i].getX() + " array y is " + array[i].getY() + " posn x is " + currentPosn.x + " posn y is " + currentPosn.y);
            //System.out.println("current player location is " + playerLocation(worldArray).getX());
            Posn thisPosn = currentPosn;
            if (worldArray[i].getKey() == 0) {
                scene = new OverlayImages(scene, new FrameImage(thisPosn, sqSide, sqSide, new Black()));
            } else if (worldArray[i].getKey() == 1) {
                scene = new OverlayImages(scene, new RectangleImage(thisPosn, sqSide, sqSide, new Red()));
            } else //if (array[i].getKey()==2){
            {
                scene = new OverlayImages(scene, new RectangleImage(thisPosn, sqSide, sqSide, new Blue()));
            }
        }
        return scene;
    }

    public Game1(DataStruct[] array, int score, int ticks) {
        super();
        worldArray = array;
        this.score = score;
        ticker = ticks;
    }

    public World onKeyEvent(String ke) {
        int playerX = playerLocation().getX();
        int playerY = playerLocation().getY();
        int playerIndex = playerY * (gridSize) + playerX;
        World w;

        if (ke.equals("right")) {
            //System.out.println("right key input recieved");
            if (playerIndex == gridSize * gridSize - 1) {
                if (worldArray[gridSize * (gridSize - 1)].getKey() == 0) {
                    worldArray[gridSize * (gridSize - 1)] = worldArray[gridSize * (gridSize - 1)].setKey(2);
                    worldArray[playerIndex] = worldArray[playerIndex].setKey(0);
                    w = new Game1(worldArray, score, ticker);
                    return w;

                } else {
                    System.out.println(score);
                    return endOfWorld("right collision");
                }
            } else if (worldArray[playerIndex + 1].getKey() == 0) {
                worldArray[playerIndex + 1] = worldArray[playerIndex + 1].setKey(2);
                worldArray[playerIndex] = worldArray[playerIndex].setKey(0);
                w = new Game1(worldArray, score, ticker);
                //System.out.println("new world returned based on right key input");
                return w;
            } else {
                System.out.println(score);
                return endOfWorld("right collision");
            }
        }
        if (ke.equals("left")) {
            if (playerIndex == gridSize * (gridSize - 1)) {
                if (worldArray[gridSize * gridSize - 1].getKey() == 0) {
                    worldArray[gridSize * gridSize - 1] = worldArray[gridSize * gridSize - 1].setKey(2);
                    worldArray[playerIndex] = worldArray[playerIndex].setKey(0);
                    w = new Game1(worldArray, score, ticker);
                    return w;
                } else {
                    System.out.println(score);
                    return endOfWorld("right collision");
                }
            } else if (worldArray[playerIndex - 1].getKey() == 0) {
                worldArray[playerIndex - 1] = worldArray[playerIndex - 1].setKey(2);
                worldArray[playerIndex] = worldArray[playerIndex].setKey(0);
                w = new Game1(worldArray, score, ticker);
                return w;
            } else {
                System.out.println(score);
                return endOfWorld("left collision");
            }
        }
        if (ke.equals("x")) {
            System.out.println("x input recieved");
            System.out.println(score);
            return endOfWorld("x hit to quit");
        }
        return endOfWorld("this should only happen if u hit not left or not right");

    }

    public World onTick() {
        //System.out.println(ticker);
        if (ticker % enemyMoveInterval == 0) {
            moveEnemies();
        }
        if (ticker % spawnInterval == 0) {
            //System.out.println("spawn triggered");
            spawnEnemies();
        }
        return new Game1(worldArray, score, ticker + 1);
    }

    public void moveEnemies() {
        ArrayList<DataStruct> enemyArray = enemyLocations();
        //System.out.println(enemyArray.toString());
        for (int i = enemyArray.size() - 1; i >= 0; i--) {
            DataStruct currentEnemy = enemyArray.get(i);
            int currentEnemyWorldIndex = currentEnemy.getX() + currentEnemy.getY() * (gridSize);
            if (currentEnemyWorldIndex < ((gridSize - 1) * gridSize)) {
                if (worldArray[currentEnemyWorldIndex + gridSize].getKey() == 2) {
                    System.out.println(score);
                    endOfWorld("collision!");
                } else {
                    worldArray[currentEnemyWorldIndex] = worldArray[currentEnemyWorldIndex].setKey(0);
                    worldArray[currentEnemyWorldIndex + (gridSize)] = worldArray[currentEnemyWorldIndex + (gridSize)].setKey(1);
                }
            } else {
                worldArray[currentEnemyWorldIndex] = worldArray[currentEnemyWorldIndex].setKey(0);
                score = score + 1;
            }
        }
    }

    public void spawnEnemies() {
        Random randy = new Random();
        int spawnLoc = randy.nextInt(gridSize);
        //System.out.println(spawnLoc);
        worldArray[spawnLoc] = worldArray[spawnLoc].setKey(1);
    }

    //Should initialize an array filled entirely with empty DataStructs
    //that have their x and y coordinates done properly. Also the player
    //should be spawned at x=3, y=6.

    private void initialize() {
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                int i = y * (gridSize) + x;
                if (x == (gridSize - 1) / 2 && y == gridSize - 1) {
                    worldArray[i] = new DataStruct(x, y, 2);
                } else {
                    worldArray[i] = new DataStruct(x, y, 0);
                }
            }
        }
    }

    //returns the 
    public DataStruct playerLocation() {
        DataStruct target;
        int targetKey;
        for (int i = 0; i < (gridSize * gridSize); i++) {
            target = worldArray[i];
            targetKey = target.getKey();
            if (targetKey == 2) {
                return target;
            } //else should throw an excepttion but this should never be reached
        }
        throw new RuntimeException("player object not found");
    }

    public ArrayList<DataStruct> enemyLocations() {
        DataStruct target;
        int targetKey;
        ArrayList<DataStruct> output = new ArrayList();
        for (int i = 0; i < (gridSize * gridSize); i++) {
            target = worldArray[i];
            targetKey = target.getKey();
            if (targetKey == 1) {
                output.add(target);
            }
        }
        return output;
    }

    public Posn calcPin(DataStruct Struct) {
        int x = Struct.getX();
        int y = Struct.getY();
        return new Posn(x * sqSide + halfSide, y * sqSide + halfSide);
    }

}
