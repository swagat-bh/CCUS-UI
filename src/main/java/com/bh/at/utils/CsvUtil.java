package com.bh.at.utils;

import com.bh.commonutil.CyBaseException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvUtil {

    public CsvUtil() {
    }

    public List<String> getGroupIdFromCSV(File CSV_FILE_PATH) throws IOException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, CyBaseException {
        List<String> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(String.valueOf(CSV_FILE_PATH)));) {
            while (scanner.hasNextLine()) {
                records.add((scanner.nextLine()));
            }
        }
        return records;

    }
}

