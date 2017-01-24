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
    @FXML
    TextField tfRGB_R;
    @FXML
    TextField tfRGB_G;
    @FXML
    TextField tfRGB_B;
    @FXML
    TextField tfXYZ_X;
    @FXML
    TextField tfXYZ_Y;
    @FXML
    TextField tfXYZ_Z;

    private static String Dxx = "D65";
    Lab_SB space1 = new Lab_SB(Dxx);


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
                    double[] XYZ = space1.LABtoXYZ(Lab);
                    if (RGB[0] < 255 && RGB[1] < 255 && RGB[2] < 255) {
                        lout1.setText("");
                        ap2.setBackground(new Background(new BackgroundFill(Color.rgb(RGB[0], RGB[1], RGB[2]), CornerRadii.EMPTY, Insets.EMPTY)));

                        setHSB(HSB);

                        setRGB(RGB);

                        setRGB(RGB);

                        tfXYZ_X.setText(String.valueOf(XYZ[0]));
                        tfXYZ_Y.setText(String.valueOf(XYZ[1]));
                        tfXYZ_Z.setText(String.valueOf(XYZ[2]));

                    } else {
                        lout1.setText("этот цвет не кодируется в sRGB и HSB");
                        ap2.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
                        tfH.clear();
                        tfS.clear();
                        tfB.clear();
                        tfRGB_R.clear();
                        tfRGB_G.clear();
                        tfRGB_B.clear();
                        tfXYZ_X.clear();
                        tfXYZ_Y.clear();
                        tfXYZ_Z.clear();
                    }
                }
            }

            public void setHSB(int[] HSB) {
                tfH.setText(String.valueOf(HSB[0]));
                tfS.setText(String.valueOf(HSB[1]));
                tfB.setText(String.valueOf(HSB[2]));
            }

            public void setRGB(int[] RGB) {
                tfRGB_R.setText(String.valueOf(RGB[0]));
                tfRGB_G.setText(String.valueOf(RGB[1]));
                tfRGB_B.setText(String.valueOf(RGB[2]));
            }
        });

    }




}

