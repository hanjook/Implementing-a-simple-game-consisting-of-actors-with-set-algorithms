import bagel.*;
import bagel.Image;

import java.io.IOException;
import java.lang.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/** This is where the main function runs ShadowLife,
 * containing the method update that has the logic to
 * control when the Gatherers change direction at what
 * frequency
 *
 */

public class ShadowLife extends AbstractGame{
    private final Image background;
    private final ReadCsv csvFile;
    private final ArrayList<Object> actors;
    private final ArrayList<Object> mobileActors;

    // the initial time
    private long previous = System.currentTimeMillis();
    private int tickCount = 0;

    // constant that has the amount of milliseconds per tick
    private static final int MILLI_SECONDS = Integer.parseInt(argsFromFile()[0]);


    // coordinates for the top left position of the map
    private static final int INIT_MAP_X_POS = 0;
    private static final int INIT_MAP_Y_POS = 0;

    // make it public so the classes Actors, Tree and Gatherer can use it
    public static final int TREE_TYPE = 0;
    public static final int GATHERER_TYPE = 1;
    public static final int THIEF_TYPE = 2;
    public static final int GOLDEN_TREE_TYPE = 3;
    public static final int STOCKPILE_TYPE = 4;
    public static final int HOARD_TYPE = 5;
    public static final int PAD_TYPE = 6;
    public static final int FENCE_TYPE = 7;
    public static final int MITOSIS_POOL_TYPE = 8;
    public static final int UP_TYPE = 9;
    public static final int RIGHT_TYPE = 10;
    public static final int DOWN_TYPE = 11;
    public static final int LEFT_TYPE = 12;





