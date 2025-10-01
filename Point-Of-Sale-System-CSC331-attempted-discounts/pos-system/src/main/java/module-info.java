module com.pos.system {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;

    opens com.pos.system to javafx.fxml;
    exports com.pos.system;
}
