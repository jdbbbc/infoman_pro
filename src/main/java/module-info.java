module com.project.infoman_pro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens com.project.infoman_pro.controller to javafx.fxml, java.base;
    exports com.project.infoman_pro.controller;
    opens com.project.infoman_pro to javafx.fxml;
    exports com.project.infoman_pro;
}