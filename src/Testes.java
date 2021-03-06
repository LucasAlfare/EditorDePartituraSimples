import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Testes extends JFrame {

    private Pauta p;

    public Testes() {
        setSize(300, 500);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        p = new Pauta();
        p.setFocusable(true);

        p.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Rectangle r : p.rects) {
                    if (r.contains(e.getPoint())) {
                        System.out.println(p.rects.indexOf(r));

                        //p.currX = r.x;
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
                    if (p.currY >= G.DISTANCIA_ENTRE_LINHAS + G.ESPACAMENTO_INICIAL_PENTAGRAMA) {
                        p.currY -= G.DISTANCIA_ENTRE_LINHAS;
                        System.out.println(p.currY / G.DISTANCIA_ENTRE_LINHAS);

                        p.revalidate();
                        p.repaint();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (p.currY <= (28 * G.DISTANCIA_ENTRE_LINHAS) + G.ESPACAMENTO_INICIAL_PENTAGRAMA) {
                        p.currY += G.DISTANCIA_ENTRE_LINHAS;
                        System.out.println(p.currY / G.DISTANCIA_ENTRE_LINHAS);

                        p.revalidate();
                        p.repaint();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        add(p);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
//        int a = 5;
//        int e = a * 5;

//        for (int i = e; i < (28 * a) + e; i += a) {
//            System.out.println((i - e) / a);
//        }

//        int c = 25 * a;
//        System.out.println((c - e) / a >= 19 ? "preciso desenhar!" : "");
//
//        for (int i = c; i >= (19 * a) + e; i -= a) {
//            System.out.println("-----");
//        }
    }
}
