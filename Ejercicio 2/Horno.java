import java.awt.*;
import javax.swing.*;

public class Horno extends JFrame {

    JPanel panelBotones;

    public Horno(){
        super("Simulacion de Horno");
        this.setBounds(500,100,600,400);

        JPanel panelBotones = new JPanel();
        //Se eligio Grid para dimencionar el tamaño de cuadriculas que en este caso fue 4 x 3
        panelBotones.setLayout(new GridLayout(4,3));

        //Se crea un ciclo for para crear los botones del 1 al 9
        for (int i = 1; i <= 9; i++) {
            panelBotones.add(new JButton("" + i));
        }

        panelBotones.add(new JButton("0"));
        panelBotones.add(new JButton("start"));
        panelBotones.add(new JButton("Stop"));

        JPanel pControles = new JPanel();
        //Se agrego el BorderLayout para mover los objetos.
        pControles.setLayout(new BorderLayout());
        pControles.add(new JTextField("00 : 00"), BorderLayout.NORTH);

        pControles.add(new JButton("Comida"), BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        this.add(pControles, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.EAST);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new Horno();
    }
}