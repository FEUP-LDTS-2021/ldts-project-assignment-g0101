import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pacman extends GameCharacter {
    private boolean mouthIsOpen;
    private char mouthOpenChar;
    private char mouthClosedChar;

    public Pacman(int x, int y) {
        super(x, y);
        mouthIsOpen = true;
    }

    public void draw(TextGraphics graphics) {
        setPacmanChar();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        if(mouthIsOpen){
            graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter(mouthOpenChar)[0]);
            mouthIsOpen = false;
        }else {
            graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter(mouthClosedChar)[0]);
            mouthIsOpen = true;
        }

    }

    private void setPacmanChar(){
        switch (direction){
            case "Up":
                mouthOpenChar = '&';
                mouthClosedChar = '+';
                break;
            case "Down":
                mouthOpenChar = '%';
                mouthClosedChar = '-';
                break;
            case "Left":
                mouthOpenChar = '$';
                mouthClosedChar = ',';
                break;
            default:
                mouthOpenChar = '*';
                mouthClosedChar = 'C';
                break;
        }
    }
}
