package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import java.io.*;

/**
 * @author  Samuel Mensak
 * @author  Rici Soltis
 */

public class MyTab extends Tab {

    private Tab tab;

    public MyTab(int counter) {

        tab = new Tab("Game_" + (counter + 1));

        Label label = new Label("This will be chessboard");
        Label label3 = new Label("Move No.              White                 Black");
        label3.setPadding(new Insets(5,0,0,0));
        label.setMinWidth(620);
        label.setMinHeight(600);
        SplitPane splitPane = new SplitPane();
        SplitPane splitPane2 = new SplitPane();
        splitPane2.setOrientation(Orientation.VERTICAL);
        splitPane.setMinWidth(620);
        splitPane2.setMaxWidth(261);

        ChessBoard tmp = new ChessBoard();
        splitPane.getItems().add(tmp.getChessBoard());

        Button button = new Button();
        button.setText("<<");
        EventHandler<ActionEvent> event = e -> {
            tmp.moveBackwards();
            tmp.update();
        };
        button.setOnAction(event);

        Button button2 = new Button();
        button2.setText(">>");
        EventHandler<ActionEvent> event2 = e -> {
            tmp.moveForward();
            tmp.update();
        };
        button2.setOnAction(event2);

        Button button3 = new Button();
        button3.setText("play");
        Button button4 = new Button();
        button4.setText("pause");
        Button button5 = new Button();
        button5.setText("load");
        Button button6 = new Button();
        button6.setText("save");

        EventHandler<ActionEvent> eventSave =  e ->  {
            tmp.saveToFile(Integer.toString(counter + 1));
        };
        button6.setOnAction(eventSave);

        EventHandler<ActionEvent> event3 =  e ->  {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Game File");
                File file = fileChooser.showOpenDialog(null);
                tmp.loadGame(file); // if false popup
                tmp.update();
        };
        button5.setOnAction(event3);

        HBox bb = new HBox(button, button2, button3, button4, button5, button6);
        bb.setMaxHeight(27);

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(10);
        slider.setValue(2);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(1);

        TextField textfield = new TextField();
        textfield.minWidth(250);
        textfield.minHeight(30);
        Button buttonX = new Button();
        buttonX.setText("go to");

        EventHandler<ActionEvent> eventGoTo =  e ->  {
            tmp.goTo(textfield.getText());
            tmp.update();
        };
        buttonX.setOnAction(eventGoTo);

        HBox hbox = new HBox(textfield, buttonX);

        splitPane2.getItems().addAll(bb, slider, label3, hbox);

        tab.setContent(splitPane);
        splitPane.getItems().add(splitPane2);
    }


    public Tab getTab() {
        return this.tab;
    }
}
