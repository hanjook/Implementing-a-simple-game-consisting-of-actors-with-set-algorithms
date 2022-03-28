import bagel.Image;
import bagel.Input;

import java.util.ArrayList;


/** Class that builds the Thief using super class Actors
 */
public class Thief extends Actor{
    private boolean carrying = false;
    private boolean consuming = false;
    private static final int DEFAULT_DIRECTION = ShadowLife.UP_TYPE;

    /** constructor for Thief
     *
     * @param x x-coordinate of Thief
     * @param y y-coordinate of Thief
     */
    public Thief(int x, int y) {
        super(DEFAULT_DIRECTION, ShadowLife.THIEF_TYPE, x, y);
        super.setImage(new Image("res/images/thief.png"));
    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }


    /**
     * override the checkPosition method in Actors to implement logic specific to thieves.
     * @param actors the ArrayList of stationary actors.
     */
    @Override
    public void checkPosition(ArrayList<Object> actors, ArrayList<Object> mobileActors, int index) {
        super.checkPosition(actors, mobileActors, index);
        for (Object actor : actors) {
            int actorX = ((Actor)actor).getX();
            int actorY = ((Actor)actor).getY();
            /*
                if the thief is standing on a stationary Actor
             */
            if (getX() == actorX && getY() == actorY) {

                /*
                    if the thief is standing on a mitosis pool, implement the mitosis pool logic
                 */
                if (((Actor)actor).getType() == ShadowLife.MITOSIS_POOL_TYPE) {
                    mitosisPoolSpawn((Actor)mobileActors.get(index), mobileActors, index);
                }
                /*
                    if thief is standing on a Golden tree, implement Golden tree specific logic
                 */
                else if (((Actor)actor).getType() == ShadowLife.PAD_TYPE) {
                    consuming = true;
                }
                /*
                    if thief is standing on a Golden Tree and if the thief is not carrying
                 */
                else if (((Actor)actor).getType() == ShadowLife.GOLDEN_TREE_TYPE && !carrying) {
                    carrying = true;
                }
                /*
                    if thief is standing on a Tree and if the thief is not carrying
                 */
                else if (((Actor)actor).getType() == ShadowLife.TREE_TYPE && !carrying) {
                    onTree(((Tree)actor));
                }
                /*
                    if thief is standing on a Hoard
                 */
                else if (((Actor)actor).getType() == ShadowLife.HOARD_TYPE) {
                    onHoard(((Hoard)actor));
                }
                /*
                    if thief is standing on a Stockpile
                 */
                else if (((Actor)actor).getType() == ShadowLife.STOCKPILE_TYPE) {
                    onStockpile((Stockpile)actor);
                }

                break;
            }

        }
        /*  check if its standing on a gatherer
         */
        for (Object mobileActor : mobileActors) {
            if (((Actor)mobileActor).getType() == ShadowLife.GATHERER_TYPE) {
                if (((Gatherer)mobileActor).getX() == getX() && ((Gatherer)mobileActor).getY() == getY()) {
                    ((Thief)mobileActors.get(index)).changeDirection("90a");
                }
            }
        }
    }

    /**
     *  method to implement the mitosisPool logic:
     *  if it is standing on a mitosis pool, spawn two new thieves and place them
     *  facing in opposite directions and destroy the current thief
     *
     *  @param thief the original thief that landed on the mitosis pool
     *  @param mobileActors the ArrayList of mobile Actors
     *  @param index the index of the original actor in mobile Actors
     */
    private void mitosisPoolSpawn(Actor thief, ArrayList<Object> mobileActors, int index) {
        int x = thief.getX();
        int y = thief.getY();

        /*
            create two new thieves with the original thief's location
         */
        Thief thief1 = new Thief(x, y);
        Thief thief2 = new Thief(x, y);

        /*
            set the new thieves to face in opposing directions
         */
        thief1.setDirection(thief.getDirection());
        thief1.changeDirection("90a");
        thief2.setDirection(thief.getDirection());
        thief2.changeDirection("90c");
        mobileActors.add(thief1);
        mobileActors.add(thief2);
        mobileActors.set(index, null);

    }
    /**
     * take away a fruit from the tree and make the thief carry it
     * @param tree the reference to the tree in ArrayList Actors
     */
    private void onTree(Tree tree) {
        int fruitNumber = tree.getNumberOfFruits();
        /*
            if the number of fruits on a tree is more than 0
         */
        if (fruitNumber > 0) {
            tree.setNumberOfFruits(fruitNumber - 1);
            carrying = true;
        }
    }

    /**
     * take away a fruit from the hoard if not carrying
     * if the hoard is empty, change direction by 90 degrees clockwise
     * Add a fruit to the hoard if carrying and change direction by 90 degrees clockwise
     * @param hoard the reference to the hoard in the ArrayList Actors
     */
    private void onHoard(Hoard hoard) {
        int FruitNumber = hoard.getNumberOfFruits();
        if (consuming) {
            consuming = false;
            if (!carrying) {
                /*
                    if the hoard is not empty
                 */
                if (FruitNumber > 0) {
                    carrying = true;
                    hoard.setNumberOfFruits(FruitNumber - 1);
                }
                else {
                    changeDirection("90c");
                }
            }
        }
        else if (carrying) {
            carrying = false;
            hoard.setNumberOfFruits(FruitNumber + 1);
            changeDirection("90c");
        }
    }

    /**
     * take away a fruit from the Stockpile if not carrying
     * and change direction by 90 degrees clockwise if the
     * stockpile is empty
     * if it is carrying, change direction by 90 degrees clockwise
     * @param stockpile the reference to the stockpile in the ArrayList Actors
     */
    private void onStockpile(Stockpile stockpile) {
        int FruitNumber = stockpile.getNumberOfFruits();
        if (!carrying) {
            /*
                if stockpile is not empty
             */
            if (FruitNumber > 0) {
                carrying = true;
                consuming = false;
                stockpile.setNumberOfFruits(FruitNumber - 1);
                changeDirection("90c");
            }
        }
        else {
            changeDirection("90c");
        }
    }
}

