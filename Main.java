import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.io.BufferedReader;
import java.util.Scanner;
class IterableFile implements Iterable<String>
{
    BufferedReader _reader;
    public IterableFile(BufferedReader reader) { _reader = reader; };
    @Override
    public Iterator<String> iterator() { return new FileIterator(_reader); }
}
class FileIterator implements Iterator<String>
{
    BufferedReader _reader;
    FileIterator(BufferedReader reader) {
        _reader = reader;
    };
    @Override
    public boolean hasNext() {
        try {
            return _reader.ready();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
    @Override
    public String next() {
        try {
            return _reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FileReader file_reader;
        flag:
        {
            while(true) {
                System.out.print("Введите путь до файла: ");
                String path = in.nextLine();
                try {
                    file_reader = new FileReader(path);
                    break flag;
                } catch (IOException e) {
                    System.out.println("Файл не найден");
                }
            }
        }
        BufferedReader reader = new BufferedReader(file_reader);
        IterableFile ifile = new IterableFile(reader);
        for (String itVar : ifile) {
            System.out.println(itVar);
        }
    }
}
