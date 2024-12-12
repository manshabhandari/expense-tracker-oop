package screens;

import java.awt.*;
import java.util.regex.Pattern;
import javax.swing.*;

public class EditExpensePage extends JFrame {
    private String firstName;
    private String lastName;
    private double totalBudget;

    public EditExpensePage(String firstName, String lastName, double totalBudget, int expenseIndex, String date, String name, String category, String amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalBudget = totalBudget;

        setTitle("Edit Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Edit Expense", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridBagLayout());
        GridBagConstraints editGbc = new GridBagConstraints();
        editGbc.insets = new Insets(5, 5, 5, 5);
        editGbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = createTextField(date);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = createTextField(name);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = createTextField(category);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = createTextField(amount);

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

        Dimension buttonSize = new Dimension(210, 40);
        JButton BtnCancel = createStyledButton("Cancel", buttonSize);
        JButton BtnSave = createStyledButton("Save Changes", buttonSize);

        //Button to cancel editing an expense
        BtnCancel.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget);
        });

        //Button to save changes to an expense 
        BtnSave.addActionListener(e -> {
            String updatedDate = dateField.getText().trim();
            String updatedName = nameField.getText().trim();
            String updatedCategory = categoryField.getText().trim();
            String updatedAmount = amountField.getText().trim();
        
            if (updatedDate.isEmpty() || updatedName.isEmpty() || updatedCategory.isEmpty() || updatedAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            String dateValidationResult = isValidDate(updatedDate);
            if (!dateValidationResult.equals("VALID")) {
                JOptionPane.showMessageDialog(this, dateValidationResult, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            try {
                double amountValue = Double.parseDouble(updatedAmount);
        
                if (amountValue <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                MainMenuPage.expenses.set(expenseIndex, new String[]{updatedDate, updatedName, updatedCategory, String.format("%.2f", amountValue)});
        
                JOptionPane.showMessageDialog(this, "Expense updated successfully!");
                dispose();
                new MainMenuPage(firstName, lastName, totalBudget);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
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

    //method to validate the date entered by the user
    private String isValidDate(String date) {
        String datePattern = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])/\\d{4}$";
        if (!Pattern.matches(datePattern, date)) {
            return "Date must be in MM/DD/YYYY format.";
        }
    
        String[] parts = date.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
    
        if (year < 2021 || year > 2026) {
            return "Year not in reasonable range.";
        }
    
        if (month == 2) { 
            if (isLeapYear(year) && day > 29) {
                return "Invalid date for February in a leap year.";
            } else if (!isLeapYear(year) && day > 28) {
                return "Invalid date for February in a non-leap year.";
            }
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return "Invalid date for the selected month.";
        } else if (day > 31) {
            return "Invalid date for the selected month.";
        }
    
        return "VALID";
    }
    
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }
}