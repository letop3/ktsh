package com.letop3.ktsh.controller;

import com.letop3.ktsh.view.LoadingView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label messageLabel;

    @FXML
    private Circle loadingCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadingView loadingView = new LoadingView(progressBar, messageLabel, loadingCircle);
        loadingView.startLoading();
    }


}
