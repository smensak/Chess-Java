package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.board.Field;

/**
 * @author  Samuel Mensak
 * @author  Rici Soltis
 */

public class MyNode extends StackPane {

    public Field field;
    @FXML
    public ImageView im = new ImageView();
    private String url = "";
    Image image;


    public MyNode(String name, double x, double y, double width, double height, Color color, Field field) {

        this.field = field;

        getUrl(field.toString());

        // create rectangle
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(color);

        // create label
        Label label = new Label("");

        if (!url.equals("")) {
            image = new Image(url);
            im = new ImageView();
            im.setImage(image);
            im.setFitWidth(50);
            im.setPreserveRatio(true);
            im.setSmooth(true);
            im.setCache(true);
        }

        getChildren().addAll(rectangle, label);
        if (!url.equals("")) {
            getChildren().add(im);
        }
    }

    private void getUrl(String type) {

        if (type.toLowerCase().contains("pesiak".toLowerCase())) {
            url = "file:lib/images/figures/";
            if (field.getFigure().isWhite()) {
                url += "WhitePawn.png";
            } else {
                url += "BlackPawn.png";
            }
        } else if (type.toLowerCase().contains("Veza".toLowerCase())) {
            url = "file:lib/images/figures/";
            if (field.getFigure().isWhite()) {
                url += "WhiteRook.png";
            } else {
                url += "BlackRook.png";
            }
        } else if (type.toLowerCase().contains("Strelec".toLowerCase())) {
            url = "file:lib/images/figures/";
            if (field.getFigure().isWhite()) {
                url += "WhiteBishop.png";
            } else {
                url += "BlackBishop.png";
            }
        } else if (type.toLowerCase().contains("Jazdec".toLowerCase())) {
            url = "file:lib/images/figures/";
            if (field.getFigure().isWhite()) {
                url += "WhiteKnight.png";
            } else {
                url += "BlackKnight.png";
            }
        } else if (type.toLowerCase().contains("Dama".toLowerCase())) {
            url = "file:lib/images/figures/";
            if (field.getFigure().isWhite()) {
                url += "WhiteQueen.png";
            } else {
                url += "BlackQueen.png";
            }
        } else if (type.toLowerCase().contains("Kral".toLowerCase())) {
            url = "file:lib/images/figures/";
            if (field.getFigure().isWhite()) {
                url += "WhiteKing.png";
            } else {
                url += "BlackKing.png";
            }
        }
    }

    public void clearIMG() {
        getChildren().remove(im);
    }
    @FXML
    public void changeImg() {

        getChildren().remove(im);

        url = "";
        getUrl(field.toString());

        if (!url.equals("")) {
            image = new Image(url);
            im = new ImageView();
            im.setImage(image);
            im.setFitWidth(50);
            im.setPreserveRatio(true);
            im.setSmooth(true);
            im.setCache(true);
        }

        if (!url.equals("")) {
            getChildren().add(im);
        } else {
            im.setImage(null);
            getChildren().add(im);
        }
    }
}