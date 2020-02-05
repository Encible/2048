package scripts;

public class Map {

    private int size = 4;
    public Tile[][] tiles;
    private int score;

    public Map() {
        score = 0;
        tiles = new Tile[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                tiles[j][i] = new Tile(new Vector2d(j,i),this);
            }
        }
        this.spawnNum();
        this.spawnNum();
    }

    public int getScore() {
        return this.score;
    }

    public void resetMap() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                tiles[j][i].clear();
            }
        }
        this.score = 0;
        this.spawnNum();
        this.spawnNum();
    }

    public void spawnNum() {
        int x = Rand.rand(size);
        int y = Rand.rand(size);

        if (tiles[x][y].isEmpty()) tiles[x][y].setRandom();
        else this.spawnNum();  //only 16 tiles so sooner or later it would find the only one left
    }


    //finds nearest non-empty tile in specified direction
    private Tile firstDown (int x, int y) {
        for(int row = y+1; row<size; row++) {
            if(!tiles[x][row].isEmpty()) return tiles[x][row];
        }
        return null;
    }
    private Tile firstUp (int x, int y) {
        for(int row = y-1; row>=0; row--) {
            if(!tiles[x][row].isEmpty()) return tiles[x][row];
        }
        return null;
    }
    private Tile firstRight (int x, int y) {
        for(int col = x+1; col<size; col++) {
            if(!tiles[col][y].isEmpty()) return tiles[col][y];
        }
        return null;
    }
    private Tile firstLeft (int x, int y) {
        for(int col = x-1; col>=0; col--) {
            if(!tiles[col][y].isEmpty()) return tiles[col][y];
        }
        return null;
    }


    public void moveUp() {
        boolean actionPerformed = false;
        for(int col = 0; col < size; col++) {
            for(int row = 0; row < size; row++) {
                Tile firstFull = firstDown(col,row);
                if(firstFull!=null && tiles[col][row].isEmpty()) { //isEmpty to avoid case 2 2 4 turning to 8
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
                firstFull = firstDown(col,row); //required for case when 2 tiles combine on previously empty tile
                if(firstFull!=null) { //if firstFull is not null, then tiles[col][row] is NOT empty
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed; //movedFrom both changes tiles number and returns boolean
                }
            }
        }
        if(actionPerformed) this.spawnNum();
    }
    public void moveDown() {
        boolean actionPerformed = false;
        for(int col = 0; col < size; col++) {
            for(int row = size-1; row >=0; row--) {
                Tile firstFull = firstUp(col,row);
                if(firstFull!=null && tiles[col][row].isEmpty()) {
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
                firstFull = firstUp(col,row);
                if(firstFull!=null) {
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
            }
        }
        if(actionPerformed) this.spawnNum();
    }
    public void moveRight() {
        boolean actionPerformed = false;
        for(int row = 0; row < size; row++) {
            for(int col = size-1; col>=0; col--) {
                Tile firstFull = firstLeft(col,row);
                if(firstFull!=null && tiles[col][row].isEmpty()) {
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
                firstFull = firstLeft(col,row);
                if(firstFull!=null) {
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
            }
        }
        if(actionPerformed) this.spawnNum();
    }
    public void moveLeft() {
        boolean actionPerformed = false;
        for(int row = 0; row < size; row++) {
            for(int col = 0; col<size; col++) {
                Tile firstFull = firstRight(col,row);
                if(firstFull!=null && tiles[col][row].isEmpty()) {
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
                firstFull = firstRight(col,row);
                if(firstFull!=null) {
                    actionPerformed = tiles[col][row].movedFrom(firstFull) || actionPerformed;
                }
            }
        }
        if(actionPerformed) this.spawnNum();
    }

    public void raiseScore(int number) {
        this.score += number;
    }
}
