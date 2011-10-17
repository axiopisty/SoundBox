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
     * The constructor of the PluginInformation class, contained in the plugin.
     * @param pluginName The name of the plugin (this is shown in the default UI)
     * @param author The author class (forename, lastname).
     * @param license The license for that plugin.
     */
    public PluginInformation(String pluginName, Author author, License license)
    {
        this.pluginName = pluginName;
        this.author = author;
        this.license = license;
    }

    /**
     * This method returns the plugin name.
     * @return the plugin name 
     */
    public String getPluginName() {
        return pluginName;
    }

    /**
     * This method sets the plugin name.
     * @param pluginName the plugin name to set
     */
    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    /**
     * This method returns the author of the plugin.
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * This method sets the author of the plugin.
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * This method returns the license of the plugin.
     * @return the license of this plugin.
     */
    public License getLicense() {
        return license;
    }

    /**
     * This method sets the license of the plugin.
     * @param license the license to set
     */
    public void setLicense(License license) {
        this.license = license;
    }
    
}
