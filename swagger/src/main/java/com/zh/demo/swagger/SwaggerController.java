//package com.zh.demo.swagger;
//
//import io.github.swagger2markup.GroupBy;
//import io.github.swagger2markup.Swagger2MarkupConfig;
//import io.github.swagger2markup.Swagger2MarkupConverter;
//import io.github.swagger2markup.Swagger2MarkupExtensionRegistry;
//import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
//import io.github.swagger2markup.builder.Swagger2MarkupExtensionRegistryBuilder;
//import io.github.swagger2markup.markup.builder.MarkupLanguage;
//import io.github.swagger2markup.spi.*;
//import io.swagger.annotations.Api;
//import org.apache.maven.project.MavenProject;
//import org.apache.maven.shared.filtering.MavenFilteringException;
//import org.apache.maven.shared.filtering.MavenResourcesExecution;
//import org.apache.maven.shared.filtering.MavenResourcesFiltering;
//import org.asciidoctor.maven.AsciidoctorMojo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//
//@Api
//@Controller
//public class SwaggerController {
//    @GetMapping("/html")
//    public void html(String url, HttpServletResponse response) throws Exception {
//        response.setContentType("application/force-download;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;fileName=index.html");
//        swagger(url);
//        String file = ClassLoader.getSystemResource("").getFile();
//        synchronized (asciidoctorMojo) {
//            asciidoctorMojo.setBackend("html5");
//            asciidoctorMojo.setDoctype("html");
//            asciidoctorMojo.setOutputDirectory(new File(dir + "/doc/asciidoc/"));
//            asciidoctorMojo.execute();
//            Path path = Paths.get(dir + "/doc/asciidoc/index.html");
//            Files.readAllLines(path, Charset.forName("utf-8")).forEach(response.getWriter()::write);
//        }
//    }
//
//    @GetMapping("/pdf")
//    public void pdf(String url, HttpServletResponse response) throws Exception {
//        response.setContentType("application/force-download;charset=utf-8");
//        response.setHeader("Content-Disposition", "attachment;fileName=index.pdf");
//        swagger(url);
//        synchronized (asciidoctorMojo) {
//            asciidoctorMojo.setBackend("pdf");
////            asciidoctorMojo.setDoctype("pdf");
//            asciidoctorMojo.setOutputDirectory(new File(dir + "/doc/asciidoc/"));
////            asciidoctorMojo.execute();
//            Path path = Paths.get(dir + "/doc/asciidoc/index.pdf");
//
//            response.getOutputStream().write(Files.readAllBytes(path));
//
//            Files.readAllLines(path).forEach(response.getWriter()::write);
//        }
//    }
//
//
//    public void swagger(String jsonTarget) throws Exception {
//        //读取配置文件
////        Configuration configuration = new Configurations().properties("config.properties");
//
//
//
////        Swagger2MarkupExtensionRegistryCustom extensionRegistry = new Swagger2MarkupExtensionRegistryCustom();
////        new Swagger2MarkupExtensionRegistryBuilder().withOverviewDocumentExtension(null).build();
////
////        extensionRegistry.getSwaggerModelExtensions().add(new SwaggerModelExtension() {
////            @Override
////            public void apply(Swagger swagger) {
////                swagger.addScheme(Scheme.HTTP);
////                swagger.addParameter("参数key", new BodyParameter());
////                swagger.addTag(new Tag());
////
////                swagger.basePath("basePath");
////                swagger.externalDocs(new ExternalDocs());
////                swagger.info(new Info().title("这里是文档头"));
////
////
////                swagger.addConsumes("add consumes");
////                swagger.consumes("consume");
////                swagger.addConsumes("consumes");
////
////            }
////        });
//
////        System.out.println(extensionRegistry);
//
//        (jsonTarget.startsWith("http")
//                //获取远程json数据
//                ? Swagger2MarkupConverter.from(new URL(jsonTarget))
//                //获取本地json数据
//                : Swagger2MarkupConverter.from(Paths.get(jsonTarget)))
//                .withConfig(config)
////                .withExtensionRegistry(extensionRegistry)
//                .build()
//                .toFolder(outputDirectory);
//    }
//
//
//
//
//
////    static String dir = System.getProperty("user.dir") + "/spring-boot-2.0.x/spring-boot-swagger/";
//    static String dir = SwaggerController.class.getClassLoader().getResource("").getPath().substring(1);
//    private static AsciidoctorMojo asciidoctorMojo;
//    //指定adoc文件生成路径
//    public Path outputDirectory = Paths.get(dir+ "/doc/asciidoc/generated");
//
//    //通过配置文件生成swagger2markup的参数
//    public Swagger2MarkupConfig config;
//
//    {
////        WindowsPath.toWindowsPath(Paths.get("/"))
//        config = new Swagger2MarkupConfigBuilder()
//                .withGeneratedExamples()
//                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
//                .withPathsGroupedBy(GroupBy.TAGS)
//                .build();
//        asciidoctorMojo = new AsciidoctorMojo() {{
//            this.project = new MavenProject();
//            this.outputResourcesFiltering = new MavenResourcesFiltering() {
//                @Override
//                public List<String> getDefaultNonFilteredFileExtensions() {
//                    return null;
//                }
//
//                @Override
//                public boolean filteredFileExtension(String s, List<String> list) {
//                    return false;
//                }
//
//                @Override
//                public void filterResources(MavenResourcesExecution mavenResourcesExecution) throws MavenFilteringException {
//
//                }
//            };
//        }};
//        asciidoctorMojo.setSourceDirectory(new File((dir + "/doc/asciidoc").replace("/out/production/classes","\\src\\main")));
//        asciidoctorMojo.setSourceDocumentName("index.adoc");
//        asciidoctorMojo.setAttributes(new HashMap() {{
//            put("doctype", "book");
//            put("toc", "left");
//            put("toclevels", "3");
////            put("numbered", "");
////            put("hardbreaks", "");
////            put("sectlinks", "");
////            put("sectanchors", "");
//            put("generated", new File(dir + "/doc/asciidoc/generated"));
//        }});
//        asciidoctorMojo.setProjectDirectory(new File(dir));
//
//
//    }
//
//
//    /**
//     * 为了定制文档，找不到swagger 自带的扩展方式。所以就重写{@link Swagger2MarkupExtensionRegistryBuilder} 类
//     */
//    private static class Swagger2MarkupExtensionRegistryCustom implements Swagger2MarkupExtensionRegistry {
//
//        List<SwaggerModelExtension> swaggerModelExtensions = new LinkedList<>();
//        List<OverviewDocumentExtension> overviewDocumentExtensions = new LinkedList<>();
//        List<DefinitionsDocumentExtension> definitionsDocumentExtensions = new LinkedList<>();
//        List<PathsDocumentExtension> pathsDocumentExtensions = new LinkedList<>();
//        List<SecurityDocumentExtension> securityDocumentExtensions = new LinkedList<>();
//        Swagger2MarkupExtensionRegistryCustom() {
//        }
//        @Override
//        public List<SwaggerModelExtension> getSwaggerModelExtensions() {
//            return this.getSwaggerModelExtensions();
//        }
//
//        @Override
//        public List<OverviewDocumentExtension> getOverviewDocumentExtensions() {
//            return this.getOverviewDocumentExtensions();
//        }
//
//        @Override
//        public List<DefinitionsDocumentExtension> getDefinitionsDocumentExtensions() {
//            return this.getDefinitionsDocumentExtensions();
//        }
//
//        @Override
//        public List<SecurityDocumentExtension> getSecurityDocumentExtensions() {
//            return this.getSecurityDocumentExtensions();
//        }
//
//        @Override
//        public List<PathsDocumentExtension> getPathsDocumentExtensions() {
//            return this.getPathsDocumentExtensions();
//        }
//    }
//
//
//
//    public static void main(String[] args) throws Exception {
//
////        System.out.println(System.getProperty("project"));
////                Path path = Paths.get(dir + "/target/asciidoc/index.pdf");
////        Path path = Paths.get(dir + "/target/asciidoc/index.pdf");
////        System.out.println();
////        byte[] bytes = Files.readAllBytes(path);
////        System.out.println(1);
////        System.out.println(1);
//
////        new SwaggerController().execute();
//        //指定本地json文件路径
////        new SwaggerController().swagger(System.getProperty("user.dir")+"/spring-boot-2.0.x/spring-boot-swagger/"+"target/swagger/swagger.json");
////        new SwaggerController().swagger(dir+"/s.json");
////        //指定远程json文件路径
////        //  new Swagger2Markup("http://petstore.swagger.io/v2/swagger.json");
//////        new SwaggerController();
////        asciidoctorMojo.setBackend("html5");
////        asciidoctorMojo.setDoctype("html");
////        asciidoctorMojo.setOutputDirectory(new File(dir + "/doc/asciidoc/"));
//        asciidoctorMojo.execute();
//
////        Options options = OptionsBuilder.options()
////                .backend("html5")
////                .docType("html")
////                .baseDir(new File(dir + "/doc/asciidoc/"))
////                .toFile(new File(dir + "/doc/asciidoc/index.html"))
//////                .templateDir()
////                .get();
////
////        Map<String, Object> attributes = new HashMap<String, Object>();
////        attributes.put("backend", "html5");
//////        OptionsBuilder
////
////        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
////        asciidoctor.
////        String[] strings = asciidoctor.convert(
////                new AsciiDocDirectoryWalker(dir+"/doc/asciidoc/generated/"),
////                System.out,
////                options);
//
////        for (String string : strings) {
////            System.out.println(string);
////        }
//
//
//    }
//}
