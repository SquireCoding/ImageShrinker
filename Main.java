import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        String inputDirectoryPath = "InputImages"; // Replace with your actual input directory
        String outputDirectoryPath = "OutputImages"; // Replace with your desired output directory

        File inputDirectory = new File(inputDirectoryPath);
        File[] pngFiles = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        for (File pngFile : pngFiles) {
            System.out.println("Working on "+pngFile.getName());
            try {
                BufferedImage originalImage = ImageIO.read(pngFile);
                BufferedImage resizedImage = resizeImage(originalImage, 500, 500);

                String outputFileName = pngFile.getName().replace(".png", "Small.png");
                File outputFile = new File(outputDirectoryPath, outputFileName);
                ImageIO.write(resizedImage, "PNG", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();

        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();

        return resizedImage;
    }
}
