import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.PipedOutputStream;
import java.util.Random;

public class Ghost extends GameCharacter {

    public Ghost(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('F')[0]);
    }

    public Position randomMove() {
        Position start = this.getPosition();
        Position end = this.getPosition();
        Random rand = new Random();
        while(start.equals(end)){
            int n = rand.nextInt(4);
            switch (n) {
                case 0 -> end = new Position(end.getX() + 1, end.getY());
                case 1 -> end = new Position(end.getX() - 1, end.getY());
                case 2 -> end = new Position(end.getX(), end.getY() + 1);
                case 3 -> end = new Position(end.getX(), end.getY() - 1);
            }
        }
        return end;
    }
}
