package com.nlpc.nlpsentenceclassifier;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import opennlp.tools.doccat.DoccatModel;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label resultLabel;
    @FXML
    private Button calculateButton;
    @FXML
    private TextField sentenceToAnalyse;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(){
        resultLabel.setVisible(false);


        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DoccatModel model = NaiveBayesClassifier.trainModel();

                String[] test = sentenceToAnalyse.getText().split(" ");
                if ( NaiveBayesClassifier.classify(test, model) == 4 ){
                    resultLabel.setText("The text is positive");
                    resultLabel.setVisible(true);
                }
                else {
                    resultLabel.setText("The text is negative");

                }
            }
        });

    }
}