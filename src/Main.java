import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        Downloader downloader = new PdfDownloader();

        Path path = Paths.get("urls.txt");

        List<String> urls;
        try {
            urls = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        urls.stream().filter(url -> url.length() > 0).parallel().forEach(downloader::downloadLocally);
    }
}