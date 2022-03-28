import bagel.Image;
import bagel.Input;

/** Class that builds the Pad using super class Actors
 */
public class Pad extends Actor{
    /** constructor for Tree
     *
     * @param x x-coordinate of Tree
     * @param y y-coordinate of Tree
     */
    public Pad(int x, int y) {
        super(ShadowLife.PAD_TYPE, x, y);
        super.setImage(new Image("res/images/pad.png"));
    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }
}
