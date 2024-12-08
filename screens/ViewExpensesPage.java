package screens;

import java.awt.*;
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
        String[][] data = {
            {"12/01/2024", "Trader Joe's", "Groceries", "34.72"},
            {"12/02/2024", "CVS", "Health", "6.41"},
            {"12/03/2024", "AMC", "Entertainment", "23.94"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        // Calculate total expenses and remaining budget
        double totalExpenses = 0;
        for (String[] row : data) {
            totalExpenses += Double.parseDouble(row[3].replace("$", ""));
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
            new MainMenuPage(firstName, lastName, totalBudget); // Navigate back with the correct data
        });

        buttonPanel.add(BtnMainMenu);

        // Add labels and button to the bottom panel
        bottomPanel.add(totalBudgetLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        bottomPanel.add(remainingBudgetLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
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
//         String[][] data = {
//             {"12/01/2024", "Trader Joe's", "Groceries", "34.72"},
//             {"12/02/2024", "CVS", "Health", "6.41"},
//             {"12/03/2024", "AMC", "Entertainment", "23.94"}
//         };

//         JTable table = new JTable(data, columns);
//         JScrollPane scrollPane = new JScrollPane(table);

//         // Calculate total expenses and remaining budget
//         double totalExpenses = 0;
//         for (String[] row : data) {
//             totalExpenses += Double.parseDouble(row[3].replace("$", ""));
//         }
//         double remainingBudget = totalBudget - totalExpenses;

//         // Bottom panel for budget and Main Menu button
//         JPanel bottomPanel = new JPanel();
//         bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
//         bottomPanel.setBackground(new Color(224, 247, 250));
//         bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         JLabel totalBudgetLabel = new JLabel("Total Budget: $" + totalBudget);
//         JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $" + remainingBudget);
//         totalBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));
//         remainingBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));

//         JButton BtnMainMenu = new JButton("Main Menu");
//         BtnMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
//         BtnMainMenu.addActionListener(e -> {
//             dispose();
//             new MainMenuPage(firstName, lastName, totalBudget); // Navigate back with the correct data
//         });

//         // Add labels and button to the bottom panel
//         bottomPanel.add(totalBudgetLabel);
//         bottomPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
//         bottomPanel.add(remainingBudgetLabel);
//         bottomPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
//         bottomPanel.add(BtnMainMenu);

//         // Add components to the main panel
//         panel.add(scrollPane, BorderLayout.CENTER);
//         panel.add(bottomPanel, BorderLayout.SOUTH);

//         add(panel);
//         setVisible(true);
//     }
// }
