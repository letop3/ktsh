module com.letop3.ktsh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.prefs;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.logging;

    opens com.letop3.ktsh to javafx.fxml;
    exports com.letop3.ktsh;

    exports com.letop3.ktsh.controller to javafx.fxml;
    opens com.letop3.ktsh.controller to javafx.fxml;

    opens com.letop3.ktsh.model.files to com.fasterxml.jackson.databind;
}