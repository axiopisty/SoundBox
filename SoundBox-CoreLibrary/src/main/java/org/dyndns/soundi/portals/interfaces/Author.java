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

    /**
     * Constructs a new Author Object
     * @param forename the forename of the author
     * @param lastname the lastname of the author
     */
    public Author(String forename, String lastname)
    {
        this.forename = forename;
        this.lastname = lastname;
    }
    
    /**
     * This method returns the forename of the author
     * @return the forename of the author
     */
    public String getForename() {
        return forename;
    }

    /** 
     * This method sets the forename of the author
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * This method returns the lastname of the author
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method sets the lastname of the author
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
