public abstract class GameCharacter extends Element {

    public GameCharacter(int x, int y) {
        super(x, y);
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

}