    /**make a constructor that sets up the memory for the images
     * needed in the program and assigns the default initial
     * values for the tick counter.
     */
    public ShadowLife() {
        super();

        /*
        If the arguments given are less than 3 arguments
        return error message
         */
        if (argsFromFile().length != 3) {
            System.out.println("ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        /*
        If one or both of the first two arguments are not integers, return error message
         */
        else if (!checkValidInt(argsFromFile()[0]) || !checkValidInt(argsFromFile()[1])) {
            System.out.println("ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        this.csvFile = new ReadCsv(argsFromFile()[2]);
        this.actors = new ArrayList<>();
        this.mobileActors = new ArrayList<>();
        this.background = new Image("res/images/background.png");

        /* add the Actors to the ArrayList actors in order to draw them later on in
         * the map. Add the Gatherer and Thief to the ArrayList mobileActors
         */
        for (int i = 0; i < csvFile.getLength(); i++) {
            int x = csvFile.getXPos().get(i);
            int y = csvFile.getYPos().get(i);
            if(csvFile.getType().get(i) == GATHERER_TYPE) {
                mobileActors.add(new Gatherer(x, y));
            }
            if(csvFile.getType().get(i) == THIEF_TYPE) {
                mobileActors.add(new Thief(x, y));
            }
            else {
                if(csvFile.getType().get(i) == ShadowLife.TREE_TYPE) {
                    actors.add(new Tree(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.GOLDEN_TREE_TYPE) {
                    actors.add(new GoldenTree(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.STOCKPILE_TYPE) {
                    actors.add(new Stockpile(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.HOARD_TYPE) {
                    actors.add(new Hoard(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.PAD_TYPE) {
                    actors.add(new Pad(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.FENCE_TYPE) {
                    actors.add(new Fence(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.MITOSIS_POOL_TYPE) {
                    actors.add(new MitosisPool(x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.UP_TYPE) {
                    actors.add(new Sign(UP_TYPE, x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.DOWN_TYPE) {
                    actors.add(new Sign(DOWN_TYPE, x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.LEFT_TYPE) {
                    actors.add(new Sign(LEFT_TYPE, x, y));
                }
                if(csvFile.getType().get(i) == ShadowLife.RIGHT_TYPE) {
                    actors.add(new Sign(RIGHT_TYPE, x, y));
                }

            }

        }

    }



    public static void main(String[] args) {
        ShadowLife game =  new ShadowLife();
        game.run();
    }


    /**
     * method used to update the status of the Actors for every frame
     *      * that passes.
     */

    @Override
    protected void update(Input input) {

        /*
            If the maximum amount of ticks have been reached,
            return timed out.
         */
        if (tickCount == Integer.parseInt(argsFromFile()[1])) {
            System.out.println("Timed out");
            Window.close();
            System.exit(-1);
        }

        /*
            If all the Actors are inactive, print out ending message
         */
        if (checkAllInactive(mobileActors)) {
            System.out.println(tickCount + " ticks");
            for (Object actor : actors) {
                if (((Actor)actor).getType() == STOCKPILE_TYPE) {
                    System.out.println(((Stockpile)actor).getNumberOfFruits());
                }
                else if (((Actor)actor).getType() == HOARD_TYPE) {
                    System.out.println(((Hoard)actor).getNumberOfFruits());
                }
            }
            Window.close();
        }

        /* attribute that keeps the current time that passed during this frame.
         */
        long current = System.currentTimeMillis();
        background.drawFromTopLeft(INIT_MAP_X_POS,INIT_MAP_Y_POS);

        /*
        update all stationary actors in this tick
         */
        for (Object actor : actors) {
            ((Actor)actor).update(input);
        }

        /* draw the current status of the gatherers and thieves in the
         * mobileActors arrayList.
         * At every tick, check whether the mobileActor is on the same location as
         * an Actor and do an action caused by that Actor.
         */

        for (int i = 0; i < mobileActors.size(); i++) {
            if (mobileActors.get(i) != null) {
                if (current - previous >= MILLI_SECONDS) {
                    if(((Actor)mobileActors.get(i)).getType() == GATHERER_TYPE) {
                        /*
                            check if the Gatherer is active
                         */
                        if (((Gatherer)mobileActors.get(i)).isActive()) {
                            /*
                                move the Gatherer in the direction it is in
                             */
                            ((Gatherer)mobileActors.get(i)).move();
                            /* check the position of the gatherer and if it is on a stationary Actor
                               do the action corresponding to it.
                             */
                            ((Gatherer)mobileActors.get(i)).checkPosition(actors, mobileActors, i);

                        }
                    }

                    else if (((Actor)mobileActors.get(i)).getType() == THIEF_TYPE) {
                        /*
                            check if the Thief is active
                         */
                        if (((Thief)mobileActors.get(i)).isActive()) {
                            /*
                                move the Thief in the direction it is in
                             */
                            ((Thief)mobileActors.get(i)).move();

                            /* check the position of the thief and if it is on a stationary Actor
                               do the action corresponding to it.
                             */
                            ((Thief)mobileActors.get(i)).checkPosition(actors, mobileActors, i);

                        }
                    }

                    if (i == mobileActors.size() - 1) {
                        tickCount++;
                        previous = current;
                    }
                }

                /*
                    show the mobile actors in the window
                 */
                if (mobileActors.get(i) != null) {
                    ((Actor)mobileActors.get(i)).update(input);
                }
            }
        }
    }


    /**
     * method to read the args.txt file that will be provided
     * with tick rate, max ticks, world file
     */
    private static String[] argsFromFile() {
        try {
            return Files.readString(Path.of("res/args.txt"), Charset.defaultCharset()) .split(" ");
        } catch (IOException e) { e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param string the string that will be passed to check if it is an integer
     * @return true if it is an integer, false if not
     */
    public static boolean checkValidInt(String string) {
        try {
            int num = Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * The method to check whether all mobile actors in the world are
     * inactive.
     * @param mobileActors the ArrayList of mobile actors.
     * @return false if any are still active, and true if all are inactive
     */
    private boolean checkAllInactive(ArrayList<Object> mobileActors) {
        for (Object mobileActor : mobileActors) {
            if (mobileActor != null) {
                if (((Actor) mobileActor).isActive()) {
                    return false;
                }
            }
        }
        return true;
    }

}
