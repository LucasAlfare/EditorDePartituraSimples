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
public class Pentagrama extends JComponent {

    public int currX = 10;
    public int currY =
            (G.ESPACAMENTO_INICIAL_PENTAGRAMA * G.DISTANCIA_ENTRE_LINHAS)
                    +
                    ((G.ESPACAMENTO_INICIAL_PENTAGRAMA - 2) * G.DISTANCIA_ENTRE_LINHAS);

    int a = G.DISTANCIA_ENTRE_LINHAS;
    int e = G.ESPACAMENTO_INICIAL_PENTAGRAMA;
    int h = G.ESPACAMENTO_HORIZONTAL;

    public ArrayList<Rectangle> rects;

    private static int alturaParaUnidade(int alturaY) {
        return (alturaY / G.DISTANCIA_ENTRE_LINHAS) - G.ESPACAMENTO_INICIAL_PENTAGRAMA;
    }

    private static int unidadeParaAltura(int unidade) {
        return (unidade + G.DISTANCIA_ENTRE_LINHAS) * G.ESPACAMENTO_INICIAL_PENTAGRAMA;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        retangulosPauta(g);
        quadradoMarcador(10, (currY - G.DISTANCIA_ENTRE_LINHAS) - 1, g);
        linhasAuxiliares(10, currY, g);
    }

    private void retangulosPauta(Graphics g) {
        ArrayList<Rectangle> r = new ArrayList<>();

        boolean isEspaco = true;

        for (int i = e * a; i < (28 + e) * a; i += a) {
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

                //desenha apenas as 5 linhas padrão
                if ((alturaParaUnidade(i)) >= 8 && (alturaParaUnidade(i)) <= 18) {
                    //desenha a linha na metade do retangulo
                    g.setColor(new Color(0, 0, 0, 140));
                    g.drawLine(0, (i + (a / 2)), 500, (i + (a / 2)));
                    r.add(linha);
                }
            }

            isEspaco = !isEspaco;
        }

        rects = r;
    }

    private void quadradoMarcador(int x, int y, Graphics g) {
        g.setColor(new Color(0, 255, 150, 125));
        g.fillRect(x, y, a * 3, a * 3);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, a * 3, a * 3);
    }

    private void linhasAuxiliares(int x, int y, Graphics g) {
        if (alturaParaUnidade(y) < 8) {
            System.out.println("-> " + y);
            for (int i = y; i < unidadeParaAltura(7); i += unidadeParaAltura(7)) {
                g.setColor(new Color(0, 0, 0, 140));
                g.drawLine(a, (i + (a / 2)), a * 6, (i + (a / 2)));
            }
        }
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
