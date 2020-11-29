package first_package;


import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyVisitor extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(!attrs.isDirectory()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file.toString())),"Cp1250"));
            String linia;
            StringBuffer singleFile = new StringBuffer();
            while((linia=reader.readLine())!=null)
            {
                String temp =linia+"\n";
                singleFile.append(temp);
            }
            Pattern compiledPattern = Pattern.compile(Main.searchedPhrase.getText());
            Matcher matcher = compiledPattern.matcher(singleFile);
            System.out.println(file);

            if(matcher.find()) {
                Futil.huffer.append(file+"\n");
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}