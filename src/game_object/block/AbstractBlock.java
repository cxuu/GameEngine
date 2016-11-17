package game_object.block;

import java.util.ArrayList;

import game_object.core.AbstractSprite;
import game_object.core.DefaultConstants;
import game_object.core.ImageStyle;

abstract class AbstractBlock extends AbstractSprite implements IBlock {

	protected int myCollisionBitMask =
			DefaultConstants.HERO_CATEGORY_BIT_MASK |
			DefaultConstants.ENEMY_CATEGORY_BIT_MASK;
	protected BlockCollisionBehavior myCollisionBehavior;
	
	protected AbstractBlock(double x, double y, ArrayList<String> imgPaths, BlockCollisionBehavior bcb) {
		super(x, y, imgPaths);
		myCollisionBehavior = bcb;
		setImgStyle(ImageStyle.TILE);
	}

	@Override
	public BlockCollisionBehavior getCollisionBehavior() {
		return myCollisionBehavior;
	}

	@Override
	public void setCollisionBehavior(BlockCollisionBehavior collisionBehavior) {
		this.myCollisionBehavior = collisionBehavior;
	}
	
}
