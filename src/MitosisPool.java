import bagel.Image;
import bagel.Input;

/** Class that builds the Mitosis Pool using super class Actors
 */
public class MitosisPool extends Actor{
    /** constructor for Mitosis Pool
     *
     * @param x x-coordinate of Mitosis Pool
     * @param y y-coordinate of Mitosis Pool
     */
    public MitosisPool(int x, int y) {
        super(ShadowLife.MITOSIS_POOL_TYPE, x, y);
        super.setImage(new Image("res/images/pool.png"));
    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }
}
