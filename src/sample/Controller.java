package sample;

import com.sun.javafx.scene.control.behavior.TitledPaneBehavior;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import javax.swing.plaf.synth.Region;


public class Controller {

    @FXML
    private Button b1, b2;
    @FXML
    private RadioButton rbD50;
    @FXML
    private RadioButton rbD65;
    @FXML
    private Region reg;
    @FXML
    private AnchorPane AP1;
    @FXML
    TextField tfR;
    @FXML
    TextField tfG;
    @FXML
    TextField tfB;

    final ToggleGroup g1 = new ToggleGroup();

    @FXML
    public void initialize(){
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int r = 0,
                        g = 0,
                        b = 0;
                if(tfR.getText() != null &&
                        tfG.getText() != null &&
                        tfB.getText() != null &&
                        !tfR.getText().isEmpty() &&
                        !tfG.getText().isEmpty() &&
                        !tfB.getText().isEmpty()) {
                     r = Integer.parseInt(tfR.getText());
                     g = Integer.parseInt(tfG.getText());
                     b = Integer.parseInt(tfB.getText());
                    b1.setBackground(new Background(new BackgroundFill(Color.rgb(r, g, b),CornerRadii.EMPTY ,Insets.EMPTY)));
                }
            }
        });

       g1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
           @Override
           public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
               String Dxx;
               if (g1.getSelectedToggle() != null) {
                   if(rbD50.isSelected()){
                       Dxx = rbD50.getText();}
                   else if (rbD65.isSelected()){
                       Dxx = rbD65.getText();}
               }
           }
       });

        rbD50.setToggleGroup(g1);

        rbD65.setToggleGroup(g1);
        rbD65.setSelected(true);
    }




}

