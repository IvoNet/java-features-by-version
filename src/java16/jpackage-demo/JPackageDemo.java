import javax.swing.*;

public class JPackageDemo {
        private static void createAndShowGUI() {
            final JFrame frame = new JFrame("Hello, world!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200,100);

            final JLabel label = new JLabel("Hello, JFall 2021");
            frame.getContentPane().add(label);

            frame.setVisible(true);
        }

        public static void main(final String[] args) {
            javax.swing.SwingUtilities.invokeLater(JPackageDemo::createAndShowGUI);
        }
}
