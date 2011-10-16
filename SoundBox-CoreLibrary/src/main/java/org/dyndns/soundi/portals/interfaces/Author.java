package org.dyndns.soundi.portals.interfaces;

/**
 * This is the class where some informations about the autor of the plugin is 
 * stored. For example, first name, last name...
 * 
 * @version 0.0.1
 * @author oli
 */
public class Author {
    private String forename, lastname;

    public Author(String forename, String lastname)
    {
        this.forename = forename;
        this.lastname = lastname;
    }
    
    /**
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
