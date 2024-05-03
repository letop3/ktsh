module com.letop3.ktsh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.letop3.ktsh to javafx.fxml;
    exports com.letop3.ktsh;
}