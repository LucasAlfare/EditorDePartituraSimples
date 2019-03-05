import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import static java.awt.RenderingHints.*;

/*
-
----------
-
----------
-
----------
-
----------
-.........
----------
-
----------
-
----------
-
----------
-
----------
-.........
----------
-
----------19
-
----------
-
----------
-
----------
 */

@SuppressWarnings("WeakerAccess")
public class Pauta extends JComponent {

    public final ArrayList<Rectangle> rects;

    int a = G.DISTANCIA_ENTRE_LINHAS;
    int e = G.ESPACAMENTO_INICIAL_PENTAGRAMA;

    public int currX = 35;
    public int currY = 15 * a;

    public Pauta() {
        rects = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g2 = (Graphics2D) g1;

        g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(KEY_STROKE_CONTROL, VALUE_STROKE_PURE);
        g2.setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);

        pentagrama(g2);
        quadradoMarcador(g2);
        linhasAux(g2);
    }

    private void pentagrama(Graphics2D g) {
        boolean isEspaco = true;
        for (int i = e; i < (28 * a) + e; i += a) {
//            //pintando para debugar
//            g.setColor(new Color((int) (Math.random() * 0x1000000)));
//            g.fillRect(0, i, 500, a);

            if (!isEspaco) {
                rects.add(new Linha(0, i, 500, a));
                isEspaco = true;

                if (((i - e) / a > 8) && ((i - e) / a < 19)) {
                    g.setColor(G.COR_PADRAO_LINHAS);
                    g.drawLine(0, i + (a / 2), 500, i + (a / 2));
                }
            } else {
                rects.add(new Espaco(0, i, 500, a));
                isEspaco = false;
            }
        }
    }

    private void elementoInicio(Graphics2D g) {
        Path2D painter = new Path2D.Float();

        Figuras.claveDeSol(painter, 0, (a + a / 4) * a, a * 0.09f);
        g.setColor(Color.BLACK);
        g.fill(painter);
    }

    private void quadradoMarcador(Graphics2D g) {
        g.setColor(G.COR_QUADRADO_MARCADOR);
        g.fillRect(currX, currY - a, a * 3, a * 3);

        g.setColor(Color.BLACK);
        g.drawRect(currX, currY - a, a * 3, a * 3);
    }

    private void linhasAux(Graphics2D g) {
        if ((currY - e) / a <= 7) {
            g.setColor(G.COR_PADRAO_LINHAS);
            for (int i = currY; i <= (7 * a) + e; i += a) {
                desenharLinhaPequena(g, i);
            }
        }

        else

        if ((currY - e) / a >= 19) {
            g.setColor(G.COR_PADRAO_LINHAS);
            for (int i = currY; i >= (19 * a) + e; i -= a) {
                desenharLinhaPequena(g, i);
            }
        }
    }

    private void desenharLinhaPequena(Graphics2D g, int i) {
        if (rects.get((i - e) / a) instanceof Linha) {
            g.drawLine(
                    currX - (a / 2),
                    i + (a / 2),
                    (currX - (a / 2)) + (a * 4),
                    i + (a / 2)
            );
        }
    }

    class Espaco extends Rectangle {
        Espaco(int x, int y, int w, int h) {
            super(x, y, w, h);
        }
    }

    class Linha extends Rectangle {
        Linha(int x, int y, int w, int h) {
            super(x, y, w, h);
        }
    }
}
