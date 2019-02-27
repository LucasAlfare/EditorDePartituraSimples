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
public class Pauta extends JComponent {

    public ArrayList<Rectangle> rects;

    int a = G.DISTANCIA_ENTRE_LINHAS;
    int e = G.ESPACAMENTO_INICIAL_PENTAGRAMA;
    int h = G.ESPACAMENTO_HORIZONTAL;

    public int currX = 10;
    public int currY = 15 * a;

    public Pauta() {
        rects = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        pentagrama(g);
        quadradoMarcador(g);
        linhasAux(g);
    }

    private void pentagrama(Graphics g) {
        int f = a + 3;
        boolean isEspaco = true;
        for (int i = 0; i < 28 * a; i += a) {
            //pintando para debugar
//            g.setColor(new Color((int) (Math.random() * 0x1000000)));
//            g.fillRect(0, i, 500, a);

            if (!isEspaco) {
                rects.add(new Linha(0, i, 500, a));
                isEspaco = true;

                if (((i / a) > 8) && ((i / a) < 18)) {
                    g.setColor(new Color(0, 0, 0, 140));
                    g.drawLine(0, i + (a / 2), 500, i + (a / 2));
                }
            } else {
                rects.add(new Espaco(0, i, 500, a));
                isEspaco = false;
            }
        }
    }

    private void quadradoMarcador(Graphics g) {
        g.setColor(new Color(0, 255, 150, 125));
        g.fillRect(currX, currY - a, a * 3, a * 3);

        g.setColor(Color.BLACK);
        g.drawRect(currX, currY - a, a * 3, a * 3);
    }

    private void linhasAux(Graphics g) {
        if ((currY / a) <= 7) {
            g.setColor(new Color(0, 0, 0, 140));
            for (int i = currY; i <= 7 * a; i += a) {
                if (rects.get(i / a) instanceof Linha) {
                    g.drawLine(
                            currX - (a / 2),
                            i + (a / 2),
                            (currX - (a / 2)) + (a * 4),
                            i + (a / 2)
                    );
                }
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
