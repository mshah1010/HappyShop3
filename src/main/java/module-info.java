module ci553.happyshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;

    opens ci553.happyshop to javafx.fxml;
    opens ci553.happyshop.client to javafx.fxml;
    opens ci553.happyshop.client.customer to javafx.fxml;
    opens ci553.happyshop.client.picker to javafx.fxml;
    opens ci553.happyshop.client.orderTracker to javafx.fxml;
    opens ci553.happyshop.client.warehouse to javafx.fxml;
    opens ci553.happyshop.client.emergency to javafx.fxml;

    exports ci553.happyshop;
    exports ci553.happyshop.client;
    exports ci553.happyshop.utility;
    exports ci553.happyshop.client.customer;
    exports ci553.happyshop.client.orderTracker;
    exports ci553.happyshop.client.emergency;
    exports ci553.happyshop.systemSetup;
}
