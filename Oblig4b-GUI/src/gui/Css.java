package gui;

import javafx.scene.layout.Pane;

public class Css {

    public static void setBackgroundGold(Pane... panes) {
        for(Pane pane : panes) {
            pane.setStyle("-fx-background-color: gold");
        }
    }

    public static void setBackgroundSilver(Pane... panes) {
        for(Pane pane: panes) {
            pane.setStyle("-fx-background-color: silver");
        }
    }
}
