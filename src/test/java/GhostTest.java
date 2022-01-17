import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class GhostTest {
    //TODO: Implement tests for the functionalities described in the text files

    Ghost ghost;
    Maze maze;

    @BeforeEach
    public void createGhost(){
        MapReader mapReader = new MapReader(new File("ghostmovement.txt"));
        maze = new Maze(mapReader.readMap());
        ghost = new Ghost(2,2);
    }

    @Test
    public void exists(){

        Assertions.assertNotNull(ghost);
    }

    @Test
    public void moveLeft(){
        Position start = ghost.getPosition();
        ghost.moveLeft();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY(),end.getY());
        Assertions.assertEquals(start.getX()-1,end.getX());
    }

    @Test
    public void moveRight(){
        Position start = ghost.getPosition();
        ghost.moveRight();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY(),end.getY());
        Assertions.assertEquals(start.getX()+1,end.getX());
    }

    @Test
    public void moveUp(){
        Position start = ghost.getPosition();
        ghost.moveUp();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY()-1,end.getY());
        Assertions.assertEquals(start.getX(),end.getX());
    }

    @Test
    public void moveDown(){
        Position start = ghost.getPosition();
        ghost.moveDown();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY()+1,end.getY());
        Assertions.assertEquals(start.getX(),end.getX());
    }
}
