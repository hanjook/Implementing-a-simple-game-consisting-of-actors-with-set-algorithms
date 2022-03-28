import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** This is the ReadCsv class that scans through the
 * csv file that is passed and retrieves the coordinates
 * of the Actors by making three arraylists
 * type of actor, x-coordinate and y-coordinate
 *
 */

public class ReadCsv {
    private final ArrayList<Integer> type;
    private final ArrayList<Integer> xPos;
    private final ArrayList<Integer> yPos;
    private int length;



    /** Constructor for ReadCsv
     *
     * @param directory the name of the file that will be used
     *                  for the world
     */
    public ReadCsv(String directory) {
        this.type = new ArrayList<>();
        this.xPos = new ArrayList<>();
        this.yPos = new ArrayList<>();
        File newDirectory = new File(directory);
        Scanner scanner = null;

        /* Check if the file is valid
         */
        try {
            scanner = new Scanner(newDirectory);
        } catch (FileNotFoundException e) {
            System.out.println("error: file \"<" + directory + ">\" not found");
            e.printStackTrace();
        }

        /* while loop that stores the number of Trees and the
         * number of gatherers while storing the type (Tree/Gatherer)
         * in one array, the x-coordinate in another array and the
         * y-coordinate in another array.
         *
         */
        int lineNumber = 1;
        while (true) {
            assert scanner != null;
            if (!scanner.hasNext()) break;

            String line = scanner.nextLine();
            String[] sep = line.split(",");
            if (sep.length != 3) {
                System.out.println("error: in file \"<" + directory + ">\" at line " + lineNumber);
                System.exit(-1);
            }
            else if (!ShadowLife.checkValidInt(sep[1]) || !ShadowLife.checkValidInt(sep[2])) {
                System.out.println("error: in file \"<" + directory + ">\" at line " + lineNumber);
                System.exit(-1);
            }
            switch (sep[0]) {
                case "Tree":
                    type.add(ShadowLife.TREE_TYPE);
                    length++;
                    break;
                case "Gatherer":
                    type.add(ShadowLife.GATHERER_TYPE);
                    length++;
                    break;
                case "Stockpile":
                    type.add(ShadowLife.STOCKPILE_TYPE);
                    length++;
                    break;
                case "Fence":
                    type.add(ShadowLife.FENCE_TYPE);
                    length++;
                    break;
                case "Thief":
                    type.add(ShadowLife.THIEF_TYPE);
                    length++;
                    break;
                case "Pad":
                    type.add(ShadowLife.PAD_TYPE);
                    length++;
                    break;
                case "GoldenTree":
                    type.add(ShadowLife.GOLDEN_TREE_TYPE);
                    length++;
                    break;
                case "Hoard":
                    type.add(ShadowLife.HOARD_TYPE);
                    length++;
                    break;
                case "Pool":
                    type.add(ShadowLife.MITOSIS_POOL_TYPE);
                    length++;
                    break;
                case "SignUp":
                    type.add(ShadowLife.UP_TYPE);
                    length++;
                    break;
                case "SignDown":
                    type.add(ShadowLife.DOWN_TYPE);
                    length++;
                    break;
                case "SignLeft":
                    type.add(ShadowLife.LEFT_TYPE);
                    length++;
                    break;
                case "SignRight":
                    type.add(ShadowLife.RIGHT_TYPE);
                    length++;
                    break;
            }

            /*
            take length out here once the signs are up
             */

            xPos.add(Integer.parseInt(sep[1]));
            yPos.add(Integer.parseInt(sep[2]));
            lineNumber++;
        }
    }


    /*
     *  The getters of the attributes in ReadCsv
     */

    /**
     *
     * @return the list of the Actors
     */
    public ArrayList<Integer> getType() {
        return type;
    }


    /**
     *
     * @return the current x-coordinate
     */
    public ArrayList<Integer> getXPos() {
        return xPos;
    }


    /**
     *
     * @return the current y-coordinate
     */
    public ArrayList<Integer> getYPos() {
        return yPos;
    }

    public int getLength() {
        return length;
    }
}
