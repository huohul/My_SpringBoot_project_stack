package com.gxweb.controller;

import java.net.MalformedURLException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gxweb.common.utils.SwaggerUtils;
import springfox.documentation.annotations.ApiIgnore;

/**
 * swagger 文档导出  在本总栈项目目录下
 */
@RestController
@RequestMapping("/export")
@ApiIgnore
public class ExportController {

    //	生成AsciiDocs格式文档
    @RequestMapping("/ascii")
    public String exportAscii() throws MalformedURLException {
        SwaggerUtils.generateAsciiDocs();
        return "success";
    }

    //	生成AsciiDocs格式文档,并汇总成一个文件
    @RequestMapping("/asciiToFile")
    public String asciiToFile() throws MalformedURLException {
        SwaggerUtils.generateAsciiDocsToFile();
        return "success";
    }

    //	生成Markdown格式文档
    @RequestMapping("/markdown")
    public String exportMarkdown() throws MalformedURLException {
        SwaggerUtils.generateMarkdownDocs();
        return "success";
    }

    //	生成Markdown格式文档,并汇总成一个文件
    @RequestMapping("/markdownToFile")
    public String exportMarkdownToFile() throws MalformedURLException {
        SwaggerUtils.generateMarkdownDocsToFile();
        return "success";
    }

    //	生成Confluence格式文档
    @RequestMapping("/confluence")
    public String confluence() throws MalformedURLException {
        SwaggerUtils.generateConfluenceDocs();
        return "success";
    }

    //	生成Confluence格式文档,并汇总成一个文件
    @RequestMapping("/confluenceToFile")
    public String confluenceToFile() throws MalformedURLException {
        SwaggerUtils.generateConfluenceDocsToFile();
        return "success";
    }
}
