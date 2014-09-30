package org.devdom.commons.type;

public enum FormatType {
    JSON(".json"),
    XML(".xml");
    
    private final String extension;

    FormatType(String extension) {
        this.extension = extension;
    }
    
    public String getExtension() {
        return extension;
    }
}
