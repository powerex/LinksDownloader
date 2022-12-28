import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PdfDownloader implements Downloader {

    private final String DIR = "files/";

    @Override
    public void downloadLocally(String url) {
        String fileName = getFileName(url);

        URL urlSource;
        try {
            urlSource = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try (InputStream in = urlSource.openStream()) {
            Files.copy(in, Paths.get(DIR + fileName), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Ready: " + fileName);
        } catch (IOException e) {
            // handle exception
        }
    }

    private String getFileName(String url) {
        String sub = "pdf/";
        int pos = url.lastIndexOf(sub);
        return (pos != -1 && pos != url.length() - sub.length() ? url.substring(pos + sub.length()) : "")
            .replaceAll("/", "_");
    }
}
