package authoring.view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import authoring.AuthoringController;
import authoring.View;
import game_object.GameObjectType;
import game_object.constants.GameObjectConstants;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ComponentsView extends View {

	private List<Component> enemyList, blockList, personalizedList;
	private HBox personalizedHBox;
	private Button upload;

	public ComponentsView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void layoutSelf() {
	}

	@Override
	protected void initUI() {
		TabPane tabPane = new TabPane();
		tabPane.setPrefHeight(this.getHeight());
		tabPane.setPrefWidth(this.getWidth());
		this.addUI(tabPane);
		
		initEnemyTab(tabPane);
		initBlockTab(tabPane);
		initUploadedTab(tabPane);
	}
	
	protected void initEnemyTab(TabPane tabPane) {
		Tab enemyTab = initTab("Enemy");
		enemyTab.setClosable(false);
		tabPane.getTabs().add(enemyTab);
		
		HBox hbox = initNewHBox();
		
		ScrollPane scrollPane = initScrollPane();
		scrollPane.setContent(hbox);
		
		initEnemyGraphics();
		
		for (Component enemy : enemyList) {
			ComponentView c = createComponentView(enemyList, enemy);
			hbox.getChildren().add(c.getUI());
		}
		enemyTab.setContent(scrollPane);
	}
	
	protected void initBlockTab(TabPane tabPane) {
		Tab blockTab = initTab("Block");
		blockTab.setClosable(false);
		tabPane.getTabs().add(blockTab);
		
		HBox hbox = initNewHBox();
		
		ScrollPane scrollPane = initScrollPane();
		scrollPane.setContent(hbox);

		initBlockGraphics();
		
		for (Component block : blockList) {
			ComponentView component = createComponentView(blockList, block);
			hbox.getChildren().add(component.getUI());
		}
		blockTab.setContent(scrollPane);
		
	}
	
	private void initUploadedTab(TabPane tabPane) {
		Tab uploadTab = initTab("Uploaded");
		uploadTab.setClosable(false);
		tabPane.getTabs().add(uploadTab);
		
		personalizedHBox = initNewHBox();
		
		ScrollPane scrollPane = initScrollPane();
		scrollPane.setContent(personalizedHBox);
		
		personalizedHBox.getChildren().add(initUploadButton());
		
		uploadTab.setContent(scrollPane);
	}

	protected Tab initTab(String tabName) {
		Tab tab = new Tab();
		tab.setText(tabName);
		return tab;
	}
	
	protected ScrollPane initScrollPane(){
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToHeight(true);
		return scrollPane;
	}
	
	protected HBox initNewHBox() {
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
	protected void initEnemyGraphics(){
		enemyList = new ArrayList<>();

		enemyList.add(GameObjectConstants.BLUE_SNAIL);
		enemyList.add(GameObjectConstants.ELIZA);
		enemyList.add(GameObjectConstants.ORANGE_MUSHROOM);
		enemyList.add(GameObjectConstants.RIBBON_PIG);
		enemyList.add(GameObjectConstants.SLIME);
	}
	
	protected void initBlockGraphics(){
		blockList = new ArrayList<>();
		blockList.add(GameObjectConstants.BRICK);
		blockList.add(GameObjectConstants.BUSH);
	}
	
	protected ComponentView createComponentView(List<Component> list, Component enemy) {
		ComponentView c = new ComponentView(this.getController());
		c.setWidth(50);
		c.setComponent(enemy);
		return c;
	}
	
	private Button initUploadButton(){
		upload = new Button();
		ImageView uploadImage = new ImageView(GameObjectConstants.UPLOAD);
		uploadImage.setFitHeight(50);
		uploadImage.setFitWidth(50);
		upload.setGraphic(uploadImage);
		personalizedList = new ArrayList<>();
		initUploadButtonAction();
		
		return upload;
	}
	
	private void initUploadButtonAction(){
		upload.setOnAction((event) -> {
			
			File userFile = userChosenFile();
			String userFileName = userChosenName();
			String userSpriteType = userChosenType();
			
			if ((!userFileName.equals("") && (userFile != null)))  {
				updatePersonalizedList(userFile.toURI().toString(), userFileName, userSpriteType);
			}
				
		});
	}

	private void updatePersonalizedList(String filePath, String imageName, String spriteType) {

		ComponentView c = new ComponentView(this.getController());
		c.setWidth(50);
		
		Component personalizedComponent = new Component(spriteType(spriteType), imageName, filePath);
		c.setComponent(personalizedComponent
				);
		personalizedHBox.getChildren().add(c.getUI());
		
	}
	
	private GameObjectType spriteType(String input) {
		switch (input) {
		case "Hero":
			return GameObjectType.Hero;
		case "Enemy":
			return GameObjectType.Enemy;
		case "Block":
			return GameObjectType.StaticBlock;
		}
		return GameObjectType.Hero;
	}
	
	private File userChosenFile(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose sprite image");
		File file = fileChooser.showOpenDialog(new Stage());
		return file;
	}
	
	private String userChosenName(){
		TextInputDialog dialog = new TextInputDialog("image name");
		dialog.setTitle("Image Name");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter a name to save your image by");
		Optional<String> result = dialog.showAndWait();
		return result.isPresent() ? result.get() : "";
	}
	
	private String userChosenType(){
		List<String> spriteTypes = new ArrayList<>();
		spriteTypes.add("Hero");
		spriteTypes.add("Enemy");
		spriteTypes.add("Block");
		
		ChoiceDialog<String> typeChooser = new ChoiceDialog<>("Hero", spriteTypes);
		typeChooser.setTitle("Choose your type!");
		typeChooser.setHeaderText(null);
		typeChooser.setContentText("Please choose which type of character you image will be");
		
		Optional<String> result = typeChooser.showAndWait();
		return result.isPresent() ? result.get() : "Hero";
	}
	
	
	
	

}
