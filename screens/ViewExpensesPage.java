package screens;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ViewExpensesPage extends JFrame {
    public ViewExpensesPage(String firstName, String lastName, double totalBudget) {
        setTitle("View Expenses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new BorderLayout());

        // Table data
        String[] columns = {"Date", "Name", "Category", "Amount"};
        ArrayList<String[]> data = MainMenuPage.expenses;
        String[][] tableData = data.toArray(new String[0][]);

        JTable table = new JTable(tableData, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        // Calculate total expenses and remaining budget
        double totalExpenses = 0;
        for (String[] row : data) {
            totalExpenses += Double.parseDouble(row[3]);
        }
        double remainingBudget = totalBudget - totalExpenses;

        // Bottom panel for budget
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(224, 247, 250));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel totalBudgetLabel = new JLabel("Total Budget: $" + totalBudget);
        JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $" + remainingBudget);
        totalBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        remainingBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        // Panel for the Main Menu button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(224, 247, 250));

        JButton BtnMainMenu = new JButton("Main Menu");
        BtnMainMenu.setFont(new Font("Roboto", Font.PLAIN, 14));
        BtnMainMenu.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget);
        });

        buttonPanel.add(BtnMainMenu);

        // Add labels and button to the bottom panel
        bottomPanel.add(totalBudgetLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(remainingBudgetLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        bottomPanel.add(buttonPanel);

        // Add components to the main panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}


// package screens;

// import java.awt.*;
// import java.util.*;
// import javax.swing.*;

// public class ViewExpensesPage extends JFrame {
//     private JPanel tablePanel;
//     private String firstName;
//     private String lastName;
//     private double totalBudget;

//     public ViewExpensesPage(String firstName, String lastName, double totalBudget) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.totalBudget = totalBudget;

//         setTitle("View Expenses");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(500, 400);
//         setLocationRelativeTo(null);

//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(224, 247, 250));
//         panel.setLayout(new BorderLayout());

//         // Title label
//         JLabel titleLabel = new JLabel("View Expenses", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

//         // Table placeholder
//         tablePanel = new JPanel();
//         tablePanel.setBackground(new Color(224, 247, 250));
//         tablePanel.setPreferredSize(new Dimension(500, 200));
//         showExpensesTable();

//         // Bottom panel for navigation
//         JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//         bottomPanel.setBackground(new Color(224, 247, 250));

//         JButton BtnMainMenu = new JButton("Main Menu");
//         BtnMainMenu.setFont(new Font("Roboto", Font.PLAIN, 14));
//         BtnMainMenu.addActionListener(e -> {
//             dispose();
//             new MainMenuPage(firstName, lastName, totalBudget);
//         });

//         bottomPanel.add(BtnMainMenu);

//         // Add components to the panel
//         panel.add(titleLabel, BorderLayout.NORTH);
//         panel.add(tablePanel, BorderLayout.CENTER);
//         panel.add(bottomPanel, BorderLayout.SOUTH);

//         add(panel);
//         setVisible(true);
//     }

//     private void showExpensesTable() {
//         tablePanel.removeAll();

//         // Sort expenses by date
//         java.util.List<String[]> sortedExpenses = new ArrayList<>(MainMenuPage.expenses);
//         sortedExpenses.sort(Comparator.comparing(expense -> expense[0])); // Sort by date (MM/DD/YYYY)

//         // Convert sorted expenses to table data
//         String[] columns = {"Date", "Name", "Category", "Amount"};
//         String[][] data = new String[sortedExpenses.size()][4];
//         int row = 0;
//         for (String[] expense : sortedExpenses) {
//             data[row] = expense;
//             row++;
//         }

//         JTable table = new JTable(data, columns);
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setPreferredSize(new Dimension(500, 200));
//         tablePanel.add(scrollPane);
//         tablePanel.revalidate();
//         tablePanel.repaint();
//     }
// }


// package screens;

// import java.awt.*;
// import java.util.ArrayList;
// import javax.swing.*;

// public class ViewExpensesPage extends JFrame {
//     public ViewExpensesPage(String firstName, String lastName, double totalBudget) {
//         setTitle("View Expenses");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(500, 400);
//         setLocationRelativeTo(null);

//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(224, 247, 250));
//         panel.setLayout(new BorderLayout());

//         // Table data
//         String[] columns = {"Date", "Name", "Category", "Amount"};
//         ArrayList<String[]> data = MainMenuPage.expenses;
//         String[][] tableData = data.toArray(new String[0][]);

//         JTable table = new JTable(tableData, columns);
//         JScrollPane scrollPane = new JScrollPane(table);

//         // Calculate total expenses and remaining budget
//         double totalExpenses = 0;
//         for (String[] row : data) {
//             totalExpenses += Double.parseDouble(row[3]);
//         }
//         double remainingBudget = totalBudget - totalExpenses;

//         // Bottom panel for budget
//         JPanel bottomPanel = new JPanel();
//         bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
//         bottomPanel.setBackground(new Color(224, 247, 250));
//         bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         JLabel totalBudgetLabel = new JLabel("Total Budget: $" + totalBudget);
//         JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $" + remainingBudget);
//         totalBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));
//         remainingBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));

//         // Panel for the Main Menu button
//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//         buttonPanel.setBackground(new Color(224, 247, 250));

//         JButton BtnMainMenu = new JButton("Main Menu");
//         BtnMainMenu.setFont(new Font("Roboto", Font.PLAIN, 14));
//         BtnMainMenu.addActionListener(e -> {
//             dispose();
//             new MainMenuPage(firstName, lastName, totalBudget);
//         });

//         buttonPanel.add(BtnMainMenu);

//         // Add labels and button to the bottom panel
//         bottomPanel.add(totalBudgetLabel);
//         bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         bottomPanel.add(remainingBudgetLabel);
//         bottomPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//         bottomPanel.add(buttonPanel);

//         // Add components to the main panel
//         panel.add(scrollPane, BorderLayout.CENTER);
//         panel.add(bottomPanel, BorderLayout.SOUTH);

//         add(panel);
//         setVisible(true);
//     }
// }