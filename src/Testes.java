import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Testes extends JFrame {

    private Pentagrama p;

    public Testes() {
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        p = new Pentagrama();
        p.setFocusable(true);

        p.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Rectangle r : p.rects) {
                    if (r.contains(e.getPoint())) {
                        System.out.println((r.y / G.DISTANCIA_ENTRE_LINHAS) - 10);

                        p.currX = r.x;
                        p.currY = r.y;

                        p.revalidate();
                        p.repaint();

                        break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        p.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    p.currY -= G.DISTANCIA_ENTRE_LINHAS;

                    System.out.println(p.currY / G.DISTANCIA_ENTRE_LINHAS - 10);

                    p.revalidate();
                    p.repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    p.currY += G.DISTANCIA_ENTRE_LINHAS;
                    System.out.println(p.currY / G.DISTANCIA_ENTRE_LINHAS - 10);

                    p.revalidate();
                    p.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        add(p);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
    }
}
