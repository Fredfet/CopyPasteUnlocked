package fredfet.cpu;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShortcutActions {
    private final KeysState keysState;
    private final AwtOperations awtOperations;
    private final TesseractOperations tesseractOperations;

    public ShortcutActions(KeysState keysState) {
        this.keysState = keysState;
        awtOperations = new AwtOperations();
        tesseractOperations = new TesseractOperations();
    }

    public void checkState() {
        if (keysState.isPasteShortcutActive()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            awtOperations.pasteClipboardByTyping();
        }

        if (keysState.isCopyShortcutActive()) {
            List<BufferedImage> screenShots = awtOperations.getResizedScreenShots();
            String textFromScreens = tesseractOperations.getTextFromImages(screenShots);
            awtOperations.setSystemClipboard(textFromScreens);
        }

        if (keysState.isExitShortcutActive()) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }
}
