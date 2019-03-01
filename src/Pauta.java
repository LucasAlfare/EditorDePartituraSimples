import org.apache.batik.transcoder.TranscoderException;
import tentativaelementoinicio.SVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

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
        Graphics2D g2 = (Graphics2D)g1;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        /*
        translada um pouco para "baixo"
        a fim de forcener um pequeno
        espaçamento inicial
         */
        g2.translate(0, e);

        elementoInicio(g2);

        /*
        executa os desenhos do pentagrama
        quadrado marcador e linhas auxiliares
         */
        pentagrama(g2);
        quadradoMarcador(g2);
        linhasAux(g2);

        /*
        retorna os graáficos para a posição
        inicial
         */
        g2.translate(0, -e);
    }

    private void elementoInicio(Graphics2D g) {
        Path2D painter = new Path2D.Float();

        Figuras.claveDeSol(painter, 0, 20*a, a*0.09f);
        g.setColor(Color.BLACK);
        g.fill(painter);
    }

    private void pentagrama(Graphics2D g) {
        boolean isEspaco = true;
        for (int i = 0; i < 28 * a; i += a) {
//            //pintando para debugar
//            g.setColor(new Color((int) (Math.random() * 0x1000000)));
//            g.fillRect(0, i, 500, a);

            if (!isEspaco) {
                rects.add(new Linha(0, i, 500, a));
                isEspaco = true;

                if (((i / a) > 8) && ((i / a) < 18)) {
                    g.setColor(G.COR_PADRAO_LINHAS);
                    g.drawLine(0, i + (a / 2), 500, i + (a / 2));
                }
            } else {
                rects.add(new Espaco(0, i, 500, a));
                isEspaco = false;
            }
        }
    }

    private void quadradoMarcador(Graphics2D g) {
        g.setColor(G.COR_QUADRADO_MARCADOR);
        g.fillRect(currX, currY - a, a * 3, a * 3);

        g.setColor(Color.BLACK);
        g.drawRect(currX, currY - a, a * 3, a * 3);
    }

    private void linhasAux(Graphics2D g) {
        if ((currY / a) <= 7) {
            g.setColor(G.COR_PADRAO_LINHAS);
            for (int i = currY; i <= 7 * a; i += a) {
                desenharLinhaPequena(g, i);
            }
        } else if ((currY / a > 17)) {
            g.setColor(G.COR_PADRAO_LINHAS);
            for (int i = currY; i > 17 * a; i -= a) {
                desenharLinhaPequena(g, i);
            }
        }
    }

    private void desenharLinhaPequena(Graphics2D g, int i) {
        if (rects.get(i / a) instanceof Linha) {
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
