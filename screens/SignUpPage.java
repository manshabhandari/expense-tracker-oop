package screens;

import java.awt.*;
import javax.swing.*;

public class SignUpPage extends JFrame {

    public SignUpPage() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
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
        JTextField budgetField = createTextField();

        Dimension buttonSize = new Dimension(200, 40);
        JButton BtnContinue = createStyledButton("Continue", buttonSize);

        BtnContinue.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String budgetText = budgetField.getText().trim();

            boolean hasError = false;

            // Validate the budget field
            if (budgetText.isEmpty()) {
                budgetField.setBackground(new Color(255, 182, 193)); // Light pink background
                JOptionPane.showMessageDialog(this, "Monthly Budget is required.", "Error", JOptionPane.ERROR_MESSAGE);
                hasError = true;
            } else {
                try {
                    double totalBudget = Double.parseDouble(budgetText);

                    if (totalBudget <= 0) {
                        budgetField.setBackground(new Color(255, 182, 193)); // Light pink background
                        JOptionPane.showMessageDialog(this, "Monthly Budget must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                        hasError = true;
                    } else {
                        budgetField.setBackground(Color.WHITE); // Reset background to white
                    }
                } catch (NumberFormatException ex) {
                    budgetField.setBackground(new Color(255, 182, 193)); // Light pink background
                    JOptionPane.showMessageDialog(this, "Monthly Budget must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    hasError = true;
                }
            }

            // Proceed only if there are no errors
            if (!hasError) {
                dispose();
                new MainMenuPage(firstName, lastName, Double.parseDouble(budgetText)); // Pass the data to MainMenuPage
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(budgetLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(budgetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
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

    private JButton createStyledButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setBackground(Color.decode("#DEDEDE"));
        button.setOpaque(true);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#DEDEDE")));
        button.setPreferredSize(size);
        return button;
    }
}


// package screens;


// import java.awt.*;
// import javax.swing.*;


// public class SignUpPage extends JFrame {


//    public SignUpPage() {
//        setTitle("Sign Up");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(750, 500);
//        setLocationRelativeTo(null);


//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(224, 247, 250));
//        panel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;


//        JLabel firstNameLabel = new JLabel("First Name:");
//        JTextField firstNameField = createTextField();


//        JLabel lastNameLabel = new JLabel("Last Name:");
//        JTextField lastNameField = createTextField();


//        JLabel budgetLabel = new JLabel("Monthly Budget:");
//        JTextField budgetField = createTextField();


//        Dimension buttonSize = new Dimension(200, 40);
//        JButton BtnContinue = createStyledButton("Continue", buttonSize);
//        BtnContinue.addActionListener(e -> {
//            String firstName = firstNameField.getText();
//            String lastName = lastNameField.getText();
//            double totalBudget = Double.parseDouble(budgetField.getText());
//            dispose();
//            new MainMenuPage(firstName, lastName, totalBudget); // Pass the data to MainMenuPage
//        });


//        gbc.gridx = 0; gbc.gridy = 0;
//        panel.add(firstNameLabel, gbc);


//        gbc.gridx = 1; gbc.gridy = 0;
//        panel.add(firstNameField, gbc);


//        gbc.gridx = 0; gbc.gridy = 1;
//        panel.add(lastNameLabel, gbc);


//        gbc.gridx = 1; gbc.gridy = 1;
//        panel.add(lastNameField, gbc);


//        gbc.gridx = 0; gbc.gridy = 2;
//        panel.add(budgetLabel, gbc);


//        gbc.gridx = 1; gbc.gridy = 2;
//        panel.add(budgetField, gbc);


//        gbc.gridx = 0; gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        panel.add(BtnContinue, gbc);


//        add(panel);
//        setVisible(true);
//    }


//    private JTextField createTextField() {
//        JTextField textField = new JTextField();
//        textField.setPreferredSize(new Dimension(200, 25));
//        return textField;
//    }


//    private JTextField createTextField(String text) {
//        JTextField textField = new JTextField(text);
//        textField.setPreferredSize(new Dimension(200, 25));
//        return textField;
//    }


//    private JButton createStyledButton(String text, Dimension size) {
//        JButton button = new JButton(text);
//        button.setFont(new Font("Roboto", Font.PLAIN, 14));
//        button.setBackground(Color.decode("#DEDEDE"));
//        button.setOpaque(true);
//        button.setForeground(Color.BLACK);
//        button.setFocusPainted(false);
//        button.setBorder(BorderFactory.createLineBorder(Color.decode("#DEDEDE")));
//        button.setPreferredSize(size);
//        return button;
//    }
// }
