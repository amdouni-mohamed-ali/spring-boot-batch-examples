package org.spring.tutorial.examples.batch.constants;

public class ApplicationConstants {

    public static final String INPUT_FILE = "in_file";
    public static final String EMPLOYEES = "employees";
    public static final String OUTPUT_FILE = "out_file";

    /***************************************************************
     * jobs and steps configuration
     * **************************************************************/

    public static final String JOB_NAME = "process_csv_file";
    public static final String FILE_READER_STEP = "file_reader_step";
    public static final String PROCESS_DATA_STEP = "process_file_step";
    public static final String WRITE_DATA_STEP = "write_data_step";
}
