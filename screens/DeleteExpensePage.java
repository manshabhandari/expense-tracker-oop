package screens;

import java.awt.*;
import javax.swing.*;

public class DeleteExpensePage extends JFrame {

    public DeleteExpensePage() {
        setTitle("Delete Expense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500); // Increased size for better layout
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Delete Expense", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        // Input field
        JLabel idLabel = new JLabel("Enter ID:");
        JTextField idField = createTextField();

        // Table placeholder for displaying results
        String[] columns = {"ID", "Name", "Category", "Amount"};
        JTable table = new JTable(new Object[0][4], columns); // Empty table initially
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150)); // Increased size

        // Buttons
        JButton BtnCancel = createStyledButton("Cancel");
        JButton BtnSearch = createStyledButton("Search");

        // Button functionality
        BtnCancel.addActionListener(e -> {
            dispose();
            new MainMenuPage();
        });

        BtnSearch.addActionListener(e -> {
            // TODO: Implement search functionality to populate the table
        });

        // Adding components to the panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(idLabel, gbc);

        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        gbc.gridy = 3; gbc.gridwidth = 1;
        panel.add(BtnCancel, gbc);

        gbc.gridx = 1;
        panel.add(BtnSearch, gbc);

        add(panel);
        setVisible(true);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 25)); // Lengthened size for better usability
        return textField;
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
