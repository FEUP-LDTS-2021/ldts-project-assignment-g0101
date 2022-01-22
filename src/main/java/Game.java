import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game {
    private Screen screen;
    private Maze maze;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        try {

            URL resource = getClass().getClassLoader().getResource("font.ttf");
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();

            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(28, 30));

            Terminal terminal = factory.createTerminal();
            ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });

            this.screen = new TerminalScreen(terminal);
            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this. screen.doResizeIfNecessary();     // resize screen if necessary

            //this. screen.setCharacter(10, 10, TextCharacter.fromCharacter('C')[0]);
            //this.screen.refresh();



        } catch (IOException e) {
            e.printStackTrace();
        }
        MapReader mapReader = new MapReader(new File("src/main/java/resources/basemaze.txt"));//TODO: Un-hardcode this
        this.maze = new Maze(mapReader.readMap());
    }

    public void run(){  //TODO : make this run on a different thread
        while(true){
            try {
                this.draw();
                KeyStroke key = screen.readInput();

                if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')){
                    this.screen.close();
                }
                else if (key.getKeyType() == KeyType.EOF){
                    break;
                }
                this.moveGhosts();
                this.processKey(key);
                if(maze.getState()) this.screen.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void moveGhosts(){
        maze.moveGhosts();
    }
    private void processKey(KeyStroke key) {
        maze.processKey(key);
    }

    private void draw() throws IOException{
        this.screen.clear();
        this.maze.draw(screen.newTextGraphics());
        this.screen.refresh();
    }
}
