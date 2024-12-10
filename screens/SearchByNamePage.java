package screens;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class SearchByNamePage extends JFrame {

    private JPanel tablePanel;
    private JTextField searchField;
    private String firstName;
    private String lastName;
    private double totalBudget;

    public SearchByNamePage(String firstName, String lastName, double totalBudget) {
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
        JLabel titleLabel = new JLabel("Search By Name", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        // Search field
        JLabel searchLabel = new JLabel("Name:");
        searchLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        searchField = new JTextField(15);

        JButton BtnSearch = createStyledButton("Search");
        JButton BtnMainMenu = createStyledButton("Main Menu");

        // Table placeholder
        tablePanel = new JPanel();
        tablePanel.setBackground(new Color(224, 247, 250));
        tablePanel.setPreferredSize(new Dimension(500, 200));

        // Button actions
        BtnSearch.addActionListener(e -> searchEntriesByName());
        BtnMainMenu.addActionListener(e -> {
            dispose();
            new MainMenuPage(firstName, lastName, totalBudget); // Navigate to Main Menu
        });

        // Adding components to the panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(searchLabel, gbc);

        gbc.gridx = 1;
        panel.add(searchField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(BtnSearch, gbc);

        gbc.gridy = 3;
        panel.add(tablePanel, gbc);

        gbc.gridy = 4;
        panel.add(BtnMainMenu, gbc);

        add(panel);
        setVisible(true);
    }

    private void searchEntriesByName() {
        tablePanel.removeAll();

        String searchName = searchField.getText().trim();
        if (searchName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Filter expenses by name
        java.util.List<String[]> filteredExpenses = new ArrayList<>();
        for (String[] expense : MainMenuPage.expenses) {
            if (expense[1].equalsIgnoreCase(searchName)) {
                filteredExpenses.add(expense);
            }
        }

        // Check if no entries match
        if (filteredExpenses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No entries found for the name: " + searchName, "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Convert filtered entries to table data
        String[] columns = {"Date", "Name", "Category", "Amount"};
        String[][] data = new String[filteredExpenses.size()][4];
        for (int i = 0; i < filteredExpenses.size(); i++) {
            data[i] = filteredExpenses.get(i);
        }

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

// public class SearchByNamePage extends JFrame {
//     private String firstName;
//     private String lastName;
//     private double totalBudget;

//     public SearchByNamePage(String firstName, String lastName, double totalBudget) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.totalBudget = totalBudget;
//         setTitle("Search by Name");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(600, 500);
//         setLocationRelativeTo(null);

//         // Main panel with GridBagLayout
//         JPanel panel = new JPanel();
//         panel.setBackground(new Color(224, 247, 250));
//         panel.setLayout(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10); // Padding
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Title
//         JLabel titleLabel = new JLabel("Search by Name", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

//         // Input field
//         JLabel nameLabel = new JLabel("Enter Name:");
//         JTextField nameField = createTextField();

//         // Table placeholder for displaying results
//         String[] columns = {"Date", "Name", "Category","Amount"};
//         JTable table = new JTable(new Object[0][4], columns); // Empty table initially
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setPreferredSize(new Dimension(500, 150)); // Increased size for alignment

//         // Button
//         JButton BtnMainMenu = createStyledButton("Main Menu");

//         // Button functionality
//         BtnMainMenu.addActionListener(e -> {
//             dispose();
//             new MainMenuPage(firstName, lastName, totalBudget); // Pass the required data
//         });


//         // Adding components to the panel
//         gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
//         panel.add(titleLabel, gbc);

//         gbc.gridy = 1; gbc.gridwidth = 1;
//         panel.add(nameLabel, gbc);

//         gbc.gridx = 1;
//         panel.add(nameField, gbc);

//         gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
//         panel.add(scrollPane, gbc);

//         gbc.gridy = 3; gbc.gridwidth = 2;
//         panel.add(BtnMainMenu, gbc);

//         add(panel);
//         setVisible(true);
//     }

//     private JTextField createTextField() {
//         JTextField textField = new JTextField();
//         textField.setPreferredSize(new Dimension(300, 25)); // Lengthened size for better usability
//         return textField;
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
