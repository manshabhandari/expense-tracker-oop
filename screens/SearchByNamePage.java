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


       setTitle("Search By Name");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(750, 500);
       setLocationRelativeTo(null);

       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;

       JLabel titleLabel = new JLabel("Search By Name", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));

       JLabel searchLabel = new JLabel("Name:");
       searchLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
       searchField = new JTextField(15);

       Dimension buttonSize = new Dimension(210, 40);
       JButton BtnSearch = createStyledButton("Search", buttonSize);
       JButton BtnMainMenu = createStyledButton("Main Menu", buttonSize);

       tablePanel = new JPanel();
       tablePanel.setBackground(Color.WHITE);
       tablePanel.setPreferredSize(new Dimension(500, 200));

       BtnSearch.addActionListener(e -> searchEntriesByName());
       BtnMainMenu.addActionListener(e -> {
           dispose();
           new MainMenuPage(firstName, lastName, totalBudget);
       });

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

   //method to search the entries by name
   private void searchEntriesByName() {
       tablePanel.removeAll();
       tablePanel.setBackground(new Color(224, 247, 250));

       String searchName = searchField.getText().trim();
       if (searchName.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Please enter a name to search.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
       }

       java.util.List<String[]> filteredExpenses = new ArrayList<>();
       for (String[] expense : MainMenuPage.expenses) {
           if (expense[1].equalsIgnoreCase(searchName)) {
               filteredExpenses.add(expense);
           }
       }

       if (filteredExpenses.isEmpty()) {
           JOptionPane.showMessageDialog(this, "No entries found for the name: " + searchName, "Info", JOptionPane.INFORMATION_MESSAGE);
           return;
       }

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