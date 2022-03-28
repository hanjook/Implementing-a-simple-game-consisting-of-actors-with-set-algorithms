import bagel.Image;
import bagel.Input;

/** Class that builds the Sign using super class Actors
 */
public class Sign extends Actor{
    /** constructor for Sign
     *
     * @param x x-coordinate of Sign
     * @param y y-coordinate of Sign
     */
    public Sign(int direction, int x, int y) {
        super(direction, x, y);
        if (direction == ShadowLife.UP_TYPE) {
            super.setImage(new Image("res/images/up.png"));
        }
        if (direction == ShadowLife.DOWN_TYPE) {
            super.setImage(new Image("res/images/down.png"));
        }
        if (direction == ShadowLife.LEFT_TYPE) {
            super.setImage(new Image("res/images/left.png"));
        }
        if (direction == ShadowLife.RIGHT_TYPE) {
            super.setImage(new Image("res/images/right.png"));
        }

    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }
}
