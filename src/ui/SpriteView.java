package ui;

import game_object.core.ISprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends View {
	
	private ISprite mySprite;
	private ImageView imageView;
	private CanvasView myCanvas;
	
	public SpriteView(AuthoringController controller) {
		super(controller);
	}
	
	public void setSprite(ISprite sprite) {
		mySprite = sprite;
		initUI();
	}
	
	public ISprite getSprite() {
		return mySprite;
	}
	
	public void setCanvasView(CanvasView canvas) {
		myCanvas = canvas;
	}
	
	@Override
	public void setPositionX(double x) {
		myCanvas.setPosition(this, x, mySprite.getPosition().getY());
	}
	
	@Override
	public void setPositionY(double y) {
		myCanvas.setPosition(this, mySprite.getPosition().getX(), y);
	}
	
	@Override
	protected void initUI() {
		if (mySprite == null) return;
		String path = mySprite.getImagePaths().get(0);
		Image image = new Image(path);
		imageView = new ImageView(image);
		imageView.setFitHeight(image.getHeight());
		imageView.setFitWidth(image.getWidth());
		this.addUI(imageView);
		imageView.setOnMouseClicked(e -> {
			this.getController().selectSpriteView(this);});
	}

	@Override
	protected void layoutSelf() {
		System.out.println("laying out");
	}

}
