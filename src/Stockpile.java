import bagel.Font;
import bagel.Image;
import bagel.Input;

/** Class that builds the Stockpile using super class Actors
 */
public class Stockpile extends Actor {
    private int numberOfFruits = 0;
    private final Font font = new Font("res/VeraMono.ttf", 24);
    /** constructor for Stockpile
     *
     * @param x x-coordinate of Stockpile
     * @param y y-coordinate of Stockpile
     */
    public Stockpile(int x, int y) {
        super(ShadowLife.STOCKPILE_TYPE, x, y);
        super.setImage(new Image("res/images/cherries.png"));
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
 