package com.letop3.ktsh.controller;

import com.letop3.ktsh.Main;
import com.letop3.ktsh.model.utils.preferences.GamePreferences;
import com.letop3.ktsh.model.utils.preferences.prefs.AudioPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.GraphicsPreference;
import com.letop3.ktsh.model.utils.preferences.prefs.KeyPreference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private TextField moveUp;
    @FXML
    private TextField moveDown;
    @FXML
    private TextField moveRight;
    @FXML
    private TextField moveLeft;
    @FXML
    private TextField quickSlot;
    @FXML
    private TextField inventory;
    @FXML
    private TextField interact;
    @FXML
    private TextField attack;
    @FXML
    private TextField shield;
    @FXML
    private TextField masterVolume;
    @FXML
    private TextField musicVolume;
    @FXML
    private TextField effectsVolume;
    @FXML
    private TextField fullScreenToggle;
    @FXML
    private CheckBox startFullScreen;
    @FXML
    private Slider masterVolumeSlider;
    @FXML
    private Slider musicVolumeSlider;
    @FXML
    private Slider effectsVolumeSlider;
    @FXML
    private VBox keyChangePopup;

    private ParentControllerInterface parentController;
    private String currentKeyPreference;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFields();

        masterVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> handleSliderChange(masterVolumeSlider, newValue));
        musicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> handleSliderChange(musicVolumeSlider, newValue));
        effectsVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> handleSliderChange(effectsVolumeSlider, newValue));

        // Add a global key listener to handle key change
        keyChangePopup.setOnKeyPressed(this::handleKeyPress);
    }

    public void setParentController(ParentControllerInterface parentController) {
        this.parentController = parentController;
    }

    public void setFields() {
        // Set the fields with the current values from the preferences
        moveUp.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_UP.getKey(), KeyPreference.MOVE_UP.getDefaultValue()).getName());
        moveDown.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_DOWN.getKey(), KeyPreference.MOVE_DOWN.getDefaultValue()).getName());
        moveRight.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_RIGHT.getKey(), KeyPreference.MOVE_RIGHT.getDefaultValue()).getName());
        moveLeft.setText(GamePreferences.getKeyCodePreference(KeyPreference.MOVE_LEFT.getKey(), KeyPreference.MOVE_LEFT.getDefaultValue()).getName());
        quickSlot.setText(GamePreferences.getKeyCodePreference(KeyPreference.QUICK_SLOT.getKey(), KeyPreference.QUICK_SLOT.getDefaultValue()).getName());
        inventory.setText(GamePreferences.getKeyCodePreference(KeyPreference.INVENTORY.getKey(), KeyPreference.INVENTORY.getDefaultValue()).getName());
        interact.setText(GamePreferences.getKeyCodePreference(KeyPreference.INTERACT.getKey(), KeyPreference.INTERACT.getDefaultValue()).getName());
        attack.setText(GamePreferences.getKeyCodePreference(KeyPreference.ATTACK.getKey(), KeyPreference.ATTACK.getDefaultValue()).getName());
        shield.setText(GamePreferences.getKeyCodePreference(KeyPreference.SHIELD.getKey(), KeyPreference.SHIELD.getDefaultValue()).getName());
        masterVolume.setText(String.valueOf(GamePreferences.getFloatPreference(AudioPreference.MASTER_VOLUME.getKey(), (float) AudioPreference.MASTER_VOLUME.getDefaultValue())));
        musicVolume.setText(String.valueOf(GamePreferences.getFloatPreference(AudioPreference.MUSIC_VOLUME.getKey(), (float) AudioPreference.MUSIC_VOLUME.getDefaultValue())));
        effectsVolume.setText(String.valueOf(GamePreferences.getFloatPreference(AudioPreference.EFFECTS_VOLUME.getKey(), (float) AudioPreference.EFFECTS_VOLUME.getDefaultValue())));
        fullScreenToggle.setText(GamePreferences.getKeyCodePreference(GraphicsPreference.FULL_SCREEN_TOGGLE.getKey(), (KeyCode) GraphicsPreference.FULL_SCREEN_TOGGLE.getDefaultValue()).getName());
        startFullScreen.setSelected(GamePreferences.getBooleanPreference(GraphicsPreference.START_FULL_SCREEN.getKey(), (boolean) GraphicsPreference.START_FULL_SCREEN.getDefaultValue()));

        masterVolumeSlider.setValue((float) AudioPreference.MASTER_VOLUME.getDefaultValue());
        musicVolumeSlider.setValue((float) AudioPreference.MUSIC_VOLUME.getDefaultValue());
        effectsVolumeSlider.setValue((float) AudioPreference.EFFECTS_VOLUME.getDefaultValue());
    }

    @FXML
    private void handleChangeAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String buttonId = button.getId();
        switch (buttonId) {
            case "changeMoveUp":
                showKeyChangePopup(KeyPreference.MOVE_UP.getKey());
                break;
            case "changeMoveDown":
                showKeyChangePopup(KeyPreference.MOVE_DOWN.getKey());
                break;
            case "changeMoveRight":
                showKeyChangePopup(KeyPreference.MOVE_RIGHT.getKey());
                break;
            case "changeMoveLeft":
                showKeyChangePopup(KeyPreference.MOVE_LEFT.getKey());
                break;
            case "changeQuickSlot":
                showKeyChangePopup(KeyPreference.QUICK_SLOT.getKey());
                break;
            case "changeInventory":
                showKeyChangePopup(KeyPreference.INVENTORY.getKey());
                break;
            case "changeInteract":
                showKeyChangePopup(KeyPreference.INTERACT.getKey());
                break;
            case "changeAttack":
                showKeyChangePopup(KeyPreference.ATTACK.getKey());
                break;
            case "changeShield":
                showKeyChangePopup(KeyPreference.SHIELD.getKey());
                break;
            case "changeMasterVolume":
                handleSliderChange(masterVolumeSlider, masterVolumeSlider.getValue());
                break;
            case "changeMusicVolume":
                handleSliderChange(musicVolumeSlider, musicVolumeSlider.getValue());
                break;
            case "changeEffectsVolume":
                handleSliderChange(effectsVolumeSlider, effectsVolumeSlider.getValue());
                break;
            case "changeFullScreenToggle":
                showKeyChangePopup(GraphicsPreference.FULL_SCREEN_TOGGLE.getKey());
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
            case "startFullScreen":
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
        System.out.println("Field changed: " + field.getId());
    }

    private void handleSliderChange(Slider slider, Number newValue) {
        if (slider.equals(masterVolumeSlider)) {
            GamePreferences.setPreference(AudioPreference.MASTER_VOLUME.getKey(), newValue.floatValue());
            masterVolume.setText(String.valueOf(newValue.floatValue()));
        } else if (slider.equals(musicVolumeSlider)) {
            GamePreferences.setPreference(AudioPreference.MUSIC_VOLUME.getKey(), newValue.floatValue());
            musicVolume.setText(String.valueOf(newValue.floatValue()));
        } else if (slider.equals(effectsVolumeSlider)) {
            GamePreferences.setPreference(AudioPreference.EFFECTS_VOLUME.getKey(), newValue.floatValue());
            effectsVolume.setText(String.valueOf(newValue.floatValue()));
        }
    }

    private void handleToggleFullScreen(CheckBox button) {
        // Logic to toggle fullscreen setting
        GamePreferences.setPreference(GraphicsPreference.START_FULL_SCREEN.getKey(), button.isSelected());
    }

    private void showKeyChangePopup(String keyPreference) {
        currentKeyPreference = keyPreference;
        keyChangePopup.setVisible(true);
        keyChangePopup.requestFocus();  // Ensure the popup has focus to capture key events
    }

    private void handleKeyPress(KeyEvent event) {
        if (currentKeyPreference != null) {
            updateKeyPreference(currentKeyPreference, event.getCode());
            keyChangePopup.setVisible(false);
            currentKeyPreference = null;
        }
    }

    public void updateKeyPreference(String keyPreference, KeyCode newKey) {
        switch (keyPreference) {
            case "moveUp":
                GamePreferences.setPreference(KeyPreference.MOVE_UP.getKey(), newKey.getName());
                moveUp.setText(newKey.getName());
                break;
            case "moveDown":
                GamePreferences.setPreference(KeyPreference.MOVE_DOWN.getKey(), newKey.getName());
                moveDown.setText(newKey.getName());
                break;
            case "moveRight":
                GamePreferences.setPreference(KeyPreference.MOVE_RIGHT.getKey(), newKey.getName());
                moveRight.setText(newKey.getName());
                break;
            case "moveLeft":
                GamePreferences.setPreference(KeyPreference.MOVE_LEFT.getKey(), newKey.getName());
                moveLeft.setText(newKey.getName());
                break;
            case "quickSlot":
                GamePreferences.setPreference(KeyPreference.QUICK_SLOT.getKey(), newKey.getName());
                quickSlot.setText(newKey.getName());
                break;
            case "inventory":
                GamePreferences.setPreference(KeyPreference.INVENTORY.getKey(), newKey.getName());
                inventory.setText(newKey.getName());
                break;
            case "interact":
                GamePreferences.setPreference(KeyPreference.INTERACT.getKey(), newKey.getName());
                interact.setText(newKey.getName());
                break;
            case "attack":
                GamePreferences.setPreference(KeyPreference.ATTACK.getKey(), newKey.getName());
                attack.setText(newKey.getName());
                break;
            case "shield":
                GamePreferences.setPreference(KeyPreference.SHIELD.getKey(), newKey.getName());
                shield.setText(newKey.getName());
                break;
            case "fullScreenToggle":
                GamePreferences.setPreference(GraphicsPreference.FULL_SCREEN_TOGGLE.getKey(), newKey.getName());
                fullScreenToggle.setText(newKey.getName());
                Main.notifyKeyChange(); // Notify the Main class of the key change
                break;
            default:
                break;
        }
    }
}
