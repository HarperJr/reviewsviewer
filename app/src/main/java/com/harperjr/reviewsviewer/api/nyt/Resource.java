
package com.harperjr.reviewsviewer.api.nyt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("width")
    @Expose
    private int width;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Resource() {
    }

    /**
     * 
     * @param height
     * @param width
     * @param src
     * @param type
     */
    public Resource(String type, String src, int height, int width) {
        super();
        this.type = type;
        this.src = src;
        this.height = height;
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
