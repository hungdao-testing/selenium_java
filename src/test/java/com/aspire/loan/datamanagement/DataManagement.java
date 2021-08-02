package com.aspire.loan.datamanagement;

import com.aspire.loan.dataschema.DataSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DataManagement {

    protected static final String DATA_FILE = "src/test/resources/data.json";
    protected static DataSchema data;

    public static DataSchema getData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader(DATA_FILE)) {
            // Convert JSON File to Java Object
            data = gson.fromJson(reader, DataSchema.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


}
