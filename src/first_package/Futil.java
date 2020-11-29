package first_package;

import java.io.*;
import java.nio.file.*;

public class Futil {
    static StringBuffer huffer;
    public static void processDir(String data) {
        huffer = new StringBuffer();
        File file = new File(data);
        MyVisitor filevisitor =new MyVisitor();
        try {
            Files.walkFileTree(file.toPath(),filevisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.resultTextArea.setText(huffer.toString());

    }
}
