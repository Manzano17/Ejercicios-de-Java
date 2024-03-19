import java.awt.event.*;

public class GestionBotones implements ActionListener{

    Ventana v;

    public GestionBotones(Ventana xy){
        v = xy;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        v.tf1.getText();
        v.tf2.getText();
        v.L1.getText();
        v.L2.getText();
    }
}
