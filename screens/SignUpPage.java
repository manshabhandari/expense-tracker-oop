package screens;


import java.awt.*;
import javax.swing.*;


public class SignUpPage extends JFrame {


   public SignUpPage() {
       setTitle("Sign Up");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(750, 500);
       setLocationRelativeTo(null);


       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10);
       gbc.fill = GridBagConstraints.HORIZONTAL;


       JLabel firstNameLabel = new JLabel("First Name:");
       JTextField firstNameField = createTextField();


       JLabel lastNameLabel = new JLabel("Last Name:");
       JTextField lastNameField = createTextField();


       JLabel budgetLabel = new JLabel("Monthly Budget:");
       JTextField budgetField = createTextField("200.00");


       Dimension buttonSize = new Dimension(200, 40);
       JButton BtnContinue = createStyledButton("Continue", buttonSize);
       BtnContinue.addActionListener(e -> {
           String firstName = firstNameField.getText();
           String lastName = lastNameField.getText();
           double totalBudget = Double.parseDouble(budgetField.getText());
           dispose();
           new MainMenuPage(firstName, lastName, totalBudget); // Pass the data to MainMenuPage
       });


       gbc.gridx = 0; gbc.gridy = 0;
       panel.add(firstNameLabel, gbc);


       gbc.gridx = 1; gbc.gridy = 0;
       panel.add(firstNameField, gbc);


       gbc.gridx = 0; gbc.gridy = 1;
       panel.add(lastNameLabel, gbc);


       gbc.gridx = 1; gbc.gridy = 1;
       panel.add(lastNameField, gbc);


       gbc.gridx = 0; gbc.gridy = 2;
       panel.add(budgetLabel, gbc);


       gbc.gridx = 1; gbc.gridy = 2;
       panel.add(budgetField, gbc);


       gbc.gridx = 0; gbc.gridy = 3;
       gbc.gridwidth = 2;
       panel.add(BtnContinue, gbc);


       add(panel);
       setVisible(true);
   }


   private JTextField createTextField() {
       JTextField textField = new JTextField();
       textField.setPreferredSize(new Dimension(200, 25));
       return textField;
   }


   private JTextField createTextField(String text) {
       JTextField textField = new JTextField(text);
       textField.setPreferredSize(new Dimension(200, 25));
       return textField;
   }


   private JButton createStyledButton(String text, Dimension size) {
       JButton button = new JButton(text);
       button.setFont(new Font("Roboto", Font.PLAIN, 14));
       button.setBackground(Color.decode("#DEDEDE"));
       button.setOpaque(true);
       button.setForeground(Color.BLACK);
       button.setFocusPainted(false);
       button.setBorder(BorderFactory.createLineBorder(Color.decode("#DEDEDE")));
       button.setPreferredSize(size);
       return button;
   }
}
