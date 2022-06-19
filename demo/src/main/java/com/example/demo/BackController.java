package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BackController {

    int firstTeamPoints = 0, secondTeamPoints = 0;

    int chosenQuestion;

    @FXML
    Text firstTeamPointsText;
    @FXML
    Text secondTeamPointsText;
    boolean[] showedAnswers;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    AnchorPane mainPane;
    private boolean areQuestionsApplied = false;
    Question[] questions = new Question[2];
    Text[] texts;
    Button[] showButtons;
    public void loadQuestions(){
        questions[0] = new Question();
        questions[1] = new Question();
        questions[0].setQuestion("Popularne imię dla psa");
        questions[0].setNumberOfAnswers(4);
        questions[0].setAnswer(0,"Azor");
        questions[0].setAnswer(1, "Burek");
        questions[0].setAnswer(2, "Tofik");
        questions[0].setAnswer(3, "Maks");
        questions[0].setPoint(0, 34);
        questions[0].setPoint(1,26);
        questions[0].setPoint(2,19);
        questions[0].setPoint(3,13);

        questions[1].setQuestion("Popularne imię dla kota");
        questions[1].setNumberOfAnswers(5);
        questions[1].setAnswer(0,"Rudy");
        questions[1].setAnswer(1, "Bury");
        questions[1].setAnswer(2, "Freja");
        questions[1].setAnswer(3, "Adolf");
        questions[1].setAnswer(4, "Eryl");
        questions[1].setPoint(0, 34);
        questions[1].setPoint(1,26);
        questions[1].setPoint(2,19);
        questions[1].setPoint(3,13);
        questions[1].setPoint(4,3);
        choiceBox.getItems().add(questions[0].getQuestion());
        choiceBox.getItems().add(questions[1].getQuestion());
    }
    public void applyQuestion(){
        if(areQuestionsApplied) {
            for (Text x : texts) {
                x.setText("");
            }
            for (Button x: showButtons){
                x.setDisable(true);
                x.setVisible(false);
            }
            for(Boolean x: showedAnswers){
                x = false;
            }
        }
        for(int i=0; i<2; i++){
            if(choiceBox.getValue().equals(questions[i].getQuestion()))
                chosenQuestion = i;
        }
        showedAnswers = new boolean[questions[chosenQuestion].getNumberOfAnswers()];
        texts = new Text[questions[chosenQuestion].getNumberOfAnswers()];
        showButtons = new Button[questions[chosenQuestion].getNumberOfAnswers()];
        for(int j=0; j<questions[chosenQuestion].getNumberOfAnswers(); j++){
            texts[j] = new Text();
            texts[j].setText(questions[chosenQuestion].getAnswer(j));
            texts[j].setX(300);
            showButtons[j] = new Button();
            showButtons[j].setDisable(false);
            showButtons[j].setText("Show");
            showButtons[j].setLayoutX(433);
            showButtons[j].setOnMouseClicked(onClickedShowButton());
            if(j != 0) {
                texts[j].setY(texts[j - 1].getY() + 30);
                showButtons[j].setLayoutY(showButtons[j-1].getLayoutY() + 30);
            }
            else {
                texts[j].setY(65);
                showButtons[j].setLayoutY(48);
            }
            mainPane.getChildren().addAll(texts[j],showButtons[j]);
        }
        areQuestionsApplied = true;
    }

    EventHandler<MouseEvent> onClickedShowButton(){
        return mouseEvent -> {
            int numberOfButton = 0;
            if(mouseEvent.getSceneY() < 344) {
                numberOfButton = 9;
            }
            if(mouseEvent.getSceneY() < 314) {
                numberOfButton = 8;
            }
            if(mouseEvent.getSceneY() < 284){
                numberOfButton = 7;
            }
            if(mouseEvent.getSceneY() < 254) {
                numberOfButton = 6;
            }
            if(mouseEvent.getSceneY() < 224) {
                numberOfButton = 5;
            }
            if(mouseEvent.getSceneY() < 194) {
                numberOfButton = 4;
            }
            if(mouseEvent.getSceneY() < 164) {
                numberOfButton = 3;
            }
            if(mouseEvent.getSceneY() < 134) {
                numberOfButton = 2;
            }
            if(mouseEvent.getSceneY() < 104) {
                numberOfButton = 1;
            }
            if(mouseEvent.getSceneY() < 74) {
                numberOfButton = 0;
            }
            showedAnswers[numberOfButton] = true;
            System.out.println("Numer przycisku: " + numberOfButton + showedAnswers[numberOfButton]);
        };
    }

    public void addPoints(int team, int multiplier){
        int amount = 0;
        for(int i=0; i<questions[chosenQuestion].getNumberOfAnswers(); i++){
            if(showedAnswers[i])
                amount += questions[chosenQuestion].getPoint(i);
        }
        if(team == 1){
            firstTeamPoints += (amount * multiplier);
            firstTeamPointsText.setText(String.valueOf(firstTeamPoints));
        }
        if(team == 2){
            secondTeamPoints += (amount * multiplier);
            secondTeamPointsText.setText(String.valueOf(secondTeamPoints));
        }
    }

    public void addPointsX1secondTeam(){
        addPoints(2,1);
    }
    public void addPointsX2secondTeam(){
        addPoints(2,2);
    }
    public void addPointsX3secondTeam(){
        addPoints(2,3);
    }
    public void addPointsX1firstTeam(){
        addPoints(1,1);
    }
    public void addPointsX2firstTeam(){
        addPoints(1,2);
    }
    public void addPointsX3firstTeam(){
        addPoints(1,3);
    }
}
