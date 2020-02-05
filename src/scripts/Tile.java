package scripts;

public class Tile {

    public Vector2d position;
    public Map map;
    private int number;

    public Tile(Vector2d position, Map map) {
        this.map = map;
        this.position = position;
        this.number = 0;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean isEmpty() {
        return number == 0;
    }

    public String toString() {
        if(!this.isEmpty())return Integer.toString(this.number);
        return " ";
    }

    public void setRandom() {
        this.number = (Rand.rand(2)+1)*2;
    }

    public void clear() {
        this.number = 0;
    }
    private void append() {
        this.number*=2;
    }
    //combines 2 tiles, clearing another one, returns true if action was successful
    public boolean movedFrom(Tile another) {
        if(this.isEmpty()) {
            this.number = another.getNumber();
            another.clear();
            return true;
        }
        else {
            if(this.number == another.number) {
                this.append();
                this.map.raiseScore(this.number);
                another.clear();
                return true;
            }
        }
        return false;
    }
}
