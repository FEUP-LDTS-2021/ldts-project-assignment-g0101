import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ghost extends GameCharacter {

    public Ghost(int x, int y) {
        super(x, y);
    }

    //TODO: Implement Movement

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('F')[0]);
    }

    public void randomMove() {

    }
}
