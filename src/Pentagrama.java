import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pentagrama extends JComponent {

    public int currX = 10;
    public int currY =
            (G.ESPACAMENTO_INICIAL_PENTAGRAMA * G.DISTANCIA_ENTRE_LINHAS)
                    +
                    ((G.ESPACAMENTO_INICIAL_PENTAGRAMA - 2) * G.DISTANCIA_ENTRE_LINHAS);

    public ArrayList<Rectangle> rects;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        retangulosPauta(g);
        quadradoMarcador(10, (currY - G.DISTANCIA_ENTRE_LINHAS) - 1, g);
    }

    private void retangulosPauta(Graphics g) {
        ArrayList<Rectangle> r = new ArrayList<>();
        int a = G.DISTANCIA_ENTRE_LINHAS;
        int e = G.ESPACAMENTO_INICIAL_PENTAGRAMA;
        int h = G.ESPACAMENTO_HORIZONTAL;
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
                if ((i / a - e) >= 8 && (i / a - e) <= 18) {
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
        int a = G.DISTANCIA_ENTRE_LINHAS;

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
