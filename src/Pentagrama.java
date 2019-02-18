import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Pentagrama extends JComponent {

    private static final int DISTANCIA_ENTRE_LINHAS = 5;
    private int currX, currY = 10 * DISTANCIA_ENTRE_LINHAS;

    private ArrayList<Rectangle> rects;

    public Pentagrama() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Rectangle r : rects) {
                    if (r.contains(e.getPoint())) {
                        System.out.println((r.y / DISTANCIA_ENTRE_LINHAS) - 10);

                        currX = r.x;
                        currY = r.y;

                        revalidate();
                        repaint();
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

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                System.out.println(e.getKeyCode());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        retangulosPauta(g);
        quadradoMarcador(10, (currY - DISTANCIA_ENTRE_LINHAS) - 1, g);
    }

    private ArrayList<Rectangle> retangulosPauta(Graphics g) {
        ArrayList<Rectangle> r = new ArrayList<>();
        int a = DISTANCIA_ENTRE_LINHAS;
        boolean isEspaco = true;

        for (int i = 10 * a; i < (11 + 10) * a; i += a) {
            if (isEspaco) {
                Rectangle esp = new Espaco(0, i, 500, a);

//                pintando pra debugar
//                g.setColor(Color.darkGray);
//                g.fillRect(espaco.x, espaco.y, espaco.width, espaco.height);

                r.add(esp);
            } else {
                Rectangle linha = new Linha(0, i, 500, a);

//                pintando pra debugar
//                g.setColor(Color.lightGray);
//                g.fillRect(linha.x, linha.y, linha.width, linha.height);

                //desenha a linha na metade do retangulo
                g.setColor(new Color(0, 0, 0, 140));
                g.drawLine(0, (i + (a / 2)), 500, (i + (a / 2)));
                r.add(linha);
            }

            isEspaco = !isEspaco;
        }

        rects = r;
        return rects;
    }

    private void quadradoMarcador(int x, int y, Graphics g) {
        int a = DISTANCIA_ENTRE_LINHAS;

        g.setColor(new Color(0, 255, 150, 125));
        g.fillRect(x, y, a * 3, a * 3);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, a * 3, a * 3);
    }

    class Espaco extends Rectangle {
        public Espaco(int x, int y, int w, int h) {
            super(x, y, w, h);
        }
    }

    class Linha extends Rectangle {
        public Linha(int x, int y, int w, int h) {
            super(x, y, w, h);
        }
    }
}
