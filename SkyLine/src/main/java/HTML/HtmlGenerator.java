package HTML;

import Test.TestResults;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlGenerator {

    public static void generateHtmlToFile(List<TestResults> testResults, String filePath) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
                .append("    <title>Test Results</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("    <h2>Test Results</h2>\n")
                .append("    <table border=\"1\">\n")
                .append("        <tr>\n")
                .append("            <th>Test Name</th>\n")
                .append("            <th>Success</th>\n")
                .append("            <th>Duration</th>\n")
                .append("            <th>Expected Result</th>\n")
                .append("            <th>Actual Result</th>\n")
                .append("        </tr>\n");

        for (TestResults result : testResults) {
            htmlContent.append("        <tr>\n")
                    .append("            <td>").append(result.getTestName()).append("</td>\n")
                    .append("            <td>").append(result.getSuccess()).append("</td>\n")
                    .append("            <td>").append(result.getDuration()).append(" ms</td>\n")
                    .append("            <td>").append(result.getExpectedResult()).append("</td>\n")
                    .append("            <td>").append(result.getActualResult()).append("</td>\n")
                    .append("        </tr>\n");
        }

        htmlContent.append("    </table>\n")
                .append("</body>\n")
                .append("</html>");

        writeHtmlToFile(htmlContent.toString(), filePath);
    }

    private static void writeHtmlToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
            System.out.println("HTML content written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
