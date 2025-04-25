import javax.swing.*;
import java.awt.*;

public class ConnexionView extends JPanel {

    JTextField idField;
    JPasswordField mdpField;
    JButton btnConnecter, btnInscrire, btnOublie;

    public ConnexionView(ApplicationFrame parent) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lblId = new JLabel("Identifiant :");
        JLabel lblMdp = new JLabel("Mot de passe :");

        idField = new JTextField(15);
        mdpField = new JPasswordField(15);

        btnConnecter = new JButton("Se connecter");
        btnInscrire = new JButton("S'inscrire");
        btnOublie = new JButton("Mot de passe oublié");

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        add(lblId, c);

        c.gridx = 1;
        add(idField, c);

        c.gridx = 0;
        c.gridy = 1;
        add(lblMdp, c);

        c.gridx = 1;
        add(mdpField, c);

        JPanel buttons = new JPanel();
        buttons.add(btnConnecter);
        buttons.add(btnInscrire);
        buttons.add(btnOublie);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(buttons, c);
    }
}
