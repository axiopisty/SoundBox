package org.dyndns.soundi.portals.interfaces;

/**
 * This is the class that represents the license of the plugin.
 * 
 * @version 0.0.1
 * @author oli
 */
public class License {
    
    public License(String licenseText)
    {
        this.licenseText = licenseText;
    }
    
    /**
     * The text of the license.
     */
    private String licenseText;

    /**
     * This method returns the license Text.
     * @return the licenseText of this license.
     */
    public String getLicenseText() {
        return licenseText;
    }

    /**
     * This method sets the license text of this license.
     * @param licenseText the licenseText to set
     */
    public void setLicenseText(String licenseText) {
        this.licenseText = licenseText;
    }
}
