package com.letop3.ktsh.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private TextField moveUpField;
    @FXML
    private TextField moveDownField;
    @FXML
    private TextField moveRightField;
    @FXML
    private TextField moveLeftField;
    @FXML
    private TextField quickSlotField;
    @FXML
    private TextField inventoryField;
    @FXML
    private TextField interactField;
    @FXML
    private TextField attackField;
    @FXML
    private TextField shieldField;
    @FXML
    private TextField masterVolumeField;
    @FXML
    private TextField musicVolumeField;
    @FXML
    private TextField effectVolumeField;
    @FXML
    private TextField fullScreenKeyField;
    @FXML
    private CheckBox checkFullScreenButton;

    private ParentControllerInterface parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any necessary data or state here
    }

    public void setParentController(ParentControllerInterface parentController) {
        this.parentController = parentController;
    }


    @FXML
    private void handleChangeAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
        switch (buttonId) {
            case "changeMoveUpButton":
                handleFieldChange(moveUpField);
                break;
            case "changeMoveDownButton":
                handleFieldChange(moveDownField);
                break;
            case "changeMoveRightButton":
                handleFieldChange(moveRightField);
                break;
            case "changeMoveLeftButton":
                handleFieldChange(moveLeftField);
                break;
            case "changeQuickSlotButton":
                handleFieldChange(quickSlotField);
                break;
            case "changeInventoryButton":
                handleFieldChange(inventoryField);
                break;
            case "changeInteractButton":
                handleFieldChange(interactField);
                break;
            case "changeAttackButton":
                handleFieldChange(attackField);
                break;
            case "changeShieldButton":
                handleFieldChange(shieldField);
                break;
            case "changeMasterVolumeButton":
                handleFieldChange(masterVolumeField);
                break;
            case "changeMusicVolumeButton":
                handleFieldChange(musicVolumeField);
                break;
            case "changeEffectVolumeButton":
                handleFieldChange(effectVolumeField);
                break;
            case "changeFullScreenKeyButton":
                handleFieldChange(fullScreenKeyField);
                break;
            default:
                // Handle any other cases
                break;
        }
    }

    @FXML
    private void handleToggleAction(ActionEvent event) {
        // Handle toggle button actions here
        // You can access the button using event.getSource() and cast it to ToggleButton if needed
        ToggleButton button = (ToggleButton) event.getSource();
        if (button.getId().equals("toggleFullScreenButton")) {
            handleToggleFullScreen(button);
        }
    }

    @FXML
    private void handleResetSettings(ActionEvent event) {
        System.out.println("Settings reset");
    }

    @FXML
    private void handleClose(ActionEvent event) {
        parentController.changeChild();

    }

    private void handleFieldChange(TextField field) {
        // Logic to change the field value, e.g., show a dialog to input new value
        //field.setText("New Value"); // Placeholder logic
        System.out.println("Field changed: " + field.getId());
    }

    private void handleToggleFullScreen(ToggleButton button) {
        // Logic to toggle fullscreen setting
        boolean isFullScreen = button.isSelected();
        System.out.println("Fullscreen is " + (isFullScreen ? "enabled" : "disabled"));
    }
}
