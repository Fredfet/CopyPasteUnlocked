package fredfet.cpu;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class TesseractOperations {
    private final ITesseract tesseract;

    public TesseractOperations() {
        this.tesseract = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        tesseract.setDatapath(tessDataFolder.getAbsolutePath());
    }

    public String getTextFromImages(List<BufferedImage> screenShots) {
        StringBuilder textFromAllScreens = new StringBuilder();
        try {
            for(BufferedImage shot : screenShots) {
                textFromAllScreens.append(tesseract.doOCR(shot));
                textFromAllScreens.append("\n\n-------------SCREEN BREAK-------------\n\n");
            }
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return textFromAllScreens.toString();
    }
}
