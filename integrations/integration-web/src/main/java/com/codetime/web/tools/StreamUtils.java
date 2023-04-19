package com.codetime.web.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 通用http工具封装
 *
 */
public class StreamUtils {
    private static final Logger logger = LoggerFactory.getLogger(StreamUtils.class);

    public static String getStrByInputStream(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try (InputStream inputStream = request.getInputStream()) {
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error("StreamUtils getStrByInputStream error ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("StreamUtils getStrByInputStream close error ", e);
                }
            }
        }
        return sb.toString();
    }
}
