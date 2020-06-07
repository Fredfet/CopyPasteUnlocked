package fredfet.cpu;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class CopyPasteUnlocked implements NativeKeyListener {
    private static KeysState keysState;
    private static ShortcutActions shortcutActions;

    public static void main(String[] args) {
        keysState = new KeysState();
        shortcutActions = new ShortcutActions(keysState);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new CopyPasteUnlocked());
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        keysState.addKey(e.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        keysState.removeKey(e.getKeyCode());
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        shortcutActions.checkState();
    }
}
