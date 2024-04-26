import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana2 extends JFrame {
    MiPanel p;

    public Ventana2() {
        super("Mi pelota");
        this.setBounds(10, 10, 700, 500);
        p = new MiPanel();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 

        this.setVisible(true);

        while (p.running) {
            p.moverGraficos();
            p.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String arg[]) {
        new Ventana2();
    }
}

class MiPanel extends JPanel {
    Pelota p = new Pelota(this);
    Raqueta r = new Raqueta(this, 10, 80);
    boolean running = true; 

    public MiPanel() {
        this.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                r.keyPressed(e);
            }

            public void keyTyped(KeyEvent e) { }

            public void keyReleased(KeyEvent e) {
                r.keyReleased(e);
            }
        });
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        p.pintarPelota(g2);
        r.pintarRaqueta(g2);
    }

    public void moverGraficos() {
        p.moverPelota();
        r.moverRaqueta();
    }
}

class Pelota {
    int x = 40;
    int y = 40;
    int xVel = 3;
    int yVel = 3;
    MiPanel pa;

    public Pelota(MiPanel xp) {
        pa = xp;
    }

    public void moverPelota() {
        if (x + xVel < 0) {
            if (!getColission()) {
                JOptionPane.showMessageDialog(pa, "Has perdido", "Fin del juego", JOptionPane.WARNING_MESSAGE);
                pa.running = false; 
                return;
            }
            xVel = xVel * -1;
        }
        if (x + xVel > pa.getWidth() - 30)
            xVel = xVel * -1;
        if (y + yVel < 0 || y + yVel > pa.getHeight() - 30)
            yVel = yVel * -1;

        x += xVel;
        y += yVel;
    }

    public void pintarPelota(Graphics2D xg2) {
        xg2.fillOval(x, y, 30, 30);
    }

    public boolean getColission() {
        return this.pa.r.getBounds().intersects(this.getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }
}

class Raqueta {
    MiPanel p;
    int x;
    int y;
    int yVel = 0;
    static final int ancho = 10;
    static final int alto = 60;

    public Raqueta(MiPanel xp, int xx, int xy) {
        p = xp;
        this.x = xx;
        this.y = xy;
    }

    public void pintarRaqueta(Graphics2D xg2) {
        xg2.fillRect(x, y, ancho, alto);
    }

    public void moverRaqueta() {
        if (y + yVel >= 0 && y + yVel <= p.getHeight() - alto) {
            y += yVel;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            yVel = 3;
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            yVel = -3;
    }

    public void keyReleased(KeyEvent e) {
        yVel = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
