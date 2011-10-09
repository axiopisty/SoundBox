package org.dyndns.soundi.portals.interfaces;

/**
 * This is the class which contains all informations about a specific plugin.
 * 
 * @version 0.0.1
 * @author oli
 */
public class PluginInformation {
    private String pluginName;
    private Author author;
    private License license;
    
    /**
     * 
     * @param pluginName
     * @param author
     * @param license
     */
    public PluginInformation(String pluginName, Author author, License license)
    {
        this.pluginName = pluginName;
        this.author = author;
        this.license = license;
    }

    /**
     * @return the pluginName
     */
    public String getPluginName() {
        return pluginName;
    }

    /**
     * @param pluginName the pluginName to set
     */
    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the license
     */
    public License getLicense() {
        return license;
    }

    /**
     * @param license the license to set
     */
    public void setLicense(License license) {
        this.license = license;
    }
    
}
