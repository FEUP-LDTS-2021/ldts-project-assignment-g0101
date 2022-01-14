import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pacman extends Element {
    public Pacman(int x, int y) {
        super(x, y);
    }

    //TODO: Implement Movement

    public int getX() {
        return this.position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position p) {
        position = p;
    }

    public void moveDown() {
        this.setPosition(new Position(position.getX(), position.getY() + 1));
    }

    public void moveRight() {
        this.setPosition(new Position(position.getX() + 1, position.getY()));
    }

    public void moveLeft() {
        this.setPosition(new Position(position.getX() - 1, position.getY()));
    }

    public void moveUp() {
        this.setPosition(new Position(position.getX(), position.getY() - 1));
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('C')[0]);
    }
}
