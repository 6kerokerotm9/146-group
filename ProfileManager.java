import java.awt.image.*;

/**An implementation of a profile manager on a simple social network.
 * @author Jesse Grabowski
 * @author Joseph Erickson
 * @version 5.0 */
public class ProfileManager
{
    private UndirectedGraph<Profile> allProfiles;
    /** Constructor for an instance of a profile manager. */
    public ProfileManager()
    {
        allProfiles = new UndirectedGraph<>();
    } // end default constructor
    /** Adds a profile to the social network.@param p  The profile to be added to the network. */
    public void addProfile(Profile p)
    {
        allProfiles.addVertex(p);
    } // end addProfile
    public void createFriendship(Profile a, Profile b)
    {

    } // end createFriendship
    /** Displays each profile's information and friends. */
    public void display(Profile startPoint)
    {

    } // end display
} // end ProfileManager