package tentativaelementoinicio;

import org.apache.batik.transcoder.TranscoderException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("WeakerAccess")
public class Pauta extends JComponent {

    public final ArrayList<Rectangle> rects;
    public int currX = 10;
    int a = G.DISTANCIA_ENTRE_LINHAS;
    public int currY = 15 * a;
    int e = G.ESPACAMENTO_INICIAL_PENTAGRAMA;

    public Pauta() {
        rects = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            g.drawImage(new SVGIcon("src/tentativaelementoinicio/clave-de-sol.svg", 95, 153).bufferedImage, 0, 0, null);
        } catch (TranscoderException e1) {
            e1.printStackTrace();
        }

        /*
        translada um pouco para "baixo"
        a fim de forcener um pequeno
        espaçamento inicial
         */
        g.translate(0, e);

        /*
        executa os desenhos do pentagrama
        quadrado marcador e linhas auxiliares
         */
        pentagrama(g);
        //quadradoMarcador(g);
        //linhasAux(g);

        /*
        retorna os graáficos para a posição
        inicial
         */
        g.translate(0, -e);
    }

    private void pentagrama(Graphics g) {
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

    private void quadradoMarcador(Graphics g) {
        g.setColor(G.COR_QUADRADO_MARCADOR);
        g.fillRect(currX, currY - a, a * 3, a * 3);

        g.setColor(Color.BLACK);
        g.drawRect(currX, currY - a, a * 3, a * 3);
    }

    private void linhasAux(Graphics g) {
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

    private void desenharLinhaPequena(Graphics g, int i) {
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
