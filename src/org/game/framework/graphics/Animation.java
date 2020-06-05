package org.game.framework.graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private int x;
    private int y;

    private BufferedImage[] frames;
    public int currentFrame;

    private long startTime;
    private long delay;

    private int timesPlayed;

    public Animation(BufferedImage[] images, long delay) {
        frames = images;
        this.delay = delay;
        currentFrame = 0;
        timesPlayed = 0;
    }

    public Animation() {
        delay = -1;
        currentFrame = 0;
        timesPlayed = 0;
    }

    public void setFrames(BufferedImage[] images) {
        frames = images;
        if(currentFrame >= frames.length) currentFrame = 0;
    }


    public void setDelay(long d) {
        delay = d;
    }

    public void update() {

        if(delay == -1) {
            timesPlayed = 0;
            return;
        }

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length) {
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public BufferedImage getImage() {
        if(delay != -1) return frames[currentFrame];
        else return null;
    }

    public boolean oncePlayed() { return timesPlayed > 0; }
    public boolean hasPlayed(int times) {
        return timesPlayed >= times;
    }

    public long getDelay() { return delay; }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}

