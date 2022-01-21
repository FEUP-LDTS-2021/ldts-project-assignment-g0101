import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class PacmanTest {
    Pacman pac;
    Maze maze;


    @Test
    public void exists(){
        pac = new Pacman(1,1);
        Assertions.assertNotNull(pac);
    }


    public void setupMazePacMovement(){
        MapReader mapReader = new MapReader(new File("pacmovement.txt"));
        maze = new Maze(mapReader.readMap());
        pac = new Pacman(2,2);
    }

    @Test
    public void moveLeft(){
        setupMazePacMovement();
        Position start = pac.getPosition();
        pac.moveLeft();
        Position end = pac.getPosition();
        Assertions.assertEquals(start.getX()-1,end.getX());
        Assertions.assertEquals(start.getY(),end.getY());
    }

    @Test
    public void moveRight(){
        setupMazePacMovement();
        Position start = pac.getPosition();
        pac.moveRight();
        Position end = pac.getPosition();
        Assertions.assertEquals(start.getX()+1,end.getX());
        Assertions.assertEquals(start.getY(),end.getY());
    }

    @Test
    public void moveUp(){
        setupMazePacMovement();
        Position start = pac.getPosition();
        pac.moveUp();
        Position end = pac.getPosition();
        Assertions.assertEquals(start.getX(),end.getX());
        Assertions.assertEquals(start.getY()-1,end.getY());
    }

    @Test
    public void moveDown(){
        setupMazePacMovement();
        Position start = pac.getPosition();
        pac.moveDown();
        Position end = pac.getPosition();
        Assertions.assertEquals(start.getX(),end.getX());
        Assertions.assertEquals(start.getY()+1,end.getY());
    }
}