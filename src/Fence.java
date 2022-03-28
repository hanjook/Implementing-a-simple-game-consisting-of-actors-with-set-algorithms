import bagel.Image;
import bagel.Input;

/**
 * Class that builds the Fence using super class Actors
 */
public class Fence extends Actor {
    /** constructor for Fence
     *
     * @param x x-coordinate of Fence
     * @param y y-coordinate of Fence
     */
    public Fence(int x, int y) {
        super(ShadowLife.FENCE_TYPE, x, y);
        super.setImage(new Image("res/images/fence.png"));
    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }
}
