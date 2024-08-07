package arc.haldun.ik.applicationform;

import javax.naming.OperationNotSupportedException;
import java.io.*;

public class ApplicationSerializer {

    public static void serialize(Application application, String outputFile) {
        serialize(application, new File(outputFile));
    }

    public static void serialize(Application application, File outputFile) {

        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            if (!outputFile.exists()) {
                boolean res = outputFile.createNewFile();
                if (!res) {
                    System.err.printf("Çıkış (%s) dosyası oluşturulamadı.", outputFile.getAbsolutePath());
                    return;
                }
            }

            fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(application.toString());

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Application deserialize(String targetFile) {
        return deserialize(new File(targetFile));
    }

    public static Application deserialize(File targetFile) {

        throw new RuntimeException("Operation Not Supported");
    }

    public static String indentString(String input) {
        String indent = "\t";
        String[] lines = input.split("\n");
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : lines) {
            stringBuilder.append(indent).append(line).append("\n");
        }

        return stringBuilder.toString();
    }
}
