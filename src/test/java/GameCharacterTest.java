import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameCharacterTest {

    GameCharacter gameCharacter;

    @BeforeEach
    public void createGhost(){
        gameCharacter = new Pacman(10,10);
    }

    @Test
    public void moveLeft(){
        gameCharacter.moveLeft();
        Assertions.assertEquals(9, gameCharacter.getPosition().getX());
        Assertions.assertEquals(10,gameCharacter.getPosition().getY());
    }

    @Test
    public void moveRight(){
        gameCharacter.moveRight();
        Assertions.assertEquals(11, gameCharacter.getPosition().getX());
        Assertions.assertEquals(10, gameCharacter.getPosition().getY());
    }

    @Test
    public void moveUp(){
        gameCharacter.moveUp();
        Assertions.assertEquals(10, gameCharacter.getPosition().getX());
        Assertions.assertEquals(9, gameCharacter.getPosition().getY());

    }

    @Test
    public void moveDown(){
        gameCharacter.moveDown();
        Assertions.assertEquals(10, gameCharacter.getPosition().getX());
        Assertions.assertEquals(11, gameCharacter.getPosition().getY());
    }
}
