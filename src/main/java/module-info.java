module com.letop3.ktsh {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.prefs;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires java.logging;
    requires javafx.base;
    requires javafx.media;

    opens com.letop3.ktsh to javafx.fxml;
    exports com.letop3.ktsh;

    exports com.letop3.ktsh.controller to javafx.fxml;
    opens com.letop3.ktsh.controller;

    opens com.letop3.ktsh.model.files to com.fasterxml.jackson.databind;

    exports com.letop3.ktsh.model;
    exports com.letop3.ktsh.model.ground;
    exports com.letop3.ktsh.model.entity;
    exports com.letop3.ktsh.model.entity.player;
    exports com.letop3.ktsh.model.entity.ennemies;
    exports com.letop3.ktsh.model.item;
    exports com.letop3.ktsh.model.item.artefact;
    exports com.letop3.ktsh.model.item.arme;
    exports com.letop3.ktsh.model.item.consomable;
}
