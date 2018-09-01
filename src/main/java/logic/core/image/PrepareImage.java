package logic.core.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrepareImage {
    private BufferedImage image;

    public PrepareImage(File sourceImage) {
        try {
            this.image = ImageIO.read(sourceImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSnippingImage(String newImageName, int startPositionX, int startPositionY, int width, int height) throws IOException {
        if (image == null) throw new NullPointerException("error to load source image");
        BufferedImage newImage = image.getSubimage(startPositionX, startPositionY, width, height);
        File output = new File(newImageName);
        for (int i = 0; i < ImageExtension.values().length; i++) {
            StringBuilder ext = new StringBuilder(".");
            String extension = ext.append(ImageExtension.values()[i].getValue()).toString();
            if (newImageName.contains(extension)) {
                ImageIO.write(newImage, String.valueOf(ImageExtension.values()[i].getValue()), output);
                break;
            }
        }
    }

    public List<String> splitImageToRows(String newImageName, int rowHeight) throws IOException {
        List<String> rows = new ArrayList<String>();
        int counterParts = (int) Math.floor((float) image.getHeight() / rowHeight);
        for (int i = 0; i < counterParts; i++) {
            String namePart = newImageName.replaceAll("\\.", "-" + i + ".");
            createSnippingImage(namePart, 0, i * rowHeight, image.getWidth(), rowHeight);
            rows.add(namePart);
        }
        return rows;
    }

    //Next methods return size in pixels.
    public float getSourceImageHeigh() {
        return image.getHeight();
    }

    public float getSourceImageWidth() {
        return image.getWidth();
    }

}
