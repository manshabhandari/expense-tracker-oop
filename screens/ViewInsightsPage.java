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
       setSize(750, 500);
       setLocationRelativeTo(null);

       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;

       JLabel titleLabel = new JLabel("View Insights", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

       Dimension buttonSize = new Dimension(210, 40);
       JButton BtnByMonth = createStyledButton("By Month", buttonSize);
       JButton BtnByCategory = createStyledButton("By Category", buttonSize);
       JButton BtnMainMenu = createStyledButton("Main Menu", buttonSize);

       BtnByMonth.addActionListener(e -> showTableByMonth());
       BtnByCategory.addActionListener(e -> showTableByCategory());
       BtnMainMenu.addActionListener(e -> {
           dispose();
           new MainMenuPage(firstName, lastName, totalBudget);
       });

       tablePanel = new JPanel();
       tablePanel.setBackground(Color.WHITE); 
       tablePanel.setPreferredSize(new Dimension(500, 200));

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

   //method to display the data by month
   private void showTableByMonth() {
       tablePanel.removeAll();
       tablePanel.setBackground(new Color(224, 247, 250));

       Map<String, Double> totalsByMonth = new LinkedHashMap<>();
       for (String[] expense : MainMenuPage.expenses) {
           String date = expense[0];
           double amount = Double.parseDouble(expense[3]);
           String month = getMonthName(date.split("/")[0]);
           totalsByMonth.put(month, totalsByMonth.getOrDefault(month, 0.0) + amount);
       }

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

   //method to display the data by category
   private void showTableByCategory() {
       tablePanel.removeAll();
       tablePanel.setBackground(new Color(224, 247, 250));

       Map<String, Double> totalsByCategory = new LinkedHashMap<>();
       for (String[] expense : MainMenuPage.expenses) {
           String category = expense[2];
           double amount = Double.parseDouble(expense[3]);
           totalsByCategory.put(category, totalsByCategory.getOrDefault(category, 0.0) + amount);
       }

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

