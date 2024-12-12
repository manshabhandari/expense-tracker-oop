package screens;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewExpensesPage extends JFrame {

    public ViewExpensesPage(String firstName, String lastName, double totalBudget) {
        setTitle("View Expenses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("View Expenses", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        String[] columns = {"Date", "Name", "Category", "Amount"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        for (String[] expense : MainMenuPage.expenses) {
            tableModel.addRow(expense);
        }

        JTable expenseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(expenseTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        Dimension buttonSize = new Dimension(210, 40);
        JButton BtnMainMenu = createStyledButton("Main Menu", buttonSize);
        JButton BtnEditExpense = createStyledButton("Edit Expense", buttonSize);
        JButton BtnDeleteExpense = createStyledButton("Delete Expense", buttonSize);

        BtnEditExpense.setVisible(false);
        BtnDeleteExpense.setVisible(false);

        BtnMainMenu.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget); 
        });

        BtnEditExpense.addActionListener(e -> {
            int selectedRow = expenseTable.getSelectedRow();
            if (selectedRow >= 0) {
                dispose();
                String[] selectedExpense = MainMenuPage.expenses.get(selectedRow);
                new EditExpensePage(
                        firstName,
                        lastName,
                        totalBudget,
                        selectedRow,
                        selectedExpense[0],
                        selectedExpense[1],
                        selectedExpense[2],
                        selectedExpense[3]
                );
            } else {
                JOptionPane.showMessageDialog(this, "Please select an expense to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //method to delete an expense
        BtnDeleteExpense.addActionListener(e -> {
            int selectedRow = expenseTable.getSelectedRow();
            if (selectedRow >= 0) {
                MainMenuPage.expenses.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Expense deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an expense to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //method to redirect to the edit expense page along with the necessary data
        expenseTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && expenseTable.getSelectedRow() >= 0) {
                BtnEditExpense.setVisible(true);
                BtnDeleteExpense.setVisible(true);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 1));
        bottomPanel.setBackground(new Color(224, 247, 250));

        JLabel totalBudgetLabel = new JLabel("Total Budget: $" + totalBudget);
        JLabel totalSpentLabel = new JLabel("Total Spent: $" + calculateTotalSpent());
        JLabel remainingBudgetLabel = new JLabel("Remaining Budget: $" + (totalBudget - calculateTotalSpent()));

        totalBudgetLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        totalSpentLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        remainingBudgetLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        bottomPanel.add(totalBudgetLabel);
        bottomPanel.add(totalSpentLabel);
        bottomPanel.add(remainingBudgetLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(BtnEditExpense, gbc);

        gbc.gridx = 1;
        panel.add(BtnDeleteExpense, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(BtnMainMenu, gbc);

        gbc.gridy = 4;
        panel.add(bottomPanel, gbc);

        add(panel);
        setVisible(true);

        showBudgetDialog(totalBudget);
    }

    private void showBudgetDialog(double totalBudget) {
        double totalSpent = calculateTotalSpent();
        String message;
        if (totalSpent > totalBudget) {
            message = "You are over budget by $" + String.format("%.2f", totalSpent - totalBudget) + ".";
        } else {
            message = "You are under budget by $" + String.format("%.2f", totalBudget - totalSpent) + ".";
        }

        JOptionPane.showMessageDialog(this, message, "Budget Status", JOptionPane.INFORMATION_MESSAGE);
    }

    private double calculateTotalSpent() {
        double totalSpent = 0.0;
        for (String[] expense : MainMenuPage.expenses) {
            totalSpent += Double.parseDouble(expense[3]);
        }
        return totalSpent;
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