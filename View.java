import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

public class View extends JPanel implements Observer{
    private ProfileManager manager;
    private Profile current;
    private boolean first_run; //keeps track of the first time 
    
    public View(ProfileManager manager) {
        super();
        this.manager = manager;
        current = null;
        first_run = true;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(current == null) { //starts at the login screen
            loginScreen();
        }
        else {
            JButton temp = new JButton("temp");
            this.add(temp);
        }
        if(first_run == false) {
            this.revalidate();
        }
        manager.addObserver(this);
    }
    
    public void loginScreen() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //layout which aligns all components vertically
        
        JTextField username = new JTextField(); 
        username.setMaximumSize(new Dimension(300, 20)); //makes sure that the text field doesn't change in size with layout
        
        JTextField password = new JTextField();
        password.setMaximumSize(new Dimension(300, 20));
        
        JButton signin = new JButton("Sign In"); 
        JButton signup = new JButton("Sign Up");
        
        this.add(username);
        this.add(password);
        this.add(signin);
        this.add(signup);
        
        signin.addActionListener(new ActionListener() { //if the sign in button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = username.getText();
                String second_temp = password.getText();
                current = manager.login(temp, second_temp);
                if(current != null) {
                    manager.update();
                    first_run = false;
                }
            }
        });
        signup.addActionListener(new ActionListener() { //event if the signup button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = username.getText();
                String second_temp = password.getText();
                username.setText("");
                password.setText("");
                if(!(temp.isEmpty() || second_temp.isEmpty())) {
                    manager.addProfile(new Profile(temp, second_temp));
                    manager.update();
                    first_run = false;
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        this.removeAll();
        this.repaint();
    }
}
