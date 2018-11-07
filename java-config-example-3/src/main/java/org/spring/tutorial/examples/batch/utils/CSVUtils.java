package org.spring.tutorial.examples.batch.utils;

import com.opencsv.*;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.tutorial.examples.batch.entity.Employee;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(CSVUtils.class);

    public static List<String[]> readCsvLines(Path file) throws IOException {

        CSVReader csvReader = null;
        try (
                Reader reader = Files.newBufferedReader(file)
        ) {

            //create our csv configuration
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')// ; is our csv separator
                    .withIgnoreQuotations(true)
                    .build();
            //configure the csv reader
            csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)//to ignore the first line
                    .withCSVParser(parser)
                    .build();
            //return the csv lines
            List<String[]> records = csvReader.readAll();
            return records;
        } finally {

            try {
                if (csvReader != null)
                    csvReader.close();
            } catch (IOException e) {
                LOGGER.warn("could not close the csv reader.", e);
            }
        }
    }

    public static void writeCsvLine(Path file, List<Employee> emp) throws Exception {

        try (
                Writer writer = Files.newBufferedWriter(file)
        ) {
            StatefulBeanToCsv<Employee> beanToCsv = new StatefulBeanToCsvBuilder<Employee>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(emp);
        }
    }
}
