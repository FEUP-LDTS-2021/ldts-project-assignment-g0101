import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pacman extends GameCharacter {
    private boolean mouthOpen;
    public Pacman(int x, int y) {
        super(x, y);
        mouthOpen = true;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        if(mouthOpen){
            graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('*')[0]);
            mouthOpen = false;
        }else {
            graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('C')[0]);
            mouthOpen = true;
        }

    }
}
