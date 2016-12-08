package authoring.settings;

import authoring.AuthoringController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Provides a button that displays the current image for some setting, and allows you to change it when it is clicked
 * on.
 *
 * @author Will Long
 */
public class ImageChangeButton extends AbstractSettingsView {
    private static final String LABEL_TEXT = "Change Image";
    private static final double WIDTH_RATIO = 0.25;

    private String myImageFilename;
    private ITextChangeHandler myHandler;

    private Button myButton;
    private ImageView myImageView;

    public ImageChangeButton(AuthoringController controller, String defaultFilename, ITextChangeHandler handler) {
        super(controller);
        myImageFilename = defaultFilename;
        myHandler = handler;
    }

    @Override
    public void initializeSettings() {
        try {
            myImageView.setImage(new Image(myImageFilename));
        } catch (RuntimeException e) {
            myImageView.setImage(null);
        }
        myButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose New Image");
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                myImageFilename = file.toURI().toString();
                myImageView.setImage(new Image(myImageFilename));
                myHandler.handle(myImageFilename);
            }
        });
    }

    @Override
    protected void initUI() {
        super.initUI();
        myLabel.setText(LABEL_TEXT);
        myButton = new Button();
        myImageView = new ImageView();
        myButton.setGraphic(myImageView);
        myContent.getChildren().add(myButton);
    }

    @Override
    protected void updateLayoutSelf() {
        myImageView.setFitWidth(getWidth() * WIDTH_RATIO);
        myImageView.setFitHeight(getWidth() * WIDTH_RATIO);
    }
}
