public class Tile {
    private int pos;
    private boolean p1Ship;
    private boolean p2Ship;
    private boolean p1Bomb;
    private boolean p2Bomb;

    public Tile( boolean p1Ship, boolean p2Ship, boolean p1Bomb, boolean p2Bomb) {

        this.p1Ship = p1Ship;
        this.p2Ship = p2Ship;
        this.p1Bomb = p1Bomb;
        this.p2Bomb = p2Bomb;
    }

    public boolean isP1Ship() {
        return p1Ship;
    }

    public void setP1Ship(boolean p1Ship) {
        this.p1Ship = p1Ship;
    }

    public boolean isP2Ship() {
        return p2Ship;
    }

    public void setP2Ship(boolean p2Ship) {
        this.p2Ship = p2Ship;
    }

    public boolean isP1Bomb() {
        return p1Bomb;
    }

    public void setP1Bomb(boolean p1Bomb) {
        this.p1Bomb = p1Bomb;
    }

    public boolean isP2Bomb() {
        return p2Bomb;
    }

    public void setP2Bomb(boolean p2Bomb) {
        this.p2Bomb = p2Bomb;
    }
}
