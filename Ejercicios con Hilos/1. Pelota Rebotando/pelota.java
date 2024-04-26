import javax.swing.*;
import java.awt.*;

public class pelota  extends JFrame{
    PanelV p;

    public pelota(){
        super("Pelota rebotando");
        this.setBounds(10, 10, 700, 500);
        p = new PanelV();
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
        this.setVisible(true);

        while(true){
            p.MoverPelota();
            p.repaint();

            try{
                Thread.sleep(10);
            } catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }

    public static void main (String arg[]){
        new pelota();
    }
}

class PanelV extends JPanel {
    int x = 10;
    int y = 10;
    int xVel = 3;
    int yVel = 3;

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.drawOval(x, y, 30 , 30);
  }

    public void MoverPelota() {
        if (x + xVel < 0)
            xVel = xVel *-1;
        if (x + xVel > this.getWidth() - 30)
            xVel = xVel *-1;
        if (y + yVel < 0)
            yVel = yVel *-1;
        if (y + yVel > this.getHeight() - 30)
        yVel = yVel * -1;

        x = x + xVel;
        y = y + yVel;
    }
}

