package screens;

import java.awt.*;
import javax.swing.*;

public class MainMenuPage extends JFrame {

    public MainMenuPage() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Buttons for all 8 options
        JButton BtnAddExpense = createStyledButton("Add Expense");
        JButton BtnEditExpense = createStyledButton("Edit Expense");
        JButton BtnDeleteExpense = createStyledButton("Delete Expense");
        JButton BtnSearchByName = createStyledButton("Search by Name");
        JButton BtnViewExpenses = createStyledButton("View Expenses");
        JButton BtnViewInsights = createStyledButton("View Insights");
        JButton BtnExport = createStyledButton("Export to CSV");
        JButton BtnClose = createStyledButton("Close Tracker");

        // Adding buttons to the panel in a grid-like layout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(BtnAddExpense, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(BtnViewExpenses, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(BtnEditExpense, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(BtnViewInsights, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(BtnDeleteExpense, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(BtnExport, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(BtnSearchByName, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(BtnClose, gbc);

        add(panel);
        setVisible(true);

        BtnAddExpense.addActionListener(e -> {
            dispose(); // Close current window
            new AddExpensePage(); // Navigate to AddExpensePage
        });

        BtnViewExpenses.addActionListener(e -> {
            dispose(); // Close current window
            new ViewExpensesPage(); // Navigate to ViewExpensesPage
        });

        BtnDeleteExpense.addActionListener(e -> {
            dispose(); // Close current window
            new DeleteExpensePage(); // Navigate to ViewExpensesPage
        });

        BtnEditExpense.addActionListener(e -> {
            dispose(); // Close current window
            new EditExpensePage(); // Navigate to ViewExpensesPage
        });

        BtnSearchByName.addActionListener(e -> {
            dispose(); // Close current window
            new SearchByNamePage(); // Navigate to ViewExpensesPage
        });

        BtnViewInsights.addActionListener(e -> {
            dispose(); // Close current window
            new ViewInsightsPage(); // Navigate to ViewExpensesPage
        });

        BtnClose.addActionListener(e -> System.exit(0));
    }

    // Helper method for consistent button styling
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setBackground(new Color(192, 192, 192));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192))); // No outline
        return button;
    }
}
