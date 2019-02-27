package tentativaelementoinicio;

import org.apache.batik.transcoder.TranscoderException;

import javax.swing.*;

public class Teste extends JFrame {

    public Teste() throws TranscoderException {
        setSize(400, 200);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        Icon i = new SVGIcon("src/tentativaelementoinicio/clave-de-sol.svg", 95, 153);


        JLabel l = new JLabel(i);


        add(l);

        setVisible(true);
    }

    public static void main(String[] args) throws TranscoderException {
        new Teste();
    }
}
