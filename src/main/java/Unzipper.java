
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

public class Unzipper {

    public void unzip(Path source, Path target){
        if(Files.notExists(source)){
            System.err.printf("The path doesn't exist!");
            return;
        }

        try{
            GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile()));
            FileOutputStream fos = new FileOutputStream(target.toFile());
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) > 0){
                fos.write(buffer,0,len);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
