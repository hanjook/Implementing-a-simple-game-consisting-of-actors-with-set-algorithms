import bagel.Image;
import bagel.Input;

import java.util.ArrayList;


/** Class that builds the Gatherer using super class Actors
 *  and implements the logic for it.
 */
public class Gatherer extends Actor {
    private boolean carrying = false;
    private static final int DEFAULT_DIRECTION = ShadowLife.LEFT_TYPE;

    /** constructor for Gatherer
     *
     * @param x x-coordinate of Gatherer
     * @param y y-coordinate of Gatherer
     */
    public Gatherer(int x, int y) {
        super(DEFAULT_DIRECTION, ShadowLife.GATHERER_TYPE, x, y);
        super.setImage(new Image("res/images/gatherer.png"));
    }


    /**
     * Use the update method in Actors
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }

    /**
     * override the checkPosition method in Actors to implement logic specific to gatherers.
     * @param actors the ArrayList of stationary actors.
     */

    @Override
    public void checkPosition(ArrayList<Object> actors, ArrayList<Object> mobileActors, int index) {
        super.checkPosition(actors, mobileActors, index);
        for (Object actor : actors) {
            int actorX = ((Actor) actor).getX();
            int actorY = ((Actor) actor).getY();

            /*
                if the gatherer is standing on a stationary Actor
             */
            if (getX() == actorX && getY() == actorY) {

                /*
                    if the gatherer is standing on a mitosis pool, implement the mitosis pool logic
                 */
                if (((Actor)actor).getType() == ShadowLife.MITOSIS_POOL_TYPE) {
                    mitosisPoolSpawn((Actor)mobileActors.get(index), mobileActors, index);
                }

                /*
                    if gatherer is standing on a tree, implement tree specific logic
                 */
                else if (((Actor) actor).getType() == ShadowLife.TREE_TYPE) {
                    onTree((Tree) actor);
                }

                /*
                    if gatherer is standing on a Golden tree, implement Golden tree specific logic
                 */
                else if (((Actor) actor).getType() == ShadowLife.GOLDEN_TREE_TYPE) {
                    onGoldenTree();
                }

                /*
                    if gatherer is standing on a Stockpile or Hoard, implement logic of the Stockpile or Hoard
                    (they have the same logic)
                 */
                else if (((Actor) actor).getType() == ShadowLife.HOARD_TYPE) {
                    onStockpileOrHoard((Actor) actor);
                }

                else if (((Actor) actor).getType() == ShadowLife.STOCKPILE_TYPE) {
                    onStockpileOrHoard((Actor) actor);
                }

                break;

            }
        }
    }


    /**
     *  method to implement the mitosisPool logic:
     *  if it is standing on a mitosis pool, spawn two new gatherers and place them
     *  facing in opposite directions and destroy the current gatherer
     *
     *  @param gatherer the original gatherer that landed on the mitosis pool
     *  @param mobileActors the ArrayList of mobile Actors
     *  @param index the index of the original actor in mobile Actors
     */
    private void mitosisPoolSpawn(Actor gatherer, ArrayList<Object> mobileActors, int index) {
        int x = gatherer.getX();
        int y = gatherer.getY();
        /*
            create two new gatherers with the original gatherer's location
         */
        Gatherer gatherer1 = new Gatherer(x, y);
        Gatherer gatherer2 = new Gatherer(x, y);
        /*
            set the new gatherers to face in opposing directions
         */
        gatherer1.setDirection(gatherer.getDirection());
        gatherer1.changeDirection("90a");
        gatherer2.setDirection(gatherer.getDirection());
        gatherer2.changeDirection("90c");
        mobileActors.add(gatherer1);
        mobileActors.add(gatherer2);
        mobileActors.set(index, null);

    }

    /**
        take a fruit from the tree if the gatherer is not
        carrying anything and change direction by 180 degrees. Else, just move on.
        @param tree reference to the tree in the ArrayList Actors
     */
    private void onTree(Tree tree) {
        /*
            if there are more than 0 fruits
         */
        if (!carrying  && tree.getNumberOfFruits() > 0) {
            int newFruitNumber = tree.getNumberOfFruits() - 1;
            tree.setNumberOfFruits(newFruitNumber);
            carrying = true;
            changeDirection("180");

        }
    }

    /**
     *  if the gatherer is not carrying anything, merely change the direction by 180 degrees
     *  and set carrying to be true
     */
    private void onGoldenTree() {
        if(!carrying) {
            carrying = true;
            changeDirection("180");
        }
    }

    /**
     * If the gatherer is carrying a fruit, then add the fruit to the
     * stockpile or hoard and set carrying to be false
     * change direction by 180 degrees
     * @param actor the reference to the Hoard or Stockpile in the ArrayList Actors
     */
    private void onStockpileOrHoard(Actor actor) {
        if (carrying && actor.getType() == ShadowLife.HOARD_TYPE) {
            int newFruitNumber = ((Hoard)actor).getNumberOfFruits() + 1;
            carrying = false;
            ((Hoard)actor).setNumberOfFruits(newFruitNumber);
        }
        else if (carrying && actor.getType() == ShadowLife.STOCKPILE_TYPE){
            int newFruitNumber = ((Stockpile)actor).getNumberOfFruits() + 1;
            carrying = false;
            ((Stockpile)actor).setNumberOfFruits(newFruitNumber);
        }
        changeDirection("180");
    }


}

