package com.example.ljavafxdemo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label display;

    private boolean typingANumber = false;

    private CalculatorModel calculatorModel = new CalculatorModel();
    @FXML
    void onNumericButtonPressed(ActionEvent event) {
        var button = (Button) event.getTarget();

        String displayText = display.getText();
        String buttonText = button.getText();
        if (typingANumber) {
            if (buttonText.equals(",") && displayText.contains(",")) {
                buttonText = "";
            }
            displayText += buttonText;
        } else {
            typingANumber = true;
            if (buttonText.equals(",")) {
                buttonText = "0,";
            }
            displayText = buttonText;
        }
        display.setText(displayText);
    }

    @FXML
    void onOperatorButtonPressed(ActionEvent event) {
        var button = (Button) event.getTarget();

        String buttonText = button.getText();
        String displayText = display.getText();

        // GUARD: Überspringt ganze Methode
        if (!typingANumber) return;

        if (button.getText().equals("=")) {

            calculatorModel.setOperand2(
                    Double.parseDouble(displayText.replace(",", "."))
            );

            try {
                double result = calculatorModel.calculate();
                display.setText(String.valueOf(result).replace(".", ","));


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {

            calculatorModel.setOperand1(
                    Double.parseDouble(displayText.replace(",", "."))
            );

            calculatorModel.setOperator(buttonText);
            display.setText(button.getText());

        }

        typingANumber = false;
    }

    @FXML
    void initialize() {
        assert display != null : "fx:id=\"display\" was not injected: check your FXML file 'calculator-view.fxml'.";

    }

    @FXML
    private void onClearButtonPressed(ActionEvent actionEvent) {
        display.setText("0,0");
        calculatorModel.setOperand1(0.0);
        calculatorModel.setOperator("");
        calculatorModel.setOperand2(0.0);
        typingANumber = false;
    }
}
