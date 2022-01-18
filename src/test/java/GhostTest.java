import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class GhostTest {
    //TODO: Implement tests for the functionalities described in the text files

    Ghost ghost;
    Maze maze;

   public void setupGhostMazeMovement(){
       MapReader mapReader = new MapReader(new File("ghostmovement.txt"));
       maze = new Maze(mapReader.readMap());
       ghost = new Ghost(2,2);
   }

    @Test
    public void exists(){
        ghost = new Ghost(1,1);
        Assertions.assertNotNull(ghost);
    }

    @Test
    public void moveLeft(){
       setupGhostMazeMovement();
        Position start = ghost.getPosition();
        ghost.moveLeft();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY(),end.getY());
        Assertions.assertEquals(start.getX()-1,end.getX());
    }

    @Test
    public void moveRight(){
        setupGhostMazeMovement();
        Position start = ghost.getPosition();
        ghost.moveRight();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY(),end.getY());
        Assertions.assertEquals(start.getX()+1,end.getX());
    }

    @Test
    public void moveUp(){
        setupGhostMazeMovement();
        Position start = ghost.getPosition();
        ghost.moveUp();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY()-1,end.getY());
        Assertions.assertEquals(start.getX(),end.getX());
    }

    @Test
    public void moveDown(){
        setupGhostMazeMovement();
        Position start = ghost.getPosition();
        ghost.moveDown();
        Position end = ghost.getPosition();
        Assertions.assertEquals(start.getY()+1,end.getY());
        Assertions.assertEquals(start.getX(),end.getX());
    }

    @Test
    public void ghostCanMoveTo(){
       setupGhostMazeMovement();
       Position start = ghost.getPosition();
       Assertions.assertTrue(maze.characterCanMoveTo(new Position(start.getX()+1, start.getY())));
       Assertions.assertTrue(maze.characterCanMoveTo(new Position(start.getX()-1, start.getY())));
       Assertions.assertTrue(maze.characterCanMoveTo(new Position(start.getX(), start.getY()+1)));
       Assertions.assertTrue(maze.characterCanMoveTo(new Position(start.getX()+1, start.getY()-1)));
    }
}
