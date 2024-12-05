package screens;

import java.awt.*;
import javax.swing.*;

public class ViewExpensesPage extends JFrame {
    public ViewExpensesPage() {
        setTitle("View Expenses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new BorderLayout());

        String[] columns = {"Date", "Name", "Category", "Amount"};
        String[][] data = {
            {"12/01/2024", "Trader Joe's", "Groceries", "$34.72"},
            {"12/02/2024", "CVS", "Health", "$6.41"},
            {"12/03/2024", "AMC", "Entertainment", "$23.94"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(e -> {
            dispose();
            new MainMenuPage();
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(mainMenuButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
