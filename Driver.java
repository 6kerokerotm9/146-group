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
        Profile temp2 = new Profile("ea", "sp");
        temp2.setName("aeris", "g");
        manager.addProfile(temp);
        Profile temp3 = new Profile("rr", "da");
        temp3.setName("odd", "eyes");
        manager.addProfile(temp);
        Profile temp4 = new Profile("ff", "ds");
        //temp4.setName("my guy", "");
        manager.addProfile(temp);
        manager.addProfile(temp2);
        manager.addProfile(temp3);
        manager.addProfile(temp4);
        
        addFriends(manager, temp, temp2);
        addFriends(manager, temp2, temp3);
        addFriends(manager, temp, temp3);
        
        View view = new View(manager);
        frame.setContentPane(view);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }
    
    public static void addFriends(ProfileManager manager, Profile current, Profile profile) {
        manager.createFriendship(current, profile);
        current.addFriend(profile);
        manager.createFriendship(profile, current);
        profile.addFriend(current);
    }
}
