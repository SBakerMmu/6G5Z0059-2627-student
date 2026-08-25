package uk.ac.mmu.sda.webmvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.servlet.View;

public class HelloWorldView implements View {

    private final String viewName;

    public HelloWorldView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.format("<!DOCTYPE html>%n");
        out.format("<html lang=\"en\">%n");
        out.format("<head>%n");
        out.format("	<meta charset=\"UTF-8\">%n");
        out.format("	<title>Hello World using </title>%n", this.getClass().getName());
        out.format("</head>%n");
        out.format("<body>%n");
        if (Objects.isNull(model)) {
            out.format("<h1>%s</h1>%n", "No model data available");
        } else {
            out.format("<h1>%s</h1>%n", model.get("message"));
        }
        out.format("</body>%n");
        out.format("</html>%n");
    }

}
