package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class ResourceReader {

    public static List<String> readLines(String resourcePath) {
        List<String> lines = new ArrayList<>();

        InputStream inputStream = ResourceReader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read resource: " + resourcePath, e);
        }

        return lines;
    }
}