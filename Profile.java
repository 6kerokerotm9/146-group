import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Profile
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

    } // end default constructor/**
    
    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
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
    } // end setProfilePicture
    /** Sets the name associated with the profile to the given name.
     * @param firstName  The first name for the profile.
     * @param lastName   The last name for the profile. */
    public void setName(String firstName, String lastName)
    {
        name = firstName + " " + lastName;
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
    } // end addFriend
    /** Removes a friend from the profile's list of friendProfiles.@param p  The profile to be removed from the list.       @return  True if the profile was removed. */
    public boolean removeFriend(Profile p)
    {
        return friendProfiles.remove(p);
    } // end removeFriend
    public String toString()
    {
        return username + " " + password; //remember to fix this later
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
   
}// end Profile