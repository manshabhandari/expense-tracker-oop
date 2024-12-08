package screens;

import java.awt.*;
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
        JButton BtnByMonth = createStyledButton("By Month");
        JButton BtnByCategory = createStyledButton("By Category");
        JButton BtnMainMenu = createStyledButton("Main Menu");

        // Force equal button sizes
        Dimension buttonSize = new Dimension(150, 40);
        BtnByMonth.setPreferredSize(buttonSize);
        BtnByCategory.setPreferredSize(buttonSize);

        // Button actions
        BtnByMonth.addActionListener(e -> showTableByMonth());
        BtnByCategory.addActionListener(e -> showTableByCategory());
        BtnMainMenu.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget); // Pass the required data
        });

        // Table placeholder
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(224, 247, 250));
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
        String[] columns = {"Month", "Total Spent"};
        String[][] data = {
            {"November", "$34.72"},
            {"December", "$30.35"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        tablePanel.add(scrollPane);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void showTableByCategory() {
        tablePanel.removeAll();
        String[] columns = {"Category", "Total Spent"};
        String[][] data = {
            {"Groceries", "$34.72"},
            {"Health", "$6.41"},
            {"Entertainment", "$23.94"}
        };

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        tablePanel.add(scrollPane);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 14));
        button.setBackground(new Color(192, 192, 192));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192)));
        return button;
    }
}


// package screens;

// import java.awt.*;
// import javax.swing.*;

// public class ViewInsightsPage extends JFrame {

//     private JPanel tablePanel;
//     private String firstName;
//     private String lastName;
//     private double totalBudget;

//     public ViewInsightsPage(String firstName, String lastName, double totalBudget) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.totalBudget = totalBudget;

//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(224, 247, 250));
//         panel.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Title
//         JLabel titleLabel = new JLabel("View Insights", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

//         // Buttons
//         JButton BtnByMonth = createStyledButton("By Month");
//         JButton BtnByCategory = createStyledButton("By Category");
//         JButton BtnMainMenu = createStyledButton("Main Menu");

//         // Enforce equal size for buttons
//         Dimension buttonSize = new Dimension(150, 30);
//         BtnByMonth.setPreferredSize(buttonSize);
//         BtnByMonth.setMinimumSize(buttonSize);
//         BtnByMonth.setMaximumSize(buttonSize);

//         BtnByCategory.setPreferredSize(buttonSize);
//         BtnByCategory.setMinimumSize(buttonSize);
//         BtnByCategory.setMaximumSize(buttonSize);

//         // Button actions
//         BtnByMonth.addActionListener(e -> showTableByMonth());
//         BtnByCategory.addActionListener(e -> showTableByCategory());
//         BtnMainMenu.addActionListener(e -> {
//             dispose();
//             new MainMenuPage(firstName, lastName, totalBudget); // Pass the required data
//         });

//         // Table placeholder
//         tablePanel = new JPanel();
//         tablePanel.setBackground(new Color(224, 247, 250));
//         tablePanel.setPreferredSize(new Dimension(500, 200));

//         // Adding components to the panel
//         gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
//         panel.add(titleLabel, gbc);

//         gbc.gridy = 1; gbc.gridwidth = 1;
//         panel.add(BtnByMonth, gbc);

//         gbc.gridx = 1;
//         panel.add(BtnByCategory, gbc);

//         gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
//         panel.add(tablePanel, gbc);

//         gbc.gridy = 3;
//         panel.add(BtnMainMenu, gbc);

//         add(panel);
//         setVisible(true);
//     }

//     private void showTableByMonth() {
//         tablePanel.removeAll();
//         String[] columns = {"Month", "Total Spent"};
//         String[][] data = {
//             {"November", "$34.72"},
//             {"December", "$30.35"}
//         };

//         JTable table = new JTable(data, columns);
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setPreferredSize(new Dimension(500, 150));
//         tablePanel.add(scrollPane);
//         tablePanel.revalidate();
//         tablePanel.repaint();
//     }

//     private void showTableByCategory() {
//         tablePanel.removeAll();
//         String[] columns = {"Category", "Total Spent"};
//         String[][] data = {
//             {"Groceries", "$34.72"},
//             {"Health", "$6.41"},
//             {"Entertainment", "$23.94"}
//         };

//         JTable table = new JTable(data, columns);
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setPreferredSize(new Dimension(500, 150));
//         tablePanel.add(scrollPane);
//         tablePanel.revalidate();
//         tablePanel.repaint();
//     }

//     private JButton createStyledButton(String text) {
//         JButton button = new JButton(text);
//         button.setFont(new Font("Roboto", Font.PLAIN, 14));
//         button.setBackground(new Color(192, 192, 192));
//         button.setForeground(Color.BLACK);
//         button.setFocusPainted(false);
//         button.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192)));
//         return button;
//     }
// }
