package org.game.framework.util;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private boolean[] keys = new boolean[256];
    private boolean[] releasedKeys = new boolean[256];

    private boolean[] buttons = new boolean[5];
    private boolean[] releasedButtons = new boolean[5];
    private int mouseX = 0, mouseY = 0;

    public void updateInput() {
        for (int i = 0; i < 256; i++) {
            releasedKeys[i] = false;
        }
        for (int i = 0; i < 5; i++) {
            releasedButtons[i] = false;
        }
    }

    public boolean isKeyDown(int code) {
        return keys[code];
    }

    public boolean isKeyUp(int code) {
        return releasedKeys[code];
    }

    public boolean isMouseDown(int code) {
        return buttons[code];
    }

    public boolean isMouseUp(int code) {
        return releasedButtons[code];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        releasedKeys[e.getKeyCode()] = true;
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasedButtons[e.getButton()] = true;
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
