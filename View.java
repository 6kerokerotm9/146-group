import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class View extends JPanel implements Observer{
    private ProfileManager manager;
    private Profile current;
    
    public View(ProfileManager manager) {
        super();
        this.manager = manager;
        //current = null;
        current = manager.login("ff", "ds");
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(current == null) { //starts at the login screen
            loginScreen();
        }
        else {
            this.removeAll(); //clear out the panel for new components
            profile();
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
                if(current != null) { //make sure that an account is found for the details passed by the user
                    manager.update();
                }
            }
        });
        signup.addActionListener(new ActionListener() { //event if the signup button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = username.getText();
                String second_temp = password.getText();
                username.setText(""); //empty out the textfields
                password.setText("");
                if(!(temp.isEmpty() || second_temp.isEmpty())) { //as long as both fields are not empty
                    manager.addProfile(new Profile(temp, second_temp));
                    manager.update();
                }
            }
        });
    }
    
    public void profile() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //needs to show name of user first
        showFriends(); //then have the friends output as well
        
        JButton add_name = new JButton("Add Name");
        JButton change_status = new JButton("Add/Change Status");
        JButton add_picture = new JButton("Add Profile Picture");
        JTextField search_field = new JTextField(); //fix this for me dude
        search_field.setMaximumSize(new Dimension(300, 20));
        search_field.setLocation(325, 10);
        JButton search = new JButton("Search");
        JButton leave = new JButton("Leave Network");
        
        this.add(change_status);
        this.add(add_name);
        this.add(add_picture);
        this.add(search);
        this.add(search_field);
        this.add(leave);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile temp = manager.search(search_field.getText()); //gets the profile that the user looks for
                if(temp != null) { //if the account is found
                    results(temp);
                    manager.update();
                }
            }
        });
        
        change_status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStatus();
            }
        });
        
        add_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputName();
            }
        });
        
        add_picture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPicture();
            }
        });
        
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete here");
            }
        });
    }
    
    public void results(Profile profile) { //function that adds friends to profile
        JFrame frame = new JFrame(); //create a new window for the information to appear on
        frame.add(new JLabel(profile.toString()));
        JButton addFriend = new JButton("Add Friend");
        frame.add(addFriend);
        addFriend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current.addFriend(profile);
                manager.createFriendship(current, profile);
                manager.update();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //close the window after status is set
                //manager.print(); //test function
            }
        });
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }
    
    public void changeStatus() {
        JFrame frame = new JFrame(); //create a new window for the information to appear on
        JTextField status = new JTextField();
        status.setPreferredSize(new Dimension(300, 20));
        JButton addStatus = new JButton("Change Status");
        frame.add(status);
        frame.add(addStatus);
        addStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current.setStatus(status.getText());
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //close the window after status is set
            }
        });
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }
    
    public void inputName() {
        JFrame frame = new JFrame(); //create a new window for the information to appear on
        JTextField first = new JTextField();
        JTextField last = new JTextField();
        first.setPreferredSize(new Dimension(300, 20));
        last.setPreferredSize(new Dimension(300, 20));
        JButton addName = new JButton("Change Name");
        frame.add(first);
        frame.add(last);
        frame.add(addName);
        addName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current.setName(first.getText(), last.getText());
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //close the window after status is set
            }
        });
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }
    
    public void showFriends() {
        String output = "";
        for(Profile p : current.getFriends()) {
            output += p + "\n";
        }
        JLabel friends = new JLabel(output);
        System.out.println(output);
    }
    
    public void setPicture() {
        JFrame frame = new JFrame(); //create a new window for the information to appear on
        JRadioButton spartan = new JRadioButton("spartan"); //add four radio buttons to let the user pick the picture that they want
        JRadioButton java = new JRadioButton("java");
        JRadioButton dijkstra = new JRadioButton("dijkstra");
        JRadioButton tree = new JRadioButton("tree");
        
        class Action implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                BufferedImage image;
                try {
                    image = ImageIO.read(new File(e.getActionCommand() + ".jpg")); //use functions to find image files
                    current.setProfilePicture(image);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                }
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //close the window after status is set
            }
        };
        
        spartan.addActionListener(new Action()); //add action of choosing the image to each of the buttons
        java.addActionListener(new Action());
        dijkstra.addActionListener(new Action());
        tree.addActionListener(new Action());
        
        frame.add(spartan);
        frame.add(java);
        frame.add(dijkstra);
        frame.add(tree);
        
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
