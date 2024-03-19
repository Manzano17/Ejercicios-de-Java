import javax.swing.*;

public class Ventana extends JFrame {

    JTextField tf1, tf2;
    JLabel L1, L2;
    JButton btn1, btn2;

    public Ventana() {

        super("Mi primer ventana");
        this.setBounds(300, 250, 400, 400);
        this.setLayout(null); // Al poner setLayout(null), tenemos que poner los componentes con cordenadas y no utilizara border, flow y grid layout.

        tf1 = new JTextField();
        tf1.setBounds(10, 10, 100, 20);

        tf2 = new JTextField();
        tf2.setBounds(10, 40, 100, 20);

        L1 = new JLabel("Etiqueta 1");
        L1.setBounds(10, 100, 100, 20);

        L2 = new JLabel("Etiqueta 2");
        L2.setBounds(10, 130, 100, 20);

        btn1 = new JButton("Boton 1");
        btn1.setBounds(10, 70, 100, 20);

        btn2 = new JButton("Boton 2");
        btn2.setBounds(120, 70, 100, 20); 

        GestionBotones g = new GestionBotones(this);
        btn1.addActionListener(g);
        btn2.addActionListener(g);

        this.add(tf1);
        this.add(tf2);
        this.add(L1);
        this.add(L2);
        this.add(btn1);
        this.add(btn2);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Ventana();
    }
}

