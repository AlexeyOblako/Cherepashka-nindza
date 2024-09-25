import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Turtle extends JFrame {

    public Turtle() {
        setTitle("Черепаха");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        add(new TurtlePanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        new Turtle();
    }
}

class TurtlePanel extends JPanel implements MouseMotionListener {

    private int mouseX = 0;
    private int mouseY = 0;
    private int tailAngle = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Фон
        g.setColor(new Color(15, 82, 186)); // Море
        g.fillRect(0, 80, getWidth(), 1000);
        g.setColor(Color.YELLOW); // Песок
        g.fillRect(0, 400, getWidth(), 1500);
        g.setColor(new Color(135, 206, 250)); // Небо
        g.fillRect(0, 0, getWidth(), 80);

        // Ножки
        g.setColor(Color.ORANGE);
        g.fillRect(120, 130, 80, 120);
        g.fillRect(191, 130, 80, 120);
        g.fillRect(120, 200, 80, 100);
        g.fillRect(191, 200, 80, 100);

        // Голова
        g.setColor(Color.ORANGE);
        g.fillOval(165, 95, 60, 65);
        g.setColor(Color.BLACK);
        g.drawOval(165, 95, 60, 65);

        // Глаза
        g.setColor(Color.BLACK);
        g.fillOval(180, 110, 10, 10);
        g.fillOval(210, 110, 10, 10);

        // хвостик
        int tailLength = 50;
        int tailX = 190;
        int tailY = 295;
        int tailTipX = tailX + (int) (tailLength * Math.cos(Math.toRadians(tailAngle)));
        int tailTipY = tailY + (int) (tailLength * Math.sin(Math.toRadians(tailAngle)));
        int[] xTail = {tailX - 28, tailX + 42, tailTipX - 43};
        int[] yTail = {tailY, tailY, tailTipY + 40};
        g.setColor(Color.ORANGE);
        g.fillPolygon(xTail, yTail, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(xTail, yTail, 3);

        // Панцирь (восьмиугольник)
        int[] xOctagon = {105, 165, 225, 285, 285, 225, 165, 105};
        int[] yOctagon = {185, 125, 125, 185, 245, 305, 305, 245};
        g.setColor(Color.GREEN);
        g.fillPolygon(xOctagon, yOctagon, 8);
        g.setColor(Color.BLACK);
        g.drawPolygon(xOctagon, yOctagon, 8);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        updateTailAngle();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        updateTailAngle();
        repaint();
    }

    private void updateTailAngle() {
        // Рассчитываем угол поворота хвоста
        double angle = Math.atan2(mouseY - 280, mouseX + 200);
        tailAngle = (int) Math.toDegrees(angle);
        // Ограничиваем угол поворота хвоста
        if (tailAngle > 100) {  // Ограничение вправо
            tailAngle = 10;
        } else if (tailAngle < -105) { // Ограничение влево
            tailAngle = -45;
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        addMouseMotionListener(this);
    }
}