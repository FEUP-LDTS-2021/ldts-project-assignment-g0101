public interface Collision {
    boolean characterCanMoveTo(Position position);
    void characterInteractsWithElement(GameCharacter gameCharacter,Position position);
}
