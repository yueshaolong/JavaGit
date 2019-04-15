package composite;

public class Test {
    public static void main(String[] args) {
        File folder = new Folder();

        File textFile = new TextFile();
        folder.addFile(textFile);

        File subFolder = new Folder();
        File videoFile = new VideoFile();
        subFolder.addFile(videoFile);
        folder.addFile(subFolder);

        folder.operation();
    }
}
