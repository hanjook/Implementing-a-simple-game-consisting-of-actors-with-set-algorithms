import bagel.Image;
import bagel.Input;

/** Class that builds the Golden Tree using super class Actors
 */
public class GoldenTree extends Actor{
    /** constructor for Golden Tree
     *
     * @param x x-coordinate of Golden Tree
     * @param y y-coordinate of Golden Tree
     */
    public GoldenTree(int x, int y) {
        super(ShadowLife.GOLDEN_TREE_TYPE, x, y);
        super.setImage(new Image("res/images/gold-tree.png"));
    }

    /** Use the update method in Actors
     *
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }
}
