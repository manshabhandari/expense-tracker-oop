package screens;

import java.awt.*;
import javax.swing.*;

public class AddExpensePage extends JFrame {

    private String firstName;
    private String lastName;
    private double totalBudget;

    public AddExpensePage(String firstName, String lastName, double totalBudget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalBudget = totalBudget;

        setTitle("Add Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField(15); // Set columns for consistent sizing

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(15);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField("15.00", 15);

        // Buttons
        JButton BtnSave = createStyledButton("Save");
        JButton BtnCancel = createStyledButton("Cancel");

        BtnSave.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget); // Navigate back to Main Menu
        });

        BtnCancel.addActionListener(e -> {
            dispose(); // Close current window
            new MainMenuPage(firstName, lastName, totalBudget); // Navigate to MainMenuPage
        });

        // Adding components to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(dateLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(dateField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(categoryLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(categoryField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(amountLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(amountField, gbc);

        // Add the Save and Cancel buttons aligned to text fields
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(BtnSave, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(BtnCancel, gbc);

        add(panel);
        setVisible(true);
    }

    // Helper method for consistent button styling
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setBackground(new Color(192, 192, 192));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192))); // No outline
        return button;
    }
}
