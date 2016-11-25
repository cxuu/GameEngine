package game_player_menu;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class MenuSceneGenerator implements IMenuSceneGenerator{
	private Scene myMenuScene;
	private List<DisplayableItemDescription> myDisplayableMenuItems;
	private PaneCreator myPaneCreator;
	
	public MenuSceneGenerator(){
		myPaneCreator = new PaneCreator();
		myDisplayableMenuItems = new ArrayList<DisplayableItemDescription>();
	}
	
	
	public abstract Scene generateScene();
	
	public void makeItemsDisplayable(List<ItemDescription> menuItems) {
		for(ItemDescription item : menuItems){
			DisplayableItemDescription currentTranslation = new DisplayableItemDescription(item, myPaneCreator);
			myDisplayableMenuItems.add(currentTranslation);
		}
	}
	
	public Scene getMenuScene(List<ItemDescription> menuItems){
		return myMenuScene;
	}
	
	
}
