package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import logger.MyLogger;
import members.*;

import members.BonusMember;
import members.MemberArchive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class Menu extends SceneView {

    private BorderPane mainLayout;
    private GridPane centerLayout;

    private TableView<BonusMember> tableMembers;

    private VBox dialogLayout = new VBox();

    private MemberArchive memberArchive;
    private ArrayList<BonusMember> members;

    private Stage dialogWindow;
    private Scene scene;

    public Menu() {
        memberArchive = new MemberArchive();
        members = memberArchive.getMembers();
        memberArchive.newMember(new Personals("William", "Forbrigd", "123@g.com"), LocalDate.now(), 50000);
        this.setLayout();
    }

    public Scene getScene() {
        return this.scene;
    }


    @Override
    public void setLayout()  {
        super.setLayout();

        mainLayout = super.getBorderPane();
        centerLayout = super.getGridPane();

        centerLayout.getChildren().add(createTableOfMembers());
        mainLayout.setCenter(centerLayout);

        this.createHeader();
        this.addAllButtons();

        scene = new Scene(mainLayout, 1000, 800);
    }

    private void createHeader() {
        Label header = new Label();
        header.setText("Member Archive");
        header.setFont(new Font("Verdana", 30));
        mainLayout.setTop(header);
        BorderPane.setAlignment(header, Pos.CENTER);
    }


    private TableView<BonusMember> createTableOfMembers() {
        tableMembers = new TableView<>();

        TableColumn<BonusMember, Integer> memberNoColumn = new TableColumn<>("Member Number");
        memberNoColumn.setMinWidth(200);
        memberNoColumn.setCellValueFactory(new PropertyValueFactory<>("memberNo"));

        TableColumn<BonusMember, String> personalsColumn = new TableColumn<>("Personals");
        personalsColumn.setMinWidth(200);
        personalsColumn.setCellValueFactory(new PropertyValueFactory<>("personals"));

        TableColumn<BonusMember, String> dateColumn = new TableColumn<>("Enrolled Date");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));

        TableColumn<BonusMember, Integer> pointsColumn = new TableColumn<>("Points");
        pointsColumn.setMinWidth(200);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableMembers.getItems().addAll(members);
        tableMembers.getColumns().addAll(memberNoColumn, personalsColumn, dateColumn, pointsColumn);

        return tableMembers;
    }

    private void addAllButtons() {
        Button addMember = new Button("Add Member");
        Button deleteMember = new Button("Delete member");
        Button checkMembers = new Button("Check members");
        Button showMemberDetails = new Button("Show info");

        addMember.setOnAction(e -> addMemberPressed());

        deleteMember.setOnAction(e -> {
            int memberNo = tableMembers.getSelectionModel().getSelectedItem().getMemberNo();
            BonusMember member = null;
            if(memberNo >= 1) {
                member = memberArchive.getMember(memberNo);
            }
            members.remove(member);
            updateMembers();
        });

        checkMembers.setOnAction(e -> {
            memberArchive.checkMembers3(LocalDate.now());
            updateMembers();
        });

        showMemberDetails.setOnAction(e -> showMemberDetailsPressed());

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(addMember, deleteMember, checkMembers, showMemberDetails);
        mainLayout.setBottom(hbox);
    }

    private void addMemberPressed() {
        this.createDialog();

        Label text = new Label("Add a basic member");
        text.setFont(new Font("Verdana", 20));

        TextField firstname = new TextField();
        firstname.setPromptText("Firstname");

        TextField surname = new TextField();
        surname.setPromptText("Surname");

        TextField email = new TextField();
        email.setPromptText("email");

        TextField password = new TextField();
        password.setPromptText("password");

        TextField points = new TextField();
        points.setPromptText("Points");

        Button createMember = new Button("Create");

        createMember.setOnAction(e -> {
            Personals personals = new Personals(firstname.getText(), surname.getText(), email.getText(), password.getText());
            int setPoinst = Integer.parseInt(points.getText());
            memberArchive.newMember(personals, LocalDate.now(), setPoinst);
            updateMembers();
            firstname.clear();
            surname.clear();
            email.clear();
            password.clear();
            points.clear();
            dialogWindow.close();
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.getChildren().addAll(firstname, surname, email, password, points);
        dialogLayout.getChildren().addAll(text, hBox, createMember);
    }

    private void showMemberDetailsPressed() {
        this.createDialog();
        BonusMember member = tableMembers.getSelectionModel().getSelectedItem();
        dialogWindow.setTitle("Details of a member");

        String info = "";

        /*
        if(member != null) {
            info = member.toString();
        } else {
            Text text = new Text("There are no members selected");
            dialogLayout.getChildren().clear();
            dialogLayout.getChildren().add(text);
        }

         */

        try {
            info = member.toString();
        } catch(NullPointerException e) {
            MyLogger.getLogger().log(Level.ALL, e.getMessage());
        }

        if(member instanceof GoldMember) {
            Css.setBackgroundGold(dialogLayout);
        } else if(member instanceof SilverMember) {
            Css.setBackgroundSilver(dialogLayout);
        }

        Text text = new Text();
        text.setText(info);

        dialogLayout.getChildren().add(text);
    }

    private void updateMembers() {
        tableMembers.getItems().clear();
        tableMembers.getItems().addAll(members);
    }

    private void createDialog() {
        dialogWindow = new Stage();
        dialogLayout = new VBox();
        dialogWindow.initModality(Modality.APPLICATION_MODAL);
        dialogLayout.setSpacing(10);
        dialogLayout.setPadding(new Insets(10,10,10,10));
        Scene dialogScene = new Scene(dialogLayout, 500, 150);
        dialogWindow.setScene(dialogScene);
        dialogWindow.show();
    }
}
