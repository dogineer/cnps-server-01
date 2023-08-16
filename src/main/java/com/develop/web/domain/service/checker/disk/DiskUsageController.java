package com.develop.web.domain.service.checker.disk;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@Tag(name = "체크", description = "Swagger 테스트용 API")
@RequestMapping(value = "/check")
public class DiskUsageController {

    @Value("${app.upload.dir:${user.home}}")
    String path;

    @GetMapping("/disk-usage")
    public String getDiskUsage() {
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("df", "-h", path);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                result.append("error").append(exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}