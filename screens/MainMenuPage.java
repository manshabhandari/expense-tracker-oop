package screens;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class MainMenuPage extends JFrame {
   private String firstName;
   private String lastName;
   private double totalBudget;
   public static ArrayList<String[]> expenses = new ArrayList<>();
   private int expenseIndex;
   private String date;
   private String name;
   private String category;
   private String amount;

   public MainMenuPage(String firstName, String lastName, double totalBudget) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.totalBudget = totalBudget;

       setTitle("Main Menu");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(750, 500);
       setLocationRelativeTo(null);

       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;

       JLabel welcomeLabel = new JLabel("Welcome, " + firstName + " " + lastName + "!");
       welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 16));
       gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
       panel.add(welcomeLabel, gbc);

       Dimension buttonSize = new Dimension(200, 40);
       JButton BtnAddExpense = createStyledButton("Add Expense", buttonSize);
       JButton BtnEditExpense = createStyledButton("Edit Expense", buttonSize);
       JButton BtnSearchByName = createStyledButton("Search by Name", buttonSize);
       JButton BtnViewExpenses = createStyledButton("View Expenses", buttonSize);
       JButton BtnViewInsights = createStyledButton("View Insights", buttonSize);
       JButton BtnExport = createStyledButton("Export to CSV", buttonSize);
       JButton BtnClose = createStyledButton("Close Tracker", buttonSize);

       gbc.gridx = 0; gbc.gridy = 1;
       gbc.gridwidth = 1;
       panel.add(BtnAddExpense, gbc);

       gbc.gridx = 1; gbc.gridy = 1;
       panel.add(BtnViewExpenses, gbc);

       gbc.gridx = 0; gbc.gridy = 2;
       panel.add(BtnSearchByName, gbc);

       gbc.gridx = 1; gbc.gridy = 2;
       panel.add(BtnViewInsights, gbc);

       gbc.gridx = 0; gbc.gridy = 3;
       panel.add(BtnExport, gbc);

       gbc.gridx = 1; gbc.gridy = 3;
       panel.add(BtnClose, gbc);

       add(panel);
       setVisible(true);

       BtnAddExpense.addActionListener(e -> {
           dispose();
           new AddExpensePage(firstName, lastName, totalBudget);
       });

       BtnViewExpenses.addActionListener(e -> {
           dispose();
           new ViewExpensesPage(firstName, lastName, totalBudget);
       });

       BtnEditExpense.addActionListener(e -> {
        dispose();
        new EditExpensePage(firstName, lastName, totalBudget, expenseIndex, date, name, category, amount);
    });

       BtnSearchByName.addActionListener(e -> {
           dispose();
           new SearchByNamePage(firstName, lastName, totalBudget);
       });

       BtnViewInsights.addActionListener(e -> {
           dispose();
           new ViewInsightsPage(firstName, lastName, totalBudget);
       });

       BtnExport.addActionListener(e -> {
           exportToCSV();
       });

       BtnClose.addActionListener(e -> System.exit(0));
   }

   private void exportToCSV() {
       SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
       String currentDate = dateFormat.format(new Date());

       String fileName = "Expenses.csv";

       try (FileWriter writer = new FileWriter(fileName)) {
           writer.append("Name: ").append(firstName).append(" ").append(lastName).append(",,,");
           writer.append("Expense Data,,,Data Generated: ").append(currentDate).append("\n");
           writer.append("Monthly Budget: ,,,").append(String.valueOf(totalBudget)).append("\n\n");
           writer.append("Date,Name,Category,Amount\n");

           for (String[] expense : expenses) {
               writer.append(String.join(",", expense)).append("\n");
           }

           JOptionPane.showMessageDialog(this, "Expenses exported successfully to " + fileName, "Success", JOptionPane.INFORMATION_MESSAGE);
       } catch (IOException e) {
           JOptionPane.showMessageDialog(this, "Error exporting expenses: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       }
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