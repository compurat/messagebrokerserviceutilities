package com.purat;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by compurat on 2-5-15.
 */
@Component
public class StringConversions {
    private static final Logger LOGGER = Logger.getLogger(StringConversions.class);

    public String convertPartToString(final Part filePart) {
        String content = null;
        try {
            File file = File.createTempFile(String.valueOf(Math.random()), "txt");
            InputStream inputStream = filePart.getInputStream();
            Writer stringWriter = new StringWriter();
            IOUtils.copy(inputStream, stringWriter);
            content = stringWriter.toString();
            file.delete();
        } catch (IOException ioe) {
            LOGGER.error("could determine file");
            ioe.printStackTrace();
        }
        return content;
    }
}

