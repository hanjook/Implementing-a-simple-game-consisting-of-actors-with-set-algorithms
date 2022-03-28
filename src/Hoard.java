import bagel.Font;
import bagel.Image;
import bagel.Input;

/** Class that builds the Hoard using super class Actors
 */
public class Hoard extends Actor{
    private int numberOfFruits = 0;
    private final Font font = new Font("res/VeraMono.ttf", 24);

    /** constructor for Hoard
     *
     * @param x x-coordinate of Hoard
     * @param y y-coordinate of Hoard
     */
    public Hoard(int x, int y) {
        super(ShadowLife.HOARD_TYPE, x, y);
        super.setImage(new Image("res/images/hoard.png"));
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
