import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import view.MyTab;

/**
 * @author  Samuel Mensak
 * @author  Rici Soltis
 */

public class Main extends Application {

    private double sceneWidth = 885;
    private double sceneHeight = 600;

    private int counter = 0;

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("\"Professional\" Chess Simulator");

        // create a tabpane
        TabPane tabpane = new TabPane();

        // create a tab which
        // when pressed creates a new tab
        Tab newtab = new Tab();
        newtab.setText("+");

        // action event
        EventHandler<Event> event =
                new EventHandler<Event>() {

                    public void handle(Event e) {
                        if (newtab.isSelected()) {

                            // create Tab
                            // TODO
                            MyTab tab = new MyTab(counter++);
                            // add tab
                            tabpane.getTabs().add(
                                    tabpane.getTabs().size() - 1, tab.getTab());

                            // select the last tab
                            tabpane.getSelectionModel().select(
                                    tabpane.getTabs().size() - 2);
                        }
                    }
                };

        // set event handler to the tab
        newtab.setOnSelectionChanged(event);

        // add newtab
        tabpane.getTabs().add(newtab);

        Scene scene = new Scene(tabpane, sceneWidth, sceneHeight);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}