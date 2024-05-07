package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class MarketCardController {

    @FXML
    private Button BuyButton;

    @FXML
    private Label companyName;

    @FXML
    private Label price;

    @FXML
    private Spinner<Integer> quantitySelector;
}
