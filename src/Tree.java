import bagel.Image;
import bagel.Input;
import bagel.Font;

/** Class that builds the Tree using super class Actors
 */
public class Tree extends Actor {
    private int numberOfFruits = 3;

    private final Font font = new Font("res/VeraMono.ttf", 24);

    /** constructor for Tree
     *
     * @param x x-coordinate of Tree
     * @param y y-coordinate of Tree
     */
    public Tree(int x, int y) {
        super(ShadowLife.TREE_TYPE, x, y);
        super.setImage(new Image("res/images/tree.png"));
    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        font.drawString(Integer.toString(getNumberOfFruits()), getX(), getY());
        super.update(input);

    }

    /**
     * get the current number of fruits the Hoard has
     * @return the current number of fruits
     */
    public int getNumberOfFruits() {
        return numberOfFruits;
    }

    /**
     * set the current number of fruits the Hoard has
     * @param numberOfFruits the wanted number of fruits
     */
    public void setNumberOfFruits(int numberOfFruits) {
        this.numberOfFruits = numberOfFruits;
    }
}
