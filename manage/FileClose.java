package proLibrary.manage;

import java.io.InputStream;
import java.io.OutputStream;

public class FileClose {

    public static void close(InputStream fis, InputStream is) {
        close(fis);
        close(is);
    }

    public static void close(OutputStream fos, OutputStream os) {
        close(fos);
        close(os);
    }

    public static void close(InputStream is) {
        if(is != null) {
            try {
                is.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(OutputStream os) {
        if(os != null) {
            try {
                os.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
