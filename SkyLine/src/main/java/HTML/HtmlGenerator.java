package HTML;

import Test.TestResults;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class HtmlGenerator {

    public void generateHtmlToFile(List<TestResults> testResults, String filePath) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("    <title>Test Results</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <img src=\"data:image/png;base64,").append(getBase64EncodedImage("Skyline.jpeg")).append("\" alt=\"Skyline Logo\" width=\"200\" height=\"200\">\n")
                .append("    <h2>Test Results</h2>\n")
                .append("    <table border=\"1\">\n")
                .append("        <tr>\n")
                .append("            <th>Test Name</th>\n")
                .append("            <th>Success</th>\n")
                .append("            <th>Duration</th>\n")
                .append("        </tr>\n");

        for (TestResults result : testResults) {
            htmlContent.append("        <tr>\n")
                    .append("            <td>").append(result.getTestName()).append("</td>\n")
                    .append("            <td>").append(result.getSuccess()).append("</td>\n")
                    .append("            <td>").append(result.getDuration()).append(" ms</td>\n")
                    .append("        </tr>\n");
        }

        htmlContent.append("    </table>\n")
                .append("</body>\n")
                .append("</html>");

        writeHtmlToFile(htmlContent.toString(), filePath);
    }

    private void writeHtmlToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            System.out.println("HTML content written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getBase64EncodedImage(String imageName) {
        try (InputStream inputStream = Objects.requireNonNull(HtmlGenerator.class.getClassLoader().getResourceAsStream(imageName))) {
            byte[] imageBytes = inputStream.readAllBytes();
            return java.util.Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return "";
        }
    }
}
