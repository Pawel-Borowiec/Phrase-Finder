package first_package;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application{
    static int HEIGTH = 600;
    static int WIDTH = 800;
    static Stage pStage;
    static Scene scene;
    static VBox root;
    static TextArea searchedPhrase;
    static Button chooseNewFileButton;
    static ScrollPane resultArea;
    static TextArea resultTextArea;

    public static void main(String[] args){
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        pStage=primaryStage;
        pStage.setTitle("Phrase Finder");
        pStage.setResizable(false);
        root = new VBox();
        root.setStyle("-fx-background-color: #e6eff4");
        resultArea = getResultArea();
        root.getChildren().addAll(getTitle(), getNavigationBar(),resultArea);
        scene = new Scene(root,WIDTH,HEIGTH);
        pStage.setScene(scene);
        pStage.show();
    }
    private Label getTitle() {
        Label label = new Label("Phrase Finder");
        label.setPrefSize(WIDTH,40);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color:  #c39bd3");
        return  label;
    }
    private HBox getNavigationBar() {
        HBox hBox = new HBox();
        hBox.setPrefSize(WIDTH,120);
        hBox.setStyle("-fx-background-color:  #82e0aa ");
        chooseNewFileButton = getNewSourceFileButton();
        searchedPhrase = getSearchedPhrase();
        hBox.getChildren().addAll(chooseNewFileButton,searchedPhrase);
        return hBox;
    }
    private Button getNewSourceFileButton(){
        Button button = new Button("Wybierz nowy plik\n do przeszukania");
        button.setPrefSize(WIDTH*0.5,120);
        button.setStyle("-fx-background-color:  #884ea0 ");
        button.setOnAction(e->{
            try {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(pStage);
                System.out.println(selectedDirectory.toPath().toString());
                resultTextArea.setText("");
                Futil.processDir(selectedDirectory.toPath().toString());
                System.out.println("Zakonczono zadanie");
            }catch (NullPointerException a) {
                a.printStackTrace();
            }
        });
        return button;
    }
    private TextArea getSearchedPhrase() {
        TextArea textArea = new TextArea();
        textArea.setPrefSize(WIDTH*0.5,440);
        return textArea;
    }
    private ScrollPane getResultArea() {
        resultTextArea = new TextArea();
        resultTextArea.setText("XDDDDDDDDDDDDDDDDddd");
        resultTextArea.setPrefSize(WIDTH,440);
        ScrollPane scrollPane = new ScrollPane(resultTextArea);
        scrollPane.setPrefSize(WIDTH, 440);
        return scrollPane;
    }
}