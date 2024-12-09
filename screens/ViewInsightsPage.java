package screens;


import java.awt.*;
import java.util.*;
import javax.swing.*;


public class ViewInsightsPage extends JFrame {


   private JPanel tablePanel;
   private String firstName;
   private String lastName;
   private double totalBudget;


   public ViewInsightsPage(String firstName, String lastName, double totalBudget) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.totalBudget = totalBudget;


       setTitle("View Insights");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(750, 500); // Ensure appropriate initial window size
       setLocationRelativeTo(null);


       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;


       // Title
       JLabel titleLabel = new JLabel("View Insights", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));


       // Buttons
       Dimension buttonSize = new Dimension(210, 40); // Slightly wider width
       JButton BtnByMonth = createStyledButton("By Month", buttonSize);
       JButton BtnByCategory = createStyledButton("By Category", buttonSize);
       JButton BtnMainMenu = createStyledButton("Main Menu", buttonSize);


       // Button actions
       BtnByMonth.addActionListener(e -> showTableByMonth());
       BtnByCategory.addActionListener(e -> showTableByCategory());
       BtnMainMenu.addActionListener(e -> {
           dispose();
           new MainMenuPage(firstName, lastName, totalBudget); // Pass the required data
       });


       // Table placeholder
       tablePanel = new JPanel();
       tablePanel.setBackground(Color.WHITE); // White box as a filler
       tablePanel.setPreferredSize(new Dimension(500, 200));


       // Adding components to the panel
       gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
       panel.add(titleLabel, gbc);


       gbc.gridy = 1; gbc.gridwidth = 1;
       gbc.gridx = 0;
       panel.add(BtnByMonth, gbc);


       gbc.gridx = 1;
       panel.add(BtnByCategory, gbc);


       gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
       panel.add(tablePanel, gbc);


       gbc.gridy = 3;
       panel.add(BtnMainMenu, gbc);


       add(panel);
       setVisible(true);
   }


   private void showTableByMonth() {
       tablePanel.removeAll();
       tablePanel.setBackground(new Color(224, 247, 250)); // Reset to match parent background


       // Calculate totals by month
       Map<String, Double> totalsByMonth = new LinkedHashMap<>();
       for (String[] expense : MainMenuPage.expenses) {
           String date = expense[0];
           double amount = Double.parseDouble(expense[3]);
           String month = getMonthName(date.split("/")[0]); // Extract month name
           totalsByMonth.put(month, totalsByMonth.getOrDefault(month, 0.0) + amount);
       }


       // Convert totals to table data
       String[] columns = {"Month", "Total Spent"};
       String[][] data = new String[totalsByMonth.size()][2];
       int row = 0;
       for (Map.Entry<String, Double> entry : totalsByMonth.entrySet()) {
           data[row][0] = entry.getKey();
           data[row][1] = String.format("$%.2f", entry.getValue());
           row++;
       }


       JTable table = new JTable(data, columns);
       JScrollPane scrollPane = new JScrollPane(table);
       scrollPane.setPreferredSize(new Dimension(500, 150));
       tablePanel.add(scrollPane);
       tablePanel.revalidate();
       tablePanel.repaint();
   }


   private void showTableByCategory() {
       tablePanel.removeAll();
       tablePanel.setBackground(new Color(224, 247, 250)); // Reset to match parent background


       // Calculate totals by category
       Map<String, Double> totalsByCategory = new LinkedHashMap<>();
       for (String[] expense : MainMenuPage.expenses) {
           String category = expense[2]; // Category is already normalized in AddExpensePage
           double amount = Double.parseDouble(expense[3]);
           totalsByCategory.put(category, totalsByCategory.getOrDefault(category, 0.0) + amount);
       }


       // Convert totals to table data
       String[] columns = {"Category", "Total Spent"};
       String[][] data = new String[totalsByCategory.size()][2];
       int row = 0;
       for (Map.Entry<String, Double> entry : totalsByCategory.entrySet()) {
           data[row][0] = entry.getKey();
           data[row][1] = String.format("$%.2f", entry.getValue());
           row++;
       }


       JTable table = new JTable(data, columns);
       JScrollPane scrollPane = new JScrollPane(table);
       scrollPane.setPreferredSize(new Dimension(500, 150));
       tablePanel.add(scrollPane);
       tablePanel.revalidate();
       tablePanel.repaint();
   }


   private String getMonthName(String monthNumber) {
       switch (monthNumber) {
           case "01":
               return "January";
           case "02":
               return "February";
           case "03":
               return "March";
           case "04":
               return "April";
           case "05":
               return "May";
           case "06":
               return "June";
           case "07":
               return "July";
           case "08":
               return "August";
           case "09":
               return "September";
           case "10":
               return "October";
           case "11":
               return "November";
           case "12":
               return "December";
           default:
               return "Unknown";
       }
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

