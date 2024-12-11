package screens;


import java.awt.*;
import javax.swing.*;


public class WelcomePage extends JFrame {


   public WelcomePage() {
       setTitle("Welcome");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(750, 500);
       setLocationRelativeTo(null);


       // Main panel with GridBagLayout
       JPanel panel = new JPanel();
       panel.setBackground(new Color(224, 247, 250));
       panel.setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.insets = new Insets(10, 10, 10, 10); // Padding
       gbc.fill = GridBagConstraints.HORIZONTAL;


       // Title label
       JLabel titleLabel = new JLabel("Expense Tracker", SwingConstants.CENTER);
       titleLabel.setFont(new Font("Roboto", Font.BOLD, 16));


       // Welcome label
       JLabel welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
       welcomeLabel.setFont(new Font("Roboto", Font.PLAIN, 14));


       // Get Started button
       Dimension buttonSize = new Dimension(200, 40);
       JButton BtnGetStarted = createStyledButton("Get Started", buttonSize);
       BtnGetStarted.addActionListener(e -> {
           dispose();
           new SignUpPage(); // Navigate to the SignUp screen
       });


       // Adding components to the panel
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 2; // Span two columns for center alignment
       panel.add(titleLabel, gbc);


       gbc.gridy = 1;
       panel.add(welcomeLabel, gbc);


       gbc.gridy = 2;
       panel.add(BtnGetStarted, gbc);


       add(panel);
       setVisible(true);
   }


   // Helper method for consistent button styling
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



