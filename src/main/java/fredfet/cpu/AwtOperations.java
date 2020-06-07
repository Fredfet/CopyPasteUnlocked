package fredfet.cpu;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class AwtOperations {
    private static final int SCALE_FOR_SCREENSHOTS = 4;
    private Robot robot;

    public AwtOperations() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private String getClipboardContent() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String removeDiacriticsAndOtherUnwantedChars(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFKD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replace('ł', 'l').replace('Ł', 'L');
    }

    public void pasteClipboardByTyping() {
        String toType = removeDiacriticsAndOtherUnwantedChars(getClipboardContent());
        for (char c : toType.toCharArray()) {
            pressInOrder(KeyValues.getKeyValuesForChar(c), 0);
        }
    }

    private void pressInOrder(int[] keysToPress, int startFrom) {
        if (keysToPress.length > startFrom) {
            robot.keyPress(keysToPress[startFrom]);
            pressInOrder(keysToPress, startFrom + 1);
            robot.keyRelease(keysToPress[startFrom]);
        }
    }

    public List<BufferedImage> getResizedScreenShots() {
        List<BufferedImage> allScreenShots = new ArrayList<>();
//        Rectangle bounds = new Rectangle();
        GraphicsDevice[] graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        for (GraphicsDevice device : graphicsDevices) {
            Rectangle deviceBounds = device.getDefaultConfiguration().getBounds();
//            bounds = bounds.union(deviceBounds);
            allScreenShots.add(resizeScreen(robot.createScreenCapture(deviceBounds), SCALE_FOR_SCREENSHOTS));
        }
//        return resizeScreen(robot.createScreenCapture(bounds), SCALE_FOR_SCREENSHOTS);
        return allScreenShots;
    }

    public void setSystemClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private BufferedImage resizeScreen(BufferedImage input, int scale) {
        BufferedImage output = new BufferedImage(scale * input.getWidth(), scale * input.getHeight(), input.getType());
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(scale, scale);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        return affineTransformOp.filter(input, output);
    }
}
