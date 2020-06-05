package org.game.framework.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Files {
    public BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке картинки");
        }
        return image;
    }

    public File loadFile(String path) {
        return new File(path);
    }

    public Properties loadProperties(String path) {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(path));
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении properties");
        }
        return null;
    }
}
