package caseStudy2.menu.format;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite {
    public static <T> void write(String path, List<T> items) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path,false));
            for (T item : items) {
                bufferedWriter.write(item.toString());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Không tìm thấy file --> " + path);
        }
    }

    public static List<String> read(String path) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader bfReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bfReader.readLine()) != null && !line.trim().isEmpty()) {
                lines.add(line);
            }

        } catch (IOException ioException) {
            System.out.println("Không tìm thấy file --> " + path);
        }
        return lines;
    }
}
