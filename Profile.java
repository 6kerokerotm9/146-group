import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Profile extends Observable
{
    private BufferedImage picture;
    private String name;
    private String password;
    private String username;
    private String status;
    private ArrayList<Profile> friendProfiles;
    /** Constructor for an instance of a profile. */
    public Profile() 
    {
        friendProfiles = new ArrayList<Profile>();
        name = "";
        status = "";
        try {
            picture = ImageIO.read(new File("blank.jpg")); //add default profile picture to profiles when created
        } catch (IOException e) {
            System.out.println("Image not found.");
        }
    }
    // end default constructor/**
    
    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
        friendProfiles = new ArrayList<Profile>();
        name = "";
        status = "";
        try {
            picture = ImageIO.read(new File("blank.jpg")); //add default profile picture to profiles when created
        } catch (IOException e) {
            System.out.println("Image not found.");
        }
    }
    /** Returns the picture associated with the profile.@return  The profile's picture. */
    public BufferedImage getProfilePicture()
    {
        return picture;
    } // end getProfilePic
    /** Sets the picture associated with the profile to the given picture.@param newPicture  The new picture associated with the profile. */
    public void setProfilePicture(BufferedImage newPicture)
    {
        picture = newPicture;
        update();
        
    } // end setProfilePicture
    /** Sets the name associated with the profile to the given name.
     * @param firstName  The first name for the profile.
     * @param lastName   The last name for the profile. */
    public void setName(String firstName, String lastName)
    {
        name = firstName + " " + lastName;
        update();
        
    } // end setName
    /** Returns the name associated with the profile.
     * @return  The profile's name. */
    public String getName()
    {
        return name;
    } // end getName
    /** Sets the current status of the profile to the given string.@param stat  The new status for the profile. */
    public void setStatus(String stat)
    {
        status = stat;
        update();
    } // end setStatus
    /** Returns the status associated with the profile.@return  The profile's status.*/
    public String getStatus()
    {
        return status;
    } // end getStatus
    /** Returns a list of all the person's friendProfiles on the social network.@return  The list of friendProfiles. */
    public ArrayList<Profile> getFriends()
    {
        return friendProfiles;
    } // end getFriends
    /** Adds a friend to the profile's list of friendProfiles.@param p  The profile to be added to the list. */
    public void addFriend(Profile p)
    {
        friendProfiles.add(p);
        update();
    } // end addFriend
    /** Removes a friend from the profile's list of friendProfiles.@param p  The profile to be removed from the list.       @return  True if the profile was removed. */
    public boolean removeFriend(Profile p)
    {
        return friendProfiles.remove(p);
    } // end removeFriend
    public String toString()
    {
        return name; //remember to fix this later
    } // end toString
    /** Displays the profile's information and friendProfiles. */
    public void display()
    {
        
    } // end display
    //function that adds username and password to the account when signing in 
    public void setLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    //function that takes login information and returns true if login is sucessful
    public boolean checkLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public void update() { //function that updates the gui when values change in class
        super.setChanged(); //notify the observers that the data changed
        super.notifyObservers();
    }
}// end Profile