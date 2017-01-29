package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;



public class Controller {

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private RadioButton rbD50;
    @FXML
    private RadioButton rbD65;
    @FXML
    private AnchorPane ap2;
    @FXML
    private AnchorPane AP1;
    @FXML
    TextField tfL;
    @FXML
    TextField tfa;
    @FXML
    TextField tfb;
    @FXML
    TextField tfH;
    @FXML
    TextField tfS;
    @FXML
    TextField tfB;
    @FXML
    Label lout1;


    private static String Dxx = "D65";
    Lab_SB space1 = new Lab_SB(Dxx);

    final ToggleGroup g1 = new ToggleGroup();

    @FXML
    public void initialize(){
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tfL.getText() != null &&
                        tfa.getText() != null &&
                        tfb.getText() != null &&
                        !tfL.getText().isEmpty() &&
                        !tfa.getText().isEmpty() &&
                        !tfb.getText().isEmpty()) {

                    double L = Double.parseDouble(tfL.getText());
                    double a = Double.parseDouble(tfa.getText());
                    double b = Double.parseDouble(tfb.getText());

                    double[] Lab = {L, a, b};
                    int[] HSB = space1.LABtoHSB(Lab);
                    int[] RGB = space1.LABtoRGB(Lab);
                    if (RGB[0] < 255 && RGB[1] < 255 && RGB[2] < 255) {
                        lout1.setText("");
                        ap2.setBackground(new Background(new BackgroundFill(Color.rgb(RGB[0], RGB[1], RGB[2]), CornerRadii.EMPTY, Insets.EMPTY)));
                        tfH.setText(String.valueOf(HSB[0]));
                        tfS.setText(String.valueOf(HSB[1]));
                        tfB.setText(String.valueOf(HSB[2]));
                    } else {
                        lout1.setText("этот цвет не кодируется в sRGB и HSB");
                        ap2.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
                        tfH.clear();
                        tfS.clear();
                        tfB.clear();
                    }
                }
            }
        });
        rbD50.setToggleGroup(g1);
        rbD65.setToggleGroup(g1);
        rbD65.setSelected(true);


       g1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

           @Override
           public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

               if (g1.getSelectedToggle() != null) {
                   if(rbD50.isSelected()){
                       Dxx = rbD50.getText();}
                   else if (rbD65.isSelected()){
                       Dxx = rbD65.getText();}
               }
               Lab_SB space1 = new Lab_SB(Dxx);
           }
       });


    }




}

