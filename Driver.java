import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

public class Driver {
    public static void main(String [] args) {
        JFrame frame  = new JFrame();
        ProfileManager manager = new ProfileManager();
        
        //preload some profiles
        ArrayList<Profile> temp = new ArrayList<>();
        temp.add(createProfile("qr", "ts", "cloud", "s", manager));
        temp.add(createProfile("ea", "sp", "aeris", "g", manager));
        temp.add(createProfile("rr", "da", "Nii", "ii", manager));
        temp.add(createProfile("ff", "ds", "four", "heads", manager));
        temp.add(createProfile("ls", "mm", "Sythiee", "s", manager));
        temp.add(createProfile("qr", "lm", "Drei", "set", manager));
                
        //create some friendships
        addFriends(manager, temp.get(0), temp.get(5));
        addFriends(manager, temp.get(2), temp.get(3));
        addFriends(manager, temp.get(0), temp.get(3));
        addFriends(manager, temp.get(4), temp.get(5));
        addFriends(manager, temp.get(0), temp.get(1));
        addFriends(manager, temp.get(1), temp.get(4));
        
        View view = new View(manager);
        frame.setContentPane(view);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(700, 700));
        frame.pack();
        frame.setLayout(new FlowLayout());
    }
    
    //function to create profiles to populate the network
    public static Profile createProfile(String username, String password, String name, String lastname, ProfileManager manager) {
        Profile profile = new Profile(username, password);
        profile.setName(name, lastname);
        manager.addProfile(profile);
        return profile;
    }
    
    //function to initialize friends for the pre constructed profiles
    public static void addFriends(ProfileManager manager, Profile current, Profile profile) {
        manager.createFriendship(current, profile);
        current.addFriend(profile);
        manager.createFriendship(profile, current);
        profile.addFriend(current);
    }
}
