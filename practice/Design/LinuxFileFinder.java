import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
    Design a file find class with filter.
*/
public class FileFinder {

    public void find(String pwd, String fileName, FileFilter filter) {
        validateString(pwd);
        validateString(fileName);

        File directory = new File(pwd);

        if (!directory.isDirectory()) {
            System.err.println("Error: " + pwd + " is not a directory.");
            return;
        }


        List<String> result = new ArrayList<>();
        search(directory, fileName, result);

        System.out.println("Found " + result.size() + " result.");
        for (String file : result) {
            System.out.println("Found: " + file);
        }
    }

    private void search(File directory, String fileName, List<String> result) {
        if (!directory.isDirectory()) {
            return;
        }

        System.out.println("Searching directory... " + directory.getName());

        if (!directory.canRead()) {
            System.out.println(directory.getAbsoluteFile().toString() + " permission denied.");
            return;
        }

        if (directory.listFiles() != null) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    // If file is not a file but a directory, go deeper (DFS)
                    search(file, fileName, result);
                } else {
                    // Do you want the file finder to be case sensitive?
                    // In Linux, it's case sensitive!
                    if (fileName.equals(file.getName())) {
                        result.add(file.getAbsoluteFile().toString());
                    }
                }
            }
        }
    }

    private void validateString(String str) {
        if (str == null || str.trim().length() == 0) {
            throw new IllegalArgumentException("Invalid parameter: str");
        }
    }
}

interface FileFilter {
    boolean accept(File file);
}

class MyFileSizeFilter implements FileFilter {
    /*
     * Get file larger than 100MB
     * */
    @Override
    public boolean accept(File file) {
        // larger than 100*2^20 in bytes
        return (file.getTotalSpace() > 100 * Math.pow(2, 20));
    }
}

