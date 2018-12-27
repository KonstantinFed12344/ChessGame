/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.List;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Konstantin
 */
public class Assign4 extends Application implements ListChangeListener {

    private ChessBoard board;
    private Button[] whiteTakenSquare;
    private Button[] blackTakenSquare;

    @Override
    public void start(Stage primaryStage) {

        GridPane brnch = new GridPane();
        Scene scene;
        Button square[][] = new Button[8][8];
        ImageView iv;
        int count = 0;
        board = new ChessBoard();
        SquareEventHandler eve = new SquareEventHandler(board);
        BorderPane outerbrnch = new BorderPane();
        Pane root1 = new VBox();
        Pane root2 = new VBox();

        whiteTakenSquare = new Button[16];
        blackTakenSquare = new Button[16];
        board.addTakenObserver(this);

        for (int i = 0; i <= 7; i++) {

            for (int j = 0; j <= 7; j++) {

                square[j][i] = new Button("" + (char) (97 + j) + (char) (56 - i));
                square[j][i].setMinSize(80, 80);
                if (board.getSquare(new Coordinate(j, i)).getPiece() != null) {

                    iv = new ImageView("/chess/images/" + board.getSquare(new Coordinate(j, 7 - i)).getPiece().getImageName());
                    square[j][i].setGraphic(iv);
                }
                if (count % 2 == 0) {
                    square[j][i].setStyle("-fx-background-color: grey;");
                    count++;
                } else {
                    square[j][i].setStyle("-fx-background-color: white;");
                    count++;
                }
                square[j][i].setOnAction(eve);
                brnch.add(square[j][i], j, i);

            }
            count++;
        }

        for (int i = 0; i < 16; i++) {
            whiteTakenSquare[i] = new Button();
            blackTakenSquare[i] = new Button();
            whiteTakenSquare[i].setMinSize(80, 40);
            blackTakenSquare[i].setMinSize(80, 40);
            whiteTakenSquare[i].setStyle("-fx-background-color: grey;");
            blackTakenSquare[i].setStyle("-fx-background-color: grey;");
            root1.getChildren().add(whiteTakenSquare[i]);
            root2.getChildren().add(blackTakenSquare[i]);
        }
        outerbrnch.setLeft(root1);
        outerbrnch.setCenter(brnch);
        outerbrnch.setRight(root2);

        scene = new Scene(outerbrnch);
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void onChanged(Change c) {
        while (c.next()) {
            if (c.wasAdded()) {
                List<Piece> pieces = c.getAddedSubList();

                for (Piece p : pieces) {
                    if (p == null) {
                        break;
                    }
                    for (int i = 0; i < 16; i++) {
                        if (p.getColour().equals(ChessColour.WHITE) && whiteTakenSquare[i].getGraphic() == null) {
                            whiteTakenSquare[i].setGraphic(new ImageView("/chess/images/" + p.getImageName()));
                            break;
                        } else if (p.getColour().equals(ChessColour.BLACK) && blackTakenSquare[i].getGraphic() == null) {
                            blackTakenSquare[i].setGraphic(new ImageView("/chess/images/" + p.getImageName()));
                            break;
                        }
                    }
                }
            }
        }
    }
}

class SquareEventHandler implements EventHandler<ActionEvent> {

    private ChessBoard board;
    private boolean firstclick;
    private Square firstSquare;
    private Button firstButton;
    private Button secondButton;

    public SquareEventHandler(ChessBoard board) {
        this.board = board;
        this.firstclick = true;
    }

    @Override
    public void handle(ActionEvent event) {

        Button btn = (Button) event.getSource();

        if (firstclick && board.getSquare(new Coordinate(btn.getText())).isOccupied() && board.getActiveColor() == board.getSquare(new Coordinate(btn.getText())).getPiece().getColour()) {
            firstclick = false;
            firstButton = (Button) event.getSource();

            firstSquare = board.getSquare(new Coordinate(firstButton.getText()));

        } else if (!firstclick) {

            firstclick = true;
            secondButton = (Button) event.getSource();
            if (board.move(new Coordinate(firstButton.getText()), new Coordinate(secondButton.getText()))) {
                firstButton.setGraphic(null);
                secondButton.setGraphic(new ImageView("/chess/images/" + board.getSquare(new Coordinate(secondButton.getText())).getPiece().getImageName()));
            } else {
                System.out.println("Invalid Move");
            }
        }
    }
}
