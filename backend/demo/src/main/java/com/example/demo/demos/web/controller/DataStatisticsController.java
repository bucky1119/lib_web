// 负责处理统计网站的访问量、用户活跃度等数据的请求，包括生成统计报表、提供标记文章功能等。
package com.example.demo.demos.web.controller;

import com.example.demo.demos.web.pojo.Result;
import com.example.demo.demos.web.pojo.VisitCount;
import com.example.demo.demos.web.service.DataStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
public class DataStatisticsController {

    @Autowired
    private DataStatisticsService dataStatisticsService;

    // 此处将访问首页的次数作为访问量
    @GetMapping("/home")
    public Result countVisit() {
        Long res = dataStatisticsService.recordVisit();
        return new Result(1,"访问量+1",res);
    }

    @GetMapping("/download-pdf")
    public void downloadPdf(HttpServletResponse response) {
        try {
            // 查询数据库最近十条记录
            List<VisitCount> dataList = dataStatisticsService.getTopTenData();

            // 创建PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // 将每条数据加入PDF
            for (VisitCount data : dataList) {
                document.add(new Paragraph(data.toString())); // 假设toString()方法返回数据的有用表示
            }
            document.close();

            // 设置HTTP响应头
            response.setContentType("application/pdf"); // 设置HTTP响应的内容类型为PDF
            /**
             例如你想发送其他类型的数据也可以设置：
             发送HTML内容: response.setContentType("text/html");
             发送纯文本: response.setContentType("text/plain");
             发送JPEG图片: response.setContentType("image/jpeg");
             发送JSON数据: response.setContentType("application/json");
             */
            response.setHeader("Content-Disposition", "attachment; filename=\"data.pdf\""); // 设置HTTP响应的头信息，告诉浏览器这是一个附件，建议保存的文件名为"data.pdf"
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            org.apache.commons.io.IOUtils.copy(in, response.getOutputStream());
            // 上面一行利用Apache Commons IO库的IOUtils类的copy方法，
            // 将PDF文件的内容（现在存储在ByteArrayInputStream 'in'）复制到HTTP响应的输出流中
            // 这使得PDF的内容能够被发送到请求该服务的客户端
            response.flushBuffer(); // 刷新响应的缓冲区，完成响应的发送
        } catch (Exception e) {
            e.printStackTrace();
            // 错误处理
        }
    }
}