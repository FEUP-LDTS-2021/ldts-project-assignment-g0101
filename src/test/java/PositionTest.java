import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    private int x=1;
    private int y=2;

    @Test
    public void gettersInPosition(){
        Position position = new Position(x,y);

        Assertions.assertEquals(1,position.getX());
        Assertions.assertEquals(2,position.getY());
    }

    @Test
    public void settersInPosition(){
        Position position = new Position(x,y);

        position.setX(2);
        position.setY(1);

        Assertions.assertEquals(2,position.getX());
        Assertions.assertEquals(1,position.getY());
    }
}
