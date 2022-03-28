import bagel.Image;
import bagel.Input;

import java.util.ArrayList;


/** This is the class that will be the super class of
 * the Tree class and Gatherer class. It contains the
 * method move, which has the algorithm for the
 * movement of an Actor and the method update which will
 * draw the Actor.
 *
 */

public abstract class Actor {
    private final int type;
    private int x;
    private int y;
    private Image image;
    private int direction;
    private static final int SPEED = 64;
    private boolean active = true;


    /**
     * constructor for Actors
     *
     * @param defaultDirection is the default direction an Actor will be set in
     *                         (only applies to Gatherers and Thieves)
     *
     * @param type is for the type of Actor (e.g. Gatherer or Tree)
     *             each type is assigned to an int number
     * @param x is the x-coordinate of the Actor that will be passed into
     *          this class
     * @param y is the y-coordinate of the Actor that will be passed into
     *          this class
     */

    public Actor(int defaultDirection, int type, int x, int y) {
        this.type = type;
        this.direction = defaultDirection;
        this.x = x;
        this.y = y;
    }

    public Actor(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }


    /**
     * method that takes the current direction the Actor is in and moves the Actor
     * by the indicated SPEED in that direction
     */
    public void move() {
        if (direction == ShadowLife.UP_TYPE) {
            y -= SPEED;
        }
        else if (direction == ShadowLife.DOWN_TYPE) {
            y += SPEED;
        }
        else if (direction == ShadowLife.LEFT_TYPE) {
            x -= SPEED;
        }
        else if (direction == ShadowLife.RIGHT_TYPE) {
            x += SPEED;
        }
    }

    /**
     * Draws the Actor at the coordinates (x,y)
     */
    public void update(Input input) {
        image.drawFromTopLeft(x, y);
    }

    /**
     * Method that checks the position of the Gatherer/Thief and implements the
     * logic that happens when the mobile Actor lands on a stationary Actor.
     *
     * @param actors the ArrayList of the stationary Actors
     *          a mitosis pool or not
     */
    public void checkPosition(ArrayList<Object> actors, ArrayList<Object> mobileActors, int index) {
        for (Object actor : actors) {
            int actorX = ((Actor)actor).getX();
            int actorY = ((Actor)actor).getY();
            if (getX() == actorX && getY() == actorY) {
                /*
                    if the mobile Actor lands on a fence, move to the previous position and
                    set the Actor to be inactive.
                 */
                if (((Actor)actor).getType() == ShadowLife.FENCE_TYPE) {
                    changeDirection("180");
                    move();
                    setActive(false);

                }
                /*
                    If the mobile Actor lands on a sign that is either UP, DOWN, LEFT or RIGHT,
                    set the direction of the mobile Actor to match the sign's direction.
                 */
                else if (((Actor)actor).getType() == ShadowLife.UP_TYPE) {
                    direction = ShadowLife.UP_TYPE;

                }
                else if (((Actor)actor).getType() == ShadowLife.DOWN_TYPE) {
                    direction = ShadowLife.DOWN_TYPE;

                }
                else if (((Actor)actor).getType() == ShadowLife.LEFT_TYPE) {
                    direction = ShadowLife.LEFT_TYPE;

                }
                else if (((Actor)actor).getType() == ShadowLife.RIGHT_TYPE) {
                   direction = ShadowLife.RIGHT_TYPE;

                }
                break;

            }

        }
    }



    /**
     * Changes the direction by the direction inserted
     * @param degreeDirection a string indicating the direction it turns and by how much
     */
    public void changeDirection(String degreeDirection) {

        /*
            changes direction by 90 degrees clockwise
         */
        switch (degreeDirection) {
            case "90c":
                if (direction == ShadowLife.LEFT_TYPE) {
                    direction = ShadowLife.UP_TYPE;
                } else {
                    direction++;
                }
                break;
        /*
            changes direction by 90 degrees anti-clockwise
         */
            case "90a":
                if (direction == ShadowLife.UP_TYPE) {
                    direction = ShadowLife.LEFT_TYPE;
                } else {
                    direction--;
                }
                break;

        /*
            changes direction by 180 degrees
         */
            case "180":
                if (direction == ShadowLife.DOWN_TYPE) {
                    direction = ShadowLife.UP_TYPE;
                } else if (direction == ShadowLife.LEFT_TYPE) {

                    direction = ShadowLife.RIGHT_TYPE;
                } else {
                    direction = direction + 2;
                }
                break;
        }
    }


    /**
     * method to access x from outside this class.
     * @return x, the x coordinate of the Actor
     */
    public int getX() {
        return x;
    }

    /**
     * method to access y outside this class.
     * @return y, the y coordinate of the Actor
     */
    public int getY() {
        return y;
    }

    /**
     * method in order to set the Image of each subclass
     * @param image the image that corresponds to the type of Actor
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * method to check whether the Actor is Active
     * @return active is the boolean that shows whether it is active or not
     */
    public boolean isActive() {
        return active;
    }

    /**
     * method that will be used when the Actor meets a fence to set the Actor to be inactive
     * @param active the state of the activeness
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * method to get the type of the Actor
     * @return type the type of the Actor
     */
    public int getType() {
        return type;
    }

    /**
     * method to get the direction of the Actor in classes Gatherer and Thief
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * method to set the direction of the Actor in classes Gatherer and Thief
     * @param direction the wanted direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

}
