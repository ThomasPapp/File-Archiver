package pappthomas.util;

import java.nio.ByteBuffer;

/**
 * @author Thomas
 */
public class BufferUtil {

    public static void putString(ByteBuffer buffer, String str) {
        buffer.put(str.getBytes());
        buffer.put((byte) 0);
    }

    public static String getString(ByteBuffer buffer) {
        StringBuilder sb = new StringBuilder();
        byte b;
        while ((b = buffer.get()) != 0) {
            sb.append((char) b);
        }
        return sb.toString();
    }

}
