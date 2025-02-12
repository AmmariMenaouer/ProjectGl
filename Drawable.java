package gui;

import java.awt.Graphics;

/**
 * Interface representing a drawable object.
 * Any class that implements this interface should provide an implementation for the draw method.
 */
public interface Drawable {
    /**
     * Draws the object using the provided Graphics context.
     * 
     * @param g the Graphics context to use for drawing
     */
    void draw(Graphics g);
}