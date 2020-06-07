package fredfet.cpu;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.HashSet;
import java.util.Set;

public class KeysState {
    private final Set<Integer> keysPressed = new HashSet<>();

    public void addKey(Integer key) {
        keysPressed.add(key);
    }

    public void removeKey(Integer key) {
        keysPressed.remove(key);
    }

    public boolean isPasteShortcutActive() {
        return keysPressed.contains(NativeKeyEvent.VC_CONTROL) && keysPressed.contains(NativeKeyEvent.VC_SHIFT) && keysPressed.contains(NativeKeyEvent.VC_INSERT);
    }

    public boolean isCopyShortcutActive() {
        return keysPressed.contains(NativeKeyEvent.VC_CONTROL) && keysPressed.contains(NativeKeyEvent.VC_SHIFT) && keysPressed.contains(NativeKeyEvent.VC_DELETE);
    }

    public boolean isExitShortcutActive() {
        return keysPressed.contains(NativeKeyEvent.VC_INSERT) && keysPressed.contains(NativeKeyEvent.VC_DELETE);
    }
}
