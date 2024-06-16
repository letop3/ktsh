package com.letop3.ktsh.controller;

import com.letop3.ktsh.model.utils.preferences.GamePreferences;
import com.letop3.ktsh.model.utils.preferences.prefs.AudioPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.GraphicsPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

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
        setFields();
    }

    public void setParentController(ParentControllerInterface parentController) {
        this.parentController = parentController;
    }

    public void setFields() {
        // Set the fields with the current values from the preferences
        moveUpField.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_UP.getKey(), KeyPreference.MOVE_UP.getDefaultValue()).getName());
        moveDownField.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_DOWN.getKey(), KeyPreference.MOVE_DOWN.getDefaultValue()).getName());
        moveRightField.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_RIGHT.getKey(), KeyPreference.MOVE_RIGHT.getDefaultValue()).getName());
        moveLeftField.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_LEFT.getKey(), KeyPreference.MOVE_LEFT.getDefaultValue()).getName());
        quickSlotField.setText(GamePreferences.getKeyCodePreference(KeyPreference.QUICK_SLOT.getKey(), KeyPreference.QUICK_SLOT.getDefaultValue()).getName());
        inventoryField.setText(GamePreferences.getKeyCodePreference(KeyPreference.INVENTORY.getKey(), KeyPreference.INVENTORY.getDefaultValue()).getName());
        interactField.setText(GamePreferences.getKeyCodePreference(KeyPreference.INTERACT.getKey(), KeyPreference.INTERACT.getDefaultValue()).getName());
        attackField.setText(GamePreferences.getKeyCodePreference(KeyPreference.ATTACK.getKey(), KeyPreference.ATTACK.getDefaultValue()).getName());
        shieldField.setText(GamePreferences.getKeyCodePreference(KeyPreference.SHIELD.getKey(), KeyPreference.SHIELD.getDefaultValue()).getName());
        masterVolumeField.setText(String.valueOf(GamePreferences.getFloatPreference(AudioPreference.MASTER_VOLUME.getKey(), (float) AudioPreference.MASTER_VOLUME.getDefaultValue())));
        musicVolumeField.setText(String.valueOf(GamePreferences.getFloatPreference(AudioPreference.MUSIC_VOLUME.getKey(), (float) AudioPreference.MUSIC_VOLUME.getDefaultValue())));
        effectVolumeField.setText(String.valueOf(GamePreferences.getFloatPreference(AudioPreference.EFFECTS_VOLUME.getKey(), (float) AudioPreference.EFFECTS_VOLUME.getDefaultValue())));
        fullScreenKeyField.setText(GamePreferences.getKeyCodePreference(GraphicsPreference.FULL_SCREEN_TOGGLE.getKey(), (KeyCode) GraphicsPreference.FULL_SCREEN_TOGGLE.getDefaultValue()).getName());
        checkFullScreenButton.setSelected(GamePreferences.getBooleanPreference(GraphicsPreference.START_FULL_SCREEN.getKey(), (boolean) GraphicsPreference.START_FULL_SCREEN.getDefaultValue()));
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
                break;
        }
    }

    @FXML
    private void handleCheckBoxChange(ActionEvent event) {
        CheckBox checkBox = (CheckBox) event.getSource();
        String checkBoxId = checkBox.getId();
        switch (checkBoxId) {
            case "checkFullScreenButton":
                handleToggleFullScreen(checkBox);
                break;
            default:
                break;
        }
    }

    @FXML
    private void handleResetSettings(ActionEvent event) {
        System.out.println("Settings reset");
        GamePreferences.resetDefaultPreferences();
        setFields();
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

    private void handleToggleFullScreen(CheckBox button) {
        // Logic to toggle fullscreen setting
        GamePreferences.setPreference(GraphicsPreference.START_FULL_SCREEN.getKey(), button.isSelected());

    }
}
