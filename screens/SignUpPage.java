package screens;

import java.awt.*;
import javax.swing.*;

public class SignUpPage extends JFrame {

    public SignUpPage() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = createTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = createTextField();

        JLabel budgetLabel = new JLabel("Monthly Budget:");
        JTextField budgetField = createTextField("200.00");

        JButton BtnContinue = createStyledButton("Continue");
        BtnContinue.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            double totalBudget = Double.parseDouble(budgetField.getText());
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget); // Pass the data to MainMenuPage
        });

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(firstNameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lastNameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(budgetLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(budgetField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(BtnContinue, gbc);

        add(panel);
        setVisible(true);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        return textField;
    }

    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setPreferredSize(new Dimension(200, 25));
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setBackground(new Color(192, 192, 192));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192)));
        return button;
    }
}


// package screens;

// import java.awt.*;
// import javax.swing.*;

// public class SignUpPage extends JFrame {

//     public SignUpPage() {
//         setTitle("Sign Up");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(400, 400);
//         setLocationRelativeTo(null);

//         // Main panel with GridBagLayout
//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(224, 247, 250));
//         panel.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Labels and input fields
//         JLabel firstNameLabel = new JLabel("First Name:");
//         JTextField firstNameField = createTextField();

//         JLabel lastNameLabel = new JLabel("Last Name:");
//         JTextField lastNameField = createTextField();

//         JLabel budgetLabel = new JLabel("Monthly Budget:");
//         JTextField budgetField = createTextField("200.00");

//         // Continue button
//         JButton BtnContinue = createStyledButton("Continue");
//         BtnContinue.addActionListener(e -> {
//             double totalBudget = Double.parseDouble(budgetField.getText());
//             dispose();
//             new MainMenuPage(totalBudget); // Pass the budget
//         });
        

//         // Adding components to the panel
//         gbc.gridx = 0; gbc.gridy = 0;
//         panel.add(firstNameLabel, gbc);

//         gbc.gridx = 1; gbc.gridy = 0;
//         panel.add(firstNameField, gbc);

//         gbc.gridx = 0; gbc.gridy = 1;
//         panel.add(lastNameLabel, gbc);

//         gbc.gridx = 1; gbc.gridy = 1;
//         panel.add(lastNameField, gbc);

//         gbc.gridx = 0; gbc.gridy = 2;
//         panel.add(budgetLabel, gbc);

//         gbc.gridx = 1; gbc.gridy = 2;
//         panel.add(budgetField, gbc);

//         gbc.gridx = 0; gbc.gridy = 3;
//         gbc.gridwidth = 2; // Span two columns for the button
//         panel.add(BtnContinue, gbc);

//         add(panel);
//         setVisible(true);
//     }

//     private JTextField createTextField() {
//         JTextField textField = new JTextField();
//         textField.setPreferredSize(new Dimension(200, 25)); // Lengthened size
//         return textField;
//     }

//     private JTextField createTextField(String text) {
//         JTextField textField = new JTextField(text);
//         textField.setPreferredSize(new Dimension(200, 25)); // Lengthened size
//         return textField;
//     }

//     private JButton createStyledButton(String text) {
//         JButton button = new JButton(text);
//         button.setFont(new Font("Roboto", Font.PLAIN, 14));
//         button.setBackground(new Color(192, 192, 192)); // Light gray
//         button.setForeground(Color.BLACK);
//         button.setFocusPainted(false);
//         button.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192))); // No outline
//         return button;
//     }
// }
