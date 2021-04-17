package com.maven;

public class ControllerCreater {
    public static String starter(String project){
return "package com."+project+".controller;\n" +
        "\n" +
        "import org.springframework.web.bind.annotation.GetMapping;\n" +
        "import org.springframework.web.bind.annotation.RestController;\n" +
        "\n" +
        "@RestController\n" +
        "public class TestController {\n" +
        "    @GetMapping(\"/index\")\n" +
        "    public String getById() {\n" +
        "        return \"service.getOrder(id)\";\n" +
        "    }\n" +
        "}\n";
    }
}
