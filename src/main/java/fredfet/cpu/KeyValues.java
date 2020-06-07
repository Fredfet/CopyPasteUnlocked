package fredfet.cpu;

import java.awt.event.KeyEvent;

public class KeyValues {
    public static int[] getKeyValuesForChar(char c) {
        switch (c) {
            case '!':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_1};
            case '@':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_2};
            case '#':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_3};
            case '$':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_4};
            case '%':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_5};
            case '^':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_6};
            case '&':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_7};
            case '*':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_8};
            case '(':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_9};
            case ')':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_0};
            case '_':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS};
            case '+':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS};
            case '{':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET};
            case '}':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET};
            case ':':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON};
            case '"':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE};
            case '<':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA};
            case '>':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD};
            case '?':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH};
            case '|':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH};
            case '~':
                return new int[]{KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE};
            default:
                if ((int) c > 127) {
                    return new int[]{};
                } else if (Character.isUpperCase(c)) {
                    return new int[]{KeyEvent.VK_SHIFT, KeyEvent.getExtendedKeyCodeForChar(c)};
                } else {
                    return new int[]{KeyEvent.getExtendedKeyCodeForChar(c)};
                }
        }
    }
}
