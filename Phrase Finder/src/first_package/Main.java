package first_package;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application{
    static int HEIGTH = 600;
    static int WIDTH = 800;
    static Stage pStage;
    static Scene scene;
    static VBox root;
    static TextArea searchedPhrase;
    static Button chooseNewFileButton;
    static BorderPane resultArea;
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
        label.setPrefSize(WIDTH+10,40);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color:  #c39bd3");
        return  label;
    }
    private HBox getNavigationBar() {
        HBox hBox = new HBox();
        hBox.setPrefSize(WIDTH+10,120);
        hBox.setStyle("-fx-background-color:  lightblue ");
        chooseNewFileButton = getNewSourceFileButton();
        searchedPhrase = getSearchedPhrase();
        hBox.setPadding(new Insets(20));
        hBox.getChildren().addAll(chooseNewFileButton,getSearchedPhraseBox());
        return hBox;
    }
    private Button getNewSourceFileButton(){
        Button button = new Button("Wybierz nowy plik\n do przeszukania");
        button.setPrefSize((WIDTH+10)/2-20,100);
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
        textArea.setPrefSize((WIDTH+10)/2-20,80);
        return textArea;
    }
    private VBox getSearchedPhraseBox(){
        VBox vBox = new VBox();
        vBox.setPrefSize((WIDTH+10)/2-20,100);
        vBox.getChildren().addAll(getPhraseLabel(),searchedPhrase);
        return vBox;
    }
    private Label getPhraseLabel(){
        Label label = new Label("Tu wpisz szukaną frazę");
        label.setStyle("-fx-background-color: #884ea0");
        label.setAlignment(Pos.CENTER);
        label.setPrefSize((WIDTH+10)/2-20,20);
        return label;
    }
    private BorderPane getResultArea() {
        resultTextArea = new TextArea();
        resultTextArea.setPrefSize(WIDTH-40,420);
        resultTextArea.setStyle("-fx-background-color: lightblue");
        BorderPane borderPane = new BorderPane(resultTextArea);
        borderPane.setPadding(new Insets(20));
        borderPane.setStyle("-fx-background-color: lightblue");
        borderPane.setPrefSize(WIDTH, 460);
        return borderPane;
    }
}