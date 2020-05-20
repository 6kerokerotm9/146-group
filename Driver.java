import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class Driver {
    public static void main(String [] args) {
        JFrame frame  = new JFrame();
        ProfileManager manager = new ProfileManager();
        Profile temp = new Profile("qr", "ts");
        temp.setName("skree", "");
        manager.addProfile(temp);
        temp = new Profile("ea", "sp");
        temp.setName("aeris", "");
        manager.addProfile(temp);
        temp = new Profile("ff", "ds");
        //temp.setName("my guy", "");
        manager.addProfile(temp);
        
        View view = new View(manager);
        frame.setContentPane(view);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }
}
