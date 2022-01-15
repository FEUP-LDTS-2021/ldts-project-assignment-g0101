public interface Collision {
    boolean characterCanMoveTo(GameCharacter gameCharacter,Position position);
    void characterInteractsWithElement(GameCharacter gameCharacter,Position position);
}
