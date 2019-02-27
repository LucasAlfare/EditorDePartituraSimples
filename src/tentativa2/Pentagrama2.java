package tentativa2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*

.
--
.
--
.
--
.
--
.    idx=8
----
.
----
.
----
.
----
.
----
.
--   idx=19
.
--
.
--
.
--
.
--

 */
@SuppressWarnings("WeakerAccess")
public class Pentagrama2 extends JComponent {

    public int currX = 10;
    public int currY = (G.ESPACAMENTO_INICIAL_PENTAGRAMA * G.DISTANCIA_ENTRE_LINHAS) +
            ((G.ESPACAMENTO_INICIAL_PENTAGRAMA - 2) * G.DISTANCIA_ENTRE_LINHAS);
    public ArrayList<Rectangle> rects;
    int a = G.DISTANCIA_ENTRE_LINHAS;
    int e = G.ESPACAMENTO_INICIAL_PENTAGRAMA;
    int h = G.ESPACAMENTO_HORIZONTAL;

    public Pentagrama2() {
        rects = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean isEspaco = true;
        for (int i = 0; i < 28 * a; i += a) {
            g.setColor(new Color((int) (Math.random() * 0x1000000)));
            g.fillRect(0, i, 500, 10);

            if (!isEspaco) {
                rects.add(new Linha(0, i, 500, a));
                isEspaco = true;

                if (i / a > 8 && i / a < 18) {
                    g.setColor(Color.BLACK);
                    g.drawLine(0, i + (a / 2), 500, i + (a / 2));
                }
            } else {
                rects.add(new Espaco(0, i, 500, a));
                isEspaco = false;
            }
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
