import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Solution {

  // TODO: Particle and modeling ideas...
  //  rain (falling from top of grid to the bottom)
  //  smoke? (partner to charcoal, floats up a few pixels and then disappears) NOTE using alpha value can make it disappear over time

  // TODO: implement WOOD, COAL, and CHARCOAL behavior
  // TODO: implement COAL
  // TODO: implement CHARCOAL

  // Add constants for particle types here.
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int WOOD = 4;
  public static final int COAL = 5;
  public static final int CHARCOAL = 6;


  // Store directions in variables for the purpose of readability
  // TODO: change all particle comparisons to use these variables instead of 0, 1, 2
  //  for readability
  public static final int DOWN = 0;
  public static final int RIGHT = 1;
  public static final int LEFT = 2;

  // Add any custom colors for particle types here
  Color brown = new Color(159, 129, 112); // wood
  Color darkBlue = new Color(0, 78, 255); // water

  // Names used to populate the buttons in the UI that correspond to the particle types
  public static final String[] NAMES = {"Empty", "Metal", "Sand", "Water", "Wood", "Coal",
      "Charcoal"};

  // Do not add any more fields as part of Lab 5.
  private int[][] grid;
  public SandDisplayInterface display;
  private RandomGenerator random;

  /**
   * Constructor.
   *
   * @param display The display to use for this run
   * @param random The random number generator to use to pick random points
   */
  public Solution(SandDisplayInterface display, RandomGenerator random) {
    this.display = display;
    this.random = random;
    this.grid = new int[this.display.getNumRows()][this.display.getNumColumns()];

  }

  /**
   * Called when the user clicks on a location.
   */
  private void locationClicked(int row, int col, int tool) {
    grid[row][col] = tool;

  }

  /**
   * Copies each element of grid into the display.
   */
  public void updateDisplay() {
    // for each pixel in grid set color to tool type
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        int randColor = random.getRandomColor();
        if (grid[i][j] == EMPTY) {
          display.setColor(i, j, Color.BLACK);
        } else if (grid[i][j] == METAL) {
          display.setColor(i, j, Color.lightGray);
        } else if (grid[i][j] == SAND) {
          display.setColor(i, j, Color.YELLOW);
        } else if (grid[i][j] == WATER) {
          // water will shimmer
          if (randColor == 0) {
            display.setColor(i, j, Color.BLUE);
          }
          if (randColor == 1) {
            display.setColor(i, j, darkBlue);
          }
        } else if (grid[i][j] == WOOD) {
          display.setColor(i, j, brown);
        } else if (grid[i][j] == COAL) {
          display.setColor(i, j, Color.RED);
        } else if (grid[i][j] == CHARCOAL) {
          display.setColor(i, j, Color.DARK_GRAY);
        }

      }

    }

  }

  /**
   * Called repeatedly. Causes one random particle to maybe do something.
   */
  public void step() {
    Point point = random.getRandomPoint();
    int x = point.getRow();
    int y = point.getColumn();

    // TODO: implement WOOD, COAL, CHARCOAL behavior

    // SAND PARTICLE
    if (grid[x][y] == SAND) {
      int direction = random.getRandomDirection();
      // down
      if (direction == DOWN) {
        // move down, as long as it isn't on the bottom of the grid, and the space
        // below is EMPTY || WATER
        if (x + 1 < grid.length) {
          // and if the space below the sand particle position is empty
          if (grid[x + 1][y] == EMPTY) {
            // move sand DOWN by one row
            grid[x][y] = EMPTY;
            grid[x + 1][y] = SAND;

          }
          // If WATER below SAND, displace water by swapping the two particles
          if (grid[x + 1][y] == WATER) {
            grid[x][y] = WATER;
            grid[x + 1][y] = SAND;
          }
        }
      }
      // down and right
      if (direction == RIGHT) {
        if (y + 1 < grid[0].length && x + 1 < grid.length) {
          if (grid[x + 1][y + 1] == EMPTY && grid[x + 1][y] == SAND) {
            grid[x][y] = EMPTY;
            grid[x + 1][y + 1] = SAND;
          }
          if (grid[x + 1][y + 1] == WATER && grid[x + 1][y] == SAND) {
            // move sand DOWN by one row
            grid[x][y] = WATER;
            grid[x + 1][y + 1] = SAND;
          }
          // sand can slide off angled metal
          if (y + 3 < grid[0].length) {
            if (grid[x + 1][y] == METAL && grid[x + 1][y + 3] == EMPTY && grid[x][y + 1] == EMPTY) {
              grid[x][y] = EMPTY;
              grid[x][y + 1] = SAND;
            }
          }
        }
      }
      // down and left
      if (direction == LEFT) {
        if (y - 1 > -1 && x + 1 < grid.length) {
          if (grid[x + 1][y - 1] == EMPTY && grid[x + 1][y] == SAND) {
            grid[x][y] = EMPTY;
            grid[x + 1][y - 1] = SAND;
          }
          if (grid[x + 1][y - 1] == WATER && grid[x + 1][y] == SAND) {
            // move sand DOWN by one row
            grid[x][y] = WATER;
            grid[x + 1][y - 1] = SAND;
          }
          // sand can slide off angled metal
          if (y - 3 > -1) {
            if (grid[x + 1][y] == METAL && grid[x + 1][y - 3] == EMPTY && grid[x][y - 1] == EMPTY) {
              grid[x][y] = EMPTY;
              grid[x][y - 1] = SAND;
            }
          }
        }
      }
    }

    // WATER PARTICLE
    else if (grid[x][y] == WATER) {
      int direction = random.getRandomDirection();
      // down
      if (direction == DOWN) {
        if (x + 1 < grid.length) {
          if (grid[x + 1][y] == EMPTY) {
            grid[x][y] = EMPTY;
            grid[x + 1][y] = WATER;
          }
          if (grid[x + 1][y] == WOOD) {
            grid[x][y] = WOOD;
            grid[x + 1][y] = WATER;
          }
        }
      }
      // right
      if (direction == RIGHT) {
        if (y + 1 < grid[0].length) {
          if (grid[x][y + 1] == EMPTY) {
            grid[x][y] = EMPTY;
            grid[x][y + 1] = WATER;
          }
        }
      }
      // left
      if (direction == LEFT) {
        if (y - 1 > -1) {
          if (grid[x][y - 1] == EMPTY) {
            grid[x][y] = EMPTY;
            grid[x][y - 1] = WATER;
          }
        }
      }
    }

    // WOOD PARTICLE
    else if (grid[x][y] == WOOD) {
      int direction = random.getRandomDirection();

      if (direction == DOWN) {
        // as long as the particle is NOT on the BOTTOM of the grid
        if (x + 1 < grid.length) {
          // down
          if (grid[x + 1][y] == EMPTY) {
            grid[x][y] = EMPTY;
            grid[x + 1][y] = WOOD;
          }
          // UP if under WATER
          if (x - 1 < -1) { // ensures wood can't move above the grid boundary
            if (grid[x - 1][y] == WATER) {
              grid[x][y] = WATER;
              grid[x - 1][y] = WOOD;
            }
          }
        }
      }

      // WOOD stacking and rolling behavior
      // right
      if (direction == RIGHT) {
        if (y + 1 < grid[0].length) {
          if (x - 1 > -1) { // ensures wood can't go out of grid
            if (grid[x][y + 1] == WATER && grid[x - 1][y] == WOOD) {
              grid[x][y] = WATER;
              grid[x][y + 1] = WOOD;
            }
          }

          if (x + 1 < grid.length) {
            // float right if on water and right is empty
            if (grid[x + 1][y] == WATER && grid[x][y + 1] == EMPTY) {
              grid[x][y] = EMPTY;
              grid[x][y + 1] = WOOD;
            }
            if (grid[x][y + 1] == WATER) {
              grid[x][y] = WATER;
              grid[x][y + 1] = WOOD;
            }
            // roll off WOOD stack if right is empty and below is wood
            if (grid[x + 1][y] == WOOD && grid[x + 1][y + 1] != WOOD && grid[x][y + 1] == EMPTY) {
              grid[x][y] = EMPTY;
              grid[x][y + 1] = WOOD;
            }
          }
        }
      }
      // left
      if (direction == LEFT) {
        if (y - 1 > -1) {
          if (x - 1 > -1) {
            if (grid[x][y - 1] == WATER && grid[x - 1][y] == WOOD) {
              grid[x][y] = WATER;
              grid[x][y - 1] = WOOD;
            }
            if (x + 1 < grid.length) {
              // float left if on water and left is empty
              if (grid[x + 1][y] == WATER && grid[x][y - 1] == EMPTY) {
                grid[x][y] = EMPTY;
                grid[x][y - 1] = WOOD;
              }
              // roll off stack if left is empty and below is wood
              if (grid[x + 1][y] == WOOD && grid[x + 1][y - 1] != WOOD && grid[x][y - 1] == EMPTY) {
                grid[x][y] = EMPTY;
                grid[x][y - 1] = WOOD;
              }
            }
          }
        }
      }
    }

    // TODO: COAL needs to float on water.
    //  Stacks of COAL or CHARCOAL should fall like WOOD
    //  COAL needs to turn WOOD into COAL, setting off a chain reaction
    //  COAL needs to turn WOOD into COAL, unless the WOOD is sitting on WATER
    // COAL PARTICLE
    else if (grid[x][y] == COAL) {
      int direction = random.getRandomDirection();

      // down
      if (direction == DOWN) {
        if (x + 1 < grid.length) {
          if (grid[x + 1][y] == EMPTY) {
            grid[x][y] = EMPTY;
            grid[x + 1][y] = COAL;
          }
          if (grid[x + 1][y] == WOOD) {
            grid[x + 1][y] = COAL;
          }
        }
      }

      // WATER Interaction
      // WATER below
      if (x + 1 < grid.length && grid[x + 1][y] == WATER) {
        grid[x][y] = CHARCOAL;
      }
      // WATER above
      if (x - 1 > -1 && grid[x - 1][y] == WATER) {
        grid[x][y] = CHARCOAL;
      }
      // WATER left
      if (y - 1 > -1 && grid[x][y - 1] == WATER) {
        grid[x][y] = CHARCOAL;
      }
      // WATER right
      if (y + 1 < grid[0].length && grid[x][y + 1] == WATER) {
        grid[x][y] = CHARCOAL;
      }

      // WOOD Interaction
      if (x + 1 < grid.length && grid[x + 1][y] == WOOD) {
        grid[x + 1][y] = COAL;
      }
      // WOOD above
      if (x - 1 > -1 && grid[x - 1][y] == WOOD) {
        grid[x - 1][y] = COAL;
      }
      // WOOD left
      if (y - 1 > -1 && grid[x][y - 1] == WOOD) {
        grid[x][y - 1] = COAL;
      }
      // WOOD right
      if (y + 1 < grid[0].length && grid[x][y + 1] == WOOD) {
        grid[x][y + 1] = COAL;
      }
    }
  }

  /**
   * DO NOT MODIFY The rest of this file is UI and testing infrastructure.
   */

  private static class Point {

    private int row;
    private int column;

    public Point(int row, int column) {
      this.row = row;
      this.column = column;
    }

    public int getRow() {
      return row;
    }

    public int getColumn() {
      return column;
    }
  }

  /**
   * Special random number generating class to help get consistent results for testing.
   *
   * <p>
   * Please use getRandomPoint to get an arbitrary point on the grid to evaluate.
   *
   * <p>
   * When dealing with water, please use getRandomDirection.
   */
  public static class RandomGenerator {

    private static Random randomNumberGeneratorForPoints;
    private static Random randomNumberGeneratorForDirections;
    private static Random randomNumberGeneratorForColors;
    private int numRows;
    private int numCols;

    public RandomGenerator(int seed, int numRows, int numCols) {
      randomNumberGeneratorForPoints = new Random(seed);
      randomNumberGeneratorForDirections = new Random(seed);
      randomNumberGeneratorForColors = new Random(seed);
      this.numRows = numRows;
      this.numCols = numCols;
    }

    public RandomGenerator(int numRows, int numCols) {
      randomNumberGeneratorForPoints = new Random();
      randomNumberGeneratorForDirections = new Random();
      randomNumberGeneratorForColors = new Random();
      this.numRows = numRows;
      this.numCols = numCols;
    }

    public Point getRandomPoint() {
      return new Point(
          randomNumberGeneratorForPoints.nextInt(numRows),
          randomNumberGeneratorForPoints.nextInt(numCols));
    }

    public int getRandomColor() {
      return randomNumberGeneratorForColors.nextInt(2);
    }

    /**
     * Method that returns a random direction.
     *
     * @return an int indicating the direction of movement: 0: Indicating the water should attempt
     * to move down 1: Indicating the water should attempt to move right 2: Indicating the water
     * should attempt to move left
     */
    public int getRandomDirection() {
      return randomNumberGeneratorForDirections.nextInt(3);
    }
  }

  public static void main(String[] args) {
    // Test mode, read the input, run the simulation and print the result
    Scanner in = new Scanner(System.in);
    int numRows = in.nextInt();
    int numCols = in.nextInt();
    int iterations = in.nextInt();
    Solution lab = new Solution(new NullDisplay(numRows, numCols),
        new RandomGenerator(0, numRows, numCols));
    lab.readGridValues(in);
    lab.runNTimes(iterations);
    lab.printGrid();
  }

  /**
   * Read a grid set up from the input scanner.
   */
  private void readGridValues(Scanner in) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = in.nextInt();
      }
    }
  }

  /**
   * Output the current status of the grid for testing purposes.
   */
  private void printGrid() {
    for (int[] ints : grid) {
      System.out.println(Arrays.toString(ints));
    }
  }

  /**
   * Runner that advances the display a determinate number of times.
   */
  private void runNTimes(int times) {
    for (int i = 0; i < times; i++) {
      runOneTime();
    }
  }

  /**
   * Runner that controls the window until it is closed.
   */
  public void run() {
    while (true) {
      runOneTime();
    }
  }

  /**
   * Runs one iteration of the display. Note that one iteration may call step repeatedly depending
   * on the speed of the UI.
   */
  private void runOneTime() {
    for (int i = 0; i < display.getSpeed(); i++) {
      step();
    }
    updateDisplay();
    display.repaint();
    display.pause(1); // Wait for redrawing and for mouse
    int[] mouseLoc = display.getMouseLocation();
    if (mouseLoc != null) { // Test if mouse clicked
      locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }

  /**
   * An implementation of the SandDisplayInterface that doesn't display anything. Used for testing.
   */
  static class NullDisplay implements SandDisplayInterface {

    private int numRows;
    private int numCols;

    public NullDisplay(int numRows, int numCols) {
      this.numRows = numRows;
      this.numCols = numCols;
    }

    public void pause(int milliseconds) {
    }

    public int getNumRows() {
      return numRows;
    }

    public int getNumColumns() {
      return numCols;
    }

    public int[] getMouseLocation() {
      return null;
    }

    public int getTool() {
      return 0;
    }

    public void setColor(int row, int col, Color color) {
    }

    public int getSpeed() {
      return 1;
    }

    public void repaint() {
    }
  }

  /**
   * Interface for the UI of the SandLab.
   */
  public interface SandDisplayInterface {

    public void repaint();

    public void pause(int milliseconds);

    public int[] getMouseLocation();

    public int getNumRows();

    public int getNumColumns();

    public void setColor(int row, int col, Color color);

    public int getSpeed();

    public int getTool();
  }
}