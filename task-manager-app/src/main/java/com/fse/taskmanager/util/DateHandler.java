package com.fse.taskmanager.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler extends StdDeserializer<String> {
    public DateHandler() {
        this(null);
    }

    public DateHandler(Class<?> clazz) {
        super(clazz);
    }

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {

        String dateString = jsonparser.getText();
        return customDateFormatter(dateString);
    }

    public static String customDateFormatter(String dateString) {
        try {
            Date d1 = df.parse(dateString);
            return sdf.format(d1);
        }
        catch ( Exception ex ){
        }
        return null;
    }
}