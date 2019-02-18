import javax.swing.*;

public class Testes extends JFrame {

    public Testes() {
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        add(new Pentagrama());
    }

    public static void main(String[] args) {
        new Testes().setVisible(true);
    }
}
