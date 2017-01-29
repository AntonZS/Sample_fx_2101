package sample;


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
    private Button btnLAB,
                    btnXYZ,
                    btnRGB,
                    btnHSB;

    @FXML
    private AnchorPane ap2,
                          AP1;

    @FXML
    TextField tfL,
            tfa,
            tfb;

    @FXML
    TextField tfH,
            tfS,
            tfB;
    @FXML
    TextField tfXYZ_X,
            tfXYZ_Y,
            tfXYZ_Z;

    @FXML
    TextField tfRGB_R,
            tfRGB_G,
            tfRGB_B;

    @FXML
    Label lout1;


    private static String Dxx = "D65";
    Lab_SB space1 = new Lab_SB(Dxx);


    double[] LAB = new double[3];

    public double[] getLAB() {
        LAB[0] = Double.parseDouble(tfL.getText());
        LAB[1] = Double.parseDouble(tfa.getText());
        LAB[2] = Double.parseDouble(tfb.getText());
        return LAB;
    }

    public void setLAB(double[] LAB) {
        this.LAB = LAB;
    }

    public double[] getXYZ() {
        return XYZ;
    }

    public void setXYZ(double[] XYZ) {
        tfXYZ_X.setText(String.valueOf(XYZ[0]));
        tfXYZ_Y.setText(String.valueOf(XYZ[1]));
        tfXYZ_Z.setText(String.valueOf(XYZ[2]));
    }

    public int[] getHSB() {
        return HSB;
    }

    public void setHSB(int[] HSB) {
        tfH.setText(String.valueOf(HSB[0]));
        tfS.setText(String.valueOf(HSB[1]));
        tfB.setText(String.valueOf(HSB[2]));
    }

    public int[] getRGB() {
        return RGB;
    }

    public void setRGB(int[] RGB) {
        tfRGB_R.setText(String.valueOf(RGB[0]));
        tfRGB_G.setText(String.valueOf(RGB[1]));
        tfRGB_B.setText(String.valueOf(RGB[2]));
        //set color from RGB to ap2
        lout1.setText("");
        ap2.setBackground(new Background(new BackgroundFill(Color.rgb(RGB[0], RGB[1], RGB[2]), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    double[] XYZ = new double[3];
    int[] HSB = new int[3];
    int[] RGB = new int[3];
    private void not_correct_color(){
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
        tfL.clear();
        tfa.clear();
        tfb.clear();
    }

    @FXML
    public void initialize(){
        btnLAB.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tfL.getText() != null &&
                        tfa.getText() != null &&
                        tfb.getText() != null &&
                        !tfL.getText().isEmpty() &&
                        !tfa.getText().isEmpty() &&
                        !tfb.getText().isEmpty()) {

                    LAB = getLAB();

                    RGB = space1.LABtoRGB(LAB);
                    if (RGB[0] < 255 && RGB[1] < 255 && RGB[2] < 255 && RGB[0] > 0 && RGB[1] > 0 && RGB[2] > 0) {

                        setRGB(space1.LABtoRGB(LAB));
                        setXYZ(space1.LABtoXYZ(LAB));
                        setHSB(space1.LABtoHSB(LAB));

                    } else {
                        not_correct_color();
                    }
                }
            }
        });
    }

}

