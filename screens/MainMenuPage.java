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


       // Welcome message
       JLabel welcomeLabel = new JLabel("Welcome, " + firstName + " " + lastName + "!");
       welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 16));
       gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
       panel.add(welcomeLabel, gbc);


       // Buttons for all menu options
       Dimension buttonSize = new Dimension(200, 40);
       JButton BtnAddExpense = createStyledButton("Add Expense", buttonSize);
       JButton BtnEditExpense = createStyledButton("Edit Expense", buttonSize);
       JButton BtnSearchByName = createStyledButton("Search by Name", buttonSize);
       JButton BtnViewExpenses = createStyledButton("View Expenses", buttonSize);
       JButton BtnViewInsights = createStyledButton("View Insights", buttonSize);
       JButton BtnExport = createStyledButton("Export to CSV", buttonSize);
       JButton BtnClose = createStyledButton("Close Tracker", buttonSize);


       // Adding buttons to the panel in a grid-like layout
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


       // Add action listeners for all buttons
       BtnAddExpense.addActionListener(e -> {
           dispose(); // Close current window
           new AddExpensePage(firstName, lastName, totalBudget); // Navigate to AddExpensePage
       });


       BtnViewExpenses.addActionListener(e -> {
           dispose(); // Close current window
           new ViewExpensesPage(firstName, lastName, totalBudget); // Pass totalBudget to ViewExpensesPage
       });


       BtnEditExpense.addActionListener(e -> {
           dispose(); // Close current window
           new EditExpensePage(firstName, lastName, totalBudget); // Navigate to EditExpensePage
       });


       BtnSearchByName.addActionListener(e -> {
           dispose(); // Close current window
           new SearchByNamePage(firstName, lastName, totalBudget); // Navigate to SearchByNamePage
       });


       BtnViewInsights.addActionListener(e -> {
           dispose(); // Close current window
           new ViewInsightsPage(firstName, lastName, totalBudget); // Navigate to ViewInsightsPage
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
           // Header
           writer.append("Name: ").append(firstName).append(" ").append(lastName).append(",,,");
           writer.append("Expense Data,,,Data Generated: ").append(currentDate).append("\n");
           writer.append("Monthly Budget: ,,,").append(String.valueOf(totalBudget)).append("\n\n");


           // Columns
           writer.append("Date,Name,Category,Amount\n");


           // Expense data
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



