import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GhostTest {
    //TODO: Implement tests for the functionalities described in the text files

    Ghost ghost;
    Game game;

    @BeforeEach
    public void createGhost(){
        game = new Game();
        ghost = new Ghost(10,10);
    }

    @Test
    public void exists(){

        Assertions.assertNotNull(ghost);
    }


}
