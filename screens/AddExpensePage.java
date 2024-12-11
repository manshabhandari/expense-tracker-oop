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


       // Main panel with GridBagLayout
       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;


       // Labels and text fields
       JLabel dateLabel = new JLabel("Date:");
       JTextField dateField = new JTextField(15);


       JLabel nameLabel = new JLabel("Name:");
       JTextField nameField = new JTextField(15);


       JLabel categoryLabel = new JLabel("Category:");
       JTextField categoryField = new JTextField(15);


       JLabel amountLabel = new JLabel("Amount:");
       JTextField amountField = new JTextField(15);


       // Buttons
       Dimension buttonSize = new Dimension(80, 35); // Adjust button width
       JButton BtnSave = createStyledButton("Save", buttonSize);
       JButton BtnCancel = createStyledButton("Cancel", buttonSize);


       BtnSave.addActionListener(e -> {
           String date = dateField.getText().trim();
           String name = nameField.getText().trim();
           String category = categoryField.getText().trim();
           String amountText = amountField.getText().trim();


           // Validate fields
           if (date.isEmpty() || name.isEmpty() || category.isEmpty() || amountText.isEmpty()) {
               JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }


           // Validate date format
           if (!isValidDate(date)) {
               JOptionPane.showMessageDialog(this, "Date must be in MM/DD/YYYY format.", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }


           try {
               double amount = Double.parseDouble(amountText);


               // Validate positive amount
               if (amount <= 0) {
                   JOptionPane.showMessageDialog(this, "Amount must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                   return;
               }


               // Add expense to the shared list
               MainMenuPage.expenses.add(new String[]{date, name, category, String.format("%.2f", amount)});


               JOptionPane.showMessageDialog(this, "Expense added successfully!");
               dispose();
               new MainMenuPage(firstName, lastName, totalBudget);
           } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(this, "Amount must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
           }
       });


       BtnCancel.addActionListener(e -> {
           dispose();
           new MainMenuPage(firstName, lastName, totalBudget);
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


       gbc.gridx = 0; gbc.gridy = 4;
       panel.add(BtnSave, gbc);


       gbc.gridx = 1; gbc.gridy = 4;
       panel.add(BtnCancel, gbc);


       add(panel);
       setVisible(true);
   }


   // Helper method for consistent button styling
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


   // Helper method to validate date format and content
   private boolean isValidDate(String date) {
       // First, check if the date is in MM/DD/YYYY format
       String datePattern = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[0-1])/\\d{4}$";
       if (!Pattern.matches(datePattern, date)) {
           return false;
       }


       // Parse month, day, and year
       String[] parts = date.split("/");
       int month = Integer.parseInt(parts[0]);
       int day = Integer.parseInt(parts[1]);
       int year = Integer.parseInt(parts[2]);


       // Validate days for each month
       if (month == 2) { // February
           if (isLeapYear(year)) {
               return day <= 29; // Leap year: 29 days max
           } else {
               return day <= 28; // Non-leap year: 28 days max
           }
       } else if (month == 4 || month == 6 || month == 9 || month == 11) {
           return day <= 30; // April, June, September, November: 30 days max
       } else {
           return day <= 31; // All other months: 31 days max
       }
   }


   // Helper method to check if a year is a leap year
   private boolean isLeapYear(int year) {
       // Leap year rule: divisible by 4 and (not divisible by 100 or divisible by 400)
       return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
   }


   }

