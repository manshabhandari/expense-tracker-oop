package screens;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

        JTable table = new JTable(new DefaultTableModel(tableData, columns));
        JScrollPane scrollPane = new JScrollPane(table);

        // Calculate total expenses, total spent, and remaining budget
        double totalExpenses = 0;
        for (String[] row : data) {
            totalExpenses += Double.parseDouble(row[3]);
        }
        double remainingBudget = totalBudget - totalExpenses;

        // Bottom panel for budget and buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(224, 247, 250));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left panel for budget info
        JPanel budgetPanel = new JPanel();
        budgetPanel.setLayout(new BoxLayout(budgetPanel, BoxLayout.Y_AXIS));
        budgetPanel.setBackground(new Color(224, 247, 250));

        JLabel totalBudgetLabel = new JLabel("Total Budget: $" + totalBudget);
        JLabel totalSpentLabel = new JLabel("Total Spent: $" + totalExpenses);
        JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $" + remainingBudget);

        totalBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        totalSpentLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        remainingBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));

        budgetPanel.add(totalBudgetLabel);
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        budgetPanel.add(totalSpentLabel);
        budgetPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        budgetPanel.add(remainingBudgetLabel);

        // Right panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(224, 247, 250));

        JPanel editDeletePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        editDeletePanel.setBackground(new Color(224, 247, 250));

        JButton BtnEditExpense = new JButton("Edit Expense");
        JButton BtnDeleteExpense = new JButton("Delete Expense");
        BtnEditExpense.setFont(new Font("Roboto", Font.PLAIN, 14));
        BtnDeleteExpense.setFont(new Font("Roboto", Font.PLAIN, 14));
        BtnEditExpense.setVisible(false);
        BtnDeleteExpense.setVisible(false);

        BtnEditExpense.addActionListener(e -> {
            dispose();
            new EditExpensePage(firstName, lastName, totalBudget);
        });

        BtnDeleteExpense.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                data.remove(selectedRow);
                ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Expense deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an expense to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        editDeletePanel.add(BtnEditExpense);
        editDeletePanel.add(BtnDeleteExpense);

        JButton BtnMainMenu = new JButton("Main Menu");
        BtnMainMenu.setFont(new Font("Roboto", Font.PLAIN, 14));
        BtnMainMenu.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget);
        });

        buttonPanel.add(editDeletePanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(BtnMainMenu);

        // Add selection listener to the table
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                BtnEditExpense.setVisible(true);
                BtnDeleteExpense.setVisible(true);
            }
        });

        // Add panels to bottom panel
        bottomPanel.add(budgetPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        // Add components to the main panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}

// package screens;

// import java.awt.*;
// import java.util.ArrayList;
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;

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

//         JTable table = new JTable(new DefaultTableModel(tableData, columns));
//         JScrollPane scrollPane = new JScrollPane(table);

//         // Calculate total expenses, total spent, and remaining budget
//         double totalExpenses = 0;
//         for (String[] row : data) {
//             totalExpenses += Double.parseDouble(row[3]);
//         }
//         double remainingBudget = totalBudget - totalExpenses;

//         // Bottom panel for budget and buttons
//         JPanel bottomPanel = new JPanel();
//         bottomPanel.setLayout(new BorderLayout());
//         bottomPanel.setBackground(new Color(224, 247, 250));
//         bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//         // Left panel for budget info
//         JPanel budgetPanel = new JPanel();
//         budgetPanel.setLayout(new BoxLayout(budgetPanel, BoxLayout.Y_AXIS));
//         budgetPanel.setBackground(new Color(224, 247, 250));

//         JLabel totalBudgetLabel = new JLabel("Total Budget: $" + totalBudget);
//         JLabel totalSpentLabel = new JLabel("Total Spent: $" + totalExpenses);
//         JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $" + remainingBudget);

//         totalBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));
//         totalSpentLabel.setFont(new Font("Roboto", Font.BOLD, 14));
//         remainingBudgetLabel.setFont(new Font("Roboto", Font.BOLD, 14));

//         budgetPanel.add(totalBudgetLabel);
//         budgetPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         budgetPanel.add(totalSpentLabel);
//         budgetPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//         budgetPanel.add(remainingBudgetLabel);

//         // Right panel for buttons
//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//         buttonPanel.setBackground(new Color(224, 247, 250));

//         JButton BtnEditExpense = new JButton("Edit Expense");
//         JButton BtnDeleteExpense = new JButton("Delete Expense");
//         JButton BtnMainMenu = new JButton("Main Menu");

//         BtnEditExpense.setFont(new Font("Roboto", Font.PLAIN, 14));
//         BtnDeleteExpense.setFont(new Font("Roboto", Font.PLAIN, 14));
//         BtnMainMenu.setFont(new Font("Roboto", Font.PLAIN, 14));

//         BtnEditExpense.setVisible(false);
//         BtnDeleteExpense.setVisible(false);

//         BtnEditExpense.addActionListener(e -> {
//             dispose();
//             new EditExpensePage(firstName, lastName, totalBudget);
//         });

//         BtnDeleteExpense.addActionListener(e -> {
//             int selectedRow = table.getSelectedRow();
//             if (selectedRow >= 0) {
//                 data.remove(selectedRow);
//                 ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
//                 JOptionPane.showMessageDialog(this, "Expense deleted successfully.", "Info", JOptionPane.INFORMATION_MESSAGE);
//             } else {
//                 JOptionPane.showMessageDialog(this, "Please select an expense to delete.", "Error", JOptionPane.ERROR_MESSAGE);
//             }
//         });

//         BtnMainMenu.addActionListener(e -> {
//             dispose();
//             new MainMenuPage(firstName, lastName, totalBudget);
//         });

//         buttonPanel.add(BtnEditExpense);
//         buttonPanel.add(BtnDeleteExpense);
//         buttonPanel.add(BtnMainMenu);

//         // Add selection listener to the table
//         table.getSelectionModel().addListSelectionListener(event -> {
//             if (!event.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
//                 BtnEditExpense.setVisible(true);
//                 BtnDeleteExpense.setVisible(true);
//             }
//         });

//         // Add panels to bottom panel
//         bottomPanel.add(budgetPanel, BorderLayout.WEST);
//         bottomPanel.add(buttonPanel, BorderLayout.EAST);

//         // Add components to the main panel
//         panel.add(scrollPane, BorderLayout.CENTER);
//         panel.add(bottomPanel, BorderLayout.SOUTH);

//         add(panel);
//         setVisible(true);
//     }
// }