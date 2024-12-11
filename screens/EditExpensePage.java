package screens;

import java.awt.*;
import javax.swing.*;

public class EditExpensePage extends JFrame {
    private String firstName;
    private String lastName;
    private double totalBudget;

    public EditExpensePage(String firstName, String lastName, double totalBudget, int expenseIndex, String date,
            String name, String category, String amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalBudget = totalBudget;
        setTitle("Edit Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Edit Expense", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        // Gray panel for editable fields
        JPanel editPanel = new JPanel();
        // editPanel.setBackground(Color.decode("#E5E5E5"));
        editPanel.setLayout(new GridBagLayout());
        GridBagConstraints editGbc = new GridBagConstraints();
        editGbc.insets = new Insets(5, 5, 5, 5);
        editGbc.fill = GridBagConstraints.HORIZONTAL;

        // Editable fields
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = createTextField();

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = createTextField();

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = createTextField();

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = createTextField("15.00");

        // Add fields to the gray panel
        editGbc.gridx = 0;
        editGbc.gridy = 0;
        editPanel.add(dateLabel, editGbc);

        editGbc.gridx = 1;
        editPanel.add(dateField, editGbc);

        editGbc.gridx = 0;
        editGbc.gridy = 1;
        editPanel.add(nameLabel, editGbc);

        editGbc.gridx = 1;
        editPanel.add(nameField, editGbc);

        editGbc.gridx = 0;
        editGbc.gridy = 2;
        editPanel.add(categoryLabel, editGbc);

        editGbc.gridx = 1;
        editPanel.add(categoryField, editGbc);

        editGbc.gridx = 0;
        editGbc.gridy = 3;
        editPanel.add(amountLabel, editGbc);

        editGbc.gridx = 1;
        editPanel.add(amountField, editGbc);

        // Buttons
        Dimension buttonSize = new Dimension(210, 40); // Adjust button width
        JButton BtnCancel = createStyledButton("Cancel", buttonSize);
        JButton BtnSave = createStyledButton("Save Changes", buttonSize);

        // Button functionality
        BtnCancel.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget); // Pass the required data
        });

        BtnSave.addActionListener(e -> {
            String updatedDate = dateField.getText().trim();
            String updatedName = nameField.getText().trim();
            String updatedCategory = categoryField.getText().trim();
            String updatedAmount = amountField.getText().trim();

            if (!updatedDate.isEmpty() && !updatedName.isEmpty() && !updatedCategory.isEmpty()
                    && !updatedAmount.isEmpty()) {
                try {
                    double amountValue = Double.parseDouble(updatedAmount);
                    if (amountValue <= 0)
                        throw new NumberFormatException();

                    // Update the expense in the list
                    MainMenuPage.expenses.set(expenseIndex,
                            new String[] { updatedDate, updatedName, updatedCategory, updatedAmount });

                    JOptionPane.showMessageDialog(this, "Expense updated successfully!");
                    dispose();
                    new MainMenuPage(firstName, lastName, totalBudget);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Amount must be a positive number.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        panel.add(editPanel, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(BtnCancel, gbc);

        gbc.gridx = 1;
        panel.add(BtnSave, gbc);

        add(panel);
        setVisible(true);
    }

    private JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setPreferredSize(new Dimension(200, 25));
        return textField;

    }

    private JButton createStyledButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setBackground(Color.decode("#ECECEC"));
        button.setOpaque(true);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#ECECEC")));
        button.setPreferredSize(size);
        return button;
    }
}
