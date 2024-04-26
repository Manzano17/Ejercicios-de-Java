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

    public static void main(String[] args) {
        new Ventana2();
    }
}

class MiPanel extends JPanel {
    Pelota pelota = new Pelota(this);
    Raqueta r = new Raqueta(this, 10, 80);
    Raqueta r2 = new Raqueta(this, 660, 80);
    boolean running = true;

    public MiPanel() {
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    r2.keyPressed(e);
                } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    r.keyPressed(e);
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    r2.keyReleased(e);
                } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) {
                    r.keyReleased(e);
                }
            }
        });
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pelota.pintarPelota(g2);
        r.pintarRaqueta(g2);
        r2.pintarRaqueta(g2);
    }

    public void moverGraficos() {
        pelota.moverPelota();
        r.moverRaqueta();
        r2.moverRaqueta();
    }
}

class Pelota {
    int x = 40;
    int y = 40;
    int xVel = 3;
    int yVel = 3;
    static final int DIAMETER = 30;
    MiPanel pa;

    public Pelota(MiPanel xp) {
        pa = xp;
    }

    public void moverPelota() {
        x += xVel;
        y += yVel;

        if (x < 0 || x > pa.getWidth() - DIAMETER) {
            JOptionPane.showMessageDialog(pa, "Has perdido", "Fin del juego", JOptionPane.WARNING_MESSAGE);
            pa.running = false;
            return;
        }

        if (y < 0 || y > pa.getHeight() - DIAMETER) {
            yVel *= -1;
        }

        if (getBounds().intersects(pa.r.getBounds()) || getBounds().intersects(pa.r2.getBounds())) {
            xVel *= -1;
        }
    }

    public void pintarPelota(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}

class Raqueta {
    MiPanel panel;
    int x, y;
    int yVel = 0;
    static final int ANCHO = 10, ALTO = 60;

    public Raqueta(MiPanel panel, int x, int y) {
        this.panel = panel;
        this.x = x;
        this.y = y;
    }

    public void pintarRaqueta(Graphics2D g) {
        g.fillRect(x, y, ANCHO, ALTO);
    }

    public void moverRaqueta() {
        if (y + yVel >= 0 && y + yVel <= panel.getHeight() - ALTO) {
            y += yVel;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            yVel = -3;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            yVel = 3;
        else if (e.getKeyCode() == KeyEvent.VK_W)
            yVel = -3;
        else if (e.getKeyCode() == KeyEvent.VK_S)
            yVel = 3;
    }

    public void keyReleased(KeyEvent e) {
        yVel = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ANCHO, ALTO);
    }
}
