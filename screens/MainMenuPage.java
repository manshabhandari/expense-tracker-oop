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
        JButton addExpenseButton = createStyledButton("Add Expense");
        JButton editExpenseButton = createStyledButton("Edit Expense");
        JButton deleteExpenseButton = createStyledButton("Delete Expense");
        JButton searchButton = createStyledButton("Search by Name");
        JButton viewExpensesButton = createStyledButton("View Expenses");
        JButton viewInsightsButton = createStyledButton("View Insights");
        JButton exportButton = createStyledButton("Export to CSV");
        JButton closeButton = createStyledButton("Close Tracker");

        // Adding buttons to the panel in a grid-like layout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(addExpenseButton, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(viewExpensesButton, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(editExpenseButton, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(viewInsightsButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(deleteExpenseButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(exportButton, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(searchButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(closeButton, gbc);

        add(panel);
        setVisible(true);

        addExpenseButton.addActionListener(e -> {
            dispose(); // Close current window
            new AddExpensePage(); // Navigate to AddExpensePage
        });

        viewExpensesButton.addActionListener(e -> {
            dispose(); // Close current window
            new ViewExpensesPage(); // Navigate to ViewExpensesPage
        });

        closeButton.addActionListener(e -> System.exit(0));
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
