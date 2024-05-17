package oop.stockexchangemanager.ExportOperation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVexporter {

    public static void writeDataToCSV(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String[] rowData : data) {
                writeRow(bw, rowData);
            }
        } catch (IOException e) {
        }
    }

    private static void writeRow(BufferedWriter bw, String[] rowData) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowData.length; i++) {
            sb.append(rowData[i]);
            if (i < rowData.length - 1) {
                sb.append(",");
            }
        }
        sb.append("\n");
        bw.write(sb.toString());
    }

    public static void createBlankCSV(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.close();
        } catch (IOException e) {
        }
    }
}
