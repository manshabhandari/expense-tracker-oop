package screens;

import java.awt.*;
import javax.swing.*;

public class SearchByNamePage extends JFrame {

    public SearchByNamePage() {
        setTitle("Search by Name");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Main panel with GridBagLayout
        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 247, 250));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Search by Name", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

        // Input field
        JLabel nameLabel = new JLabel("Enter Name:");
        JTextField nameField = createTextField();

        // Table placeholder for displaying results
        String[] columns = {"ID", "Name", "Category","Total Spent"};
        JTable table = new JTable(new Object[0][4], columns); // Empty table initially
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150)); // Increased size for alignment

        // Button
        JButton BtnMainMenu = createStyledButton("Main Menu");

        // Button functionality
        BtnMainMenu.addActionListener(e -> {
            dispose();
            new MainMenuPage();
        });

        // Adding components to the panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);

        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(BtnMainMenu, gbc);

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
