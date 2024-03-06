module com.vanquyenit.baitap1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vanquyenit.baitap1 to javafx.fxml;
    opens com.vanquyenit.baitap1.Controller to javafx.fxml;
    exports com.vanquyenit.baitap1;
    exports com.vanquyenit.baitap1.Controller;
}