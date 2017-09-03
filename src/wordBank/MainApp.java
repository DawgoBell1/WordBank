package wordBank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import wordBank.controller.MainMenuController;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private static final String ROOT_LAYOUT_FXML = "view/RootLayout.fxml";
    private static final String MAIN_MENU = "view/MainMenu.fxml";

    public MainApp(){}

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("WordBank");

        initializeRootLayout();
        showOverview();
    }

    private void initializeRootLayout() {
        FXMLLoader loader = createFXMLLoader(ROOT_LAYOUT_FXML);
        setWithRootLayout(loader);
    }

    private FXMLLoader createFXMLLoader(String dialogFileLocation) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource(dialogFileLocation));
        return fxmlLoader;
    }

    private Stage createDialogStage(String stageTitle) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle(stageTitle);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        return dialogStage;
    }

    private void setWithRootLayout(FXMLLoader loader) {
        try {
            setSceneWithRootLayout(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSceneWithRootLayout(FXMLLoader loader) throws IOException {
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showOverview() {
        try {
            showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMainMenu() throws IOException {
        FXMLLoader loader = setMainMenu();
        MainMenuController controller = loader.getController();
        controller.setMainApp(this);

    }

    private FXMLLoader setMainMenu() throws IOException {
        FXMLLoader loader = createFXMLLoader(MAIN_MENU);
        AnchorPane mainMenu = loader.load();
        rootLayout.setCenter(mainMenu);
        return loader;
    }

    private Stage setUpStage(String title, FXMLLoader fxmlLoader) {
        Stage dialogStage = createDialogStage(title);
        setWithAnchorPane(dialogStage, fxmlLoader);
        return dialogStage;
    }

    private void setWithAnchorPane(Stage dialogStage, FXMLLoader loader) {
        try {
            setSceneWithAnchorPane(dialogStage, loader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setSceneWithAnchorPane(Stage dialogStage, FXMLLoader fxmlLoader) throws IOException {
        AnchorPane loadedFXML = fxmlLoader.load();
        Scene scene = new Scene(loadedFXML);
        dialogStage.setScene(scene);
    }

}
