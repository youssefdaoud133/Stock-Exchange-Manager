module oop.stockexchangemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    opens oop.stockexchangemanager.AccountPackage to javafx.base;
    opens oop.stockexchangemanager to javafx.fxml;
    opens oop.stockexchangemanager.StockPackage to javafx.base;

    exports oop.stockexchangemanager;
}