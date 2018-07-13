package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


/*
 * Draws a world consisting of hexagonal regions.
*/

public class HexWorld {
    private static final int SIZE = 3;
    private static final int COUNT = 3;
    private static TETile[][] randomTiles = new TETile[2 * SIZE * ( 2 * COUNT - 1)][2 * SIZE * ( 2 * COUNT - 1)];

    private static final long SEED = 2871;
    private static final Random RANDOM = new Random(SEED);

    public static TETile randomTile() {
        int num = RANDOM.nextInt(8);
        switch (num) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOOR;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.WATER;
            case 4: return Tileset.FLOWER;
            case 5: return Tileset.SAND;
            case 6: return Tileset.MOUNTAIN;
            case 7: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }


    // build up each single Hexagon with given size
    public static void addHexagon(int xVal, int yVal, TETile t) {
        halfHexagon(true, xVal, yVal, t);
        halfHexagon(false, xVal, yVal - SIZE, t);
    }

    // if increase, drawing the top part of the hexagon
    // otherwise, drawing the bottom of the hexagon
    private static void halfHexagon(boolean increase, int xVal, int yVal, TETile t) {
        int add = (increase) ? 2 : -2;
        int base = (increase) ? SIZE : SIZE + (SIZE - 1) * 2;
        int space = (increase) ? -1 : 1;
        int spaceBase = (increase) ? SIZE - 1 : 0;

        for (int i = 0; i < SIZE; i++) {
            int count = spaceBase + space * i;
            for (int j = 0; j < base + add * i; j++) {
                randomTiles[count + xVal + j][yVal - i] = t;
            }
        }
    }

    // in the initial step, build up the background with Tileset.nothing
    public static TETile[][] fillBackground(TETile[][] tiles) {
        TETile[][] rst = new TETile[tiles.length][tiles[0].length];
        for (int i = 0; i < rst.length; i++) {
            for (int j = 0; j < rst[0].length; j++) {
                rst[i][j] = Tileset.NOTHING;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int width = 2 * SIZE * ( 2 * COUNT - 1);
        int height = 2 * SIZE * ( 2 * COUNT - 1);
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        randomTiles = fillBackground(randomTiles);

        int xStart = -(SIZE * 2 - 1);
        int yStart = height - 1 - SIZE * COUNT;

        for (int col = 0; col < COUNT * 2 - 1; col++) {
            yStart = (col >= COUNT )? yStart - SIZE: yStart + SIZE;
            xStart = xStart + SIZE * 2 - 1;
            for (int index = 0; index < ( 2 * COUNT - 1) - Math.abs(( 2 * COUNT - 1) / 2 - col); index++) {
                addHexagon(xStart, yStart - index * 2 * SIZE, randomTile());
            }
        }


        ter.renderFrame(randomTiles);
    }
}