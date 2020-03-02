package com.derekprovance.garmin;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

final class DateTypeAdapter extends TypeAdapter<Date> {

    private static final TypeAdapter<Date> dateTypeAdapter = new DateTypeAdapter();
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private DateTypeAdapter() {
    }

    static TypeAdapter<Date> getDateTypeAdapter() {
        return dateTypeAdapter;
    }

    @Override
    public Date read(final JsonReader in) throws IOException {
        Date date = null;
        if(in.peek() == JsonToken.NUMBER) {
            date = new Date(in.nextLong());
        } else if (in.peek() == JsonToken.STRING) {
            String dateInString = in.nextString();

            try {
                date = formatter.parse(dateInString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }

    @Override
    @SuppressWarnings("resource")
    public void write(final JsonWriter out, final Date value) throws IOException {
        out.value(value.getTime());
    }

}

