import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainFile {

    private final File file;
    private FileReader out;
    private FileWriter in;
    private BufferedReader readFile;
    private BufferedWriter writeFile;

    public MainFile(String name) {
        file = new File(name);
    }

    public boolean isMade() {
        return file.exists();
    }

    public static boolean isMade(String name) {
        return new File(name).exists();
    }

    /**
     * This program opens the MainFile object for
     * writing
     */
    public void openForWriting(boolean mode) {
        try {
            in = new FileWriter(file, mode);
            writeFile = new BufferedWriter(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method takes in a String and writes it
     * to the file
     * @param message: The String that is to be typed
     *               in the file
     */
    public void writeToFile(String message) {
        try {
            writeFile.write(message);
            writeFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method appends to the file, rather
     * than just writing to it and clearing everything out
     * @param message: The String that is to be appened
     *               on to the file
     */
    public void appendToFile(String message) {
        try {
            writeFile.append(message).append("\n");
            writeFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method closes the file once it
     * has been written too.
     */
    public void closeForWriting() {
        try {
            writeFile.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method opens the file for reading
     */
    public void openForReading() {
        try {
            out = new FileReader(file);
            readFile = new BufferedReader(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns a specific line from
     * the file, useful for when you're looping through it.
     * @return The current line of the file as a String
     */
    public String getLine() {
        try {
            return readFile.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method closes the file after it's been read
     */
    public void closeForReading() {
        try {
            readFile.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method searches for a String in a file
     * and then replaces every occurrence of that String.
     *
     * @param oldString: The String that is to be replaced
     *                 in the file.
     * @param newString: The new String that replaces the old
     *                 String
     */
    public void replace(String oldString, String newString) {
        String line;
        StringBuilder containment = new StringBuilder();

        openForReading();

        while((line = getLine()) != null) {
            containment.append(line).append("\n");
        }

        closeForReading();

        replaceAll(containment, oldString, newString);

        openForWriting(false);

        writeToFile(containment.toString());

        closeForWriting();
    }

    /**
     * This method takes in a StringBuilder
     * object and replaces every occurrence of that
     * String in the StringBuilder with a new String.
     *
     * @param builder: The StringBuilder object that
     *               is to be modified
     * @param from: The old String that is to be replaced.
     * @param to: The new String that it should be replaced
     *          too.
     */
    private void replaceAll(StringBuilder builder, String from, String to) {
        int index = builder.lastIndexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index -= from.length();
            index = builder.lastIndexOf(from, index);
        }
    }

    /**
     * This method allows the name of a specific file
     * of any type to be renamed. Since the renameTo();
     * method from the File class does not always respond
     * accordingly
     *
     * @param newName: The new name of the file
     *               which is to be renamed
     */
    public void rename(String newName) {
        try {
            Path source = Paths.get(file.getName());
            Files.move(source, source.resolveSibling(newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
