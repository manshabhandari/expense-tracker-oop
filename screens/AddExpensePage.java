package screens;

import java.awt.*;
import java.util.regex.Pattern;
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

       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;

       JLabel dateLabel = new JLabel("Date:");
       JTextField dateField = new JTextField(15);

       JLabel nameLabel = new JLabel("Name:");
       JTextField nameField = new JTextField(15);

       JLabel categoryLabel = new JLabel("Category:");
       JTextField categoryField = new JTextField(15);

       JLabel amountLabel = new JLabel("Amount:");
       JTextField amountField = new JTextField(15);

       Dimension buttonSize = new Dimension(80, 35);
       JButton BtnSave = createStyledButton("Save", buttonSize);
       JButton BtnCancel = createStyledButton("Cancel", buttonSize);
 
       //Button to validate and save the expense added by the user
       BtnSave.addActionListener(e -> {
        String date = dateField.getText().trim();
        String name = nameField.getText().trim();
        String category = categoryField.getText().trim();
        String amountText = amountField.getText().trim();

        if (date.isEmpty() || name.isEmpty() || category.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String dateValidationResult = isValidDate(date);
        if (!dateValidationResult.equals("VALID")) {
            JOptionPane.showMessageDialog(this, dateValidationResult, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MainMenuPage.expenses.add(new String[]{date, name, category, String.format("%.2f", amount)});
    
            JOptionPane.showMessageDialog(this, "Expense added successfully!");
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Amount must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

       //Button to cancel adding an expense
       BtnCancel.addActionListener(e -> {
           dispose();
           new MainMenuPage(firstName, lastName, totalBudget);
       });

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

       gbc.gridx = 0; gbc.gridy = 4;
       panel.add(BtnSave, gbc);

       gbc.gridx = 1; gbc.gridy = 4;
       panel.add(BtnCancel, gbc);

       add(panel);
       setVisible(true);
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

   //Method to validate the date entered by the user
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