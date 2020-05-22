import java.awt.image.*;
import java.util.ArrayList;
import java.util.Observable;

/**An implementation of a profile manager on a simple social network.
 * @author Jesse Grabowski
 * @author Joseph Erickson
 * @version 5.0 */
public class ProfileManager extends Observable
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
        //allProfiles.print(); //remove this eventually
    } // end addProfile
    public void createFriendship(Profile a, Profile b)
    {
        allProfiles.makeEdge(a, b);
    } // end createFriendship
    //searches the graph for all profiles and returns the profile with the login information if found
    public Profile login(String username, String password) {
        ArrayList<Profile> temp = allProfiles.getVertices();
        for(Profile p : temp) {
            if(p.checkLogin(username, password)) {
                return p;
            }
        }
        return null;
    }
    
    public Profile search(String name) {
        ArrayList<Profile> temp = allProfiles.getVertices();
        for(Profile p : temp) {
            if(p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }
    
    public void removeFriend(Profile p) {
        for(Profile f : allProfiles.getVertices()) {
            f.removeFriend(p);
        }
        allProfiles.remove(p);
    }
    
    public ArrayList<Profile> getFriends(Profile p) {
        return allProfiles.getEdges(p);
    }
    
    public String edges(Profile p) {
        return allProfiles.printEdges(p);
    }
    
    //function that updates the gui when the user interacts with the components
    public void update() {
        super.setChanged(); //notify the observers that the data changed
        super.notifyObservers();
    }
} // end ProfileManager