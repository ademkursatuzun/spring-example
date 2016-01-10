/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akursat.webapp.controller;

import com.akursat.webapp.model.Users;
import com.akursat.webapp.service.UserService;
import com.akursat.webapp.util.JasperConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author akursat
 */
@Controller
public class ListUserController {

    @Autowired
    UserService userService;

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    public ModelAndView listUsersPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Web Demo with Spring Security");

        model.addObject("person", new Users());
        model.addObject("listPersons", this.userService.getAllUsers());

        System.out.println("listusers()");
        model.setViewName("/secured/list-users");
        return model;
    }

    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    public String generateReport(HttpServletRequest request, HttpServletResponse response) throws JRException {
        System.out.println("generateReport()");
        HashMap<String, Object> hmParams = new HashMap<String, Object>();
        hmParams.put("Title", "Test");

        String reportFileName = "JRUser1";

        String contextPath = request.getServletContext().getRealPath("jasper/JRUser1.jrxml");

        System.out.println("TTTT -:" + contextPath);

        try {
            generatePdfReport(request);
        } catch (SQLException ex) {
            Logger.getLogger(ListUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/list-users";
    }
   

    public void generatePdfReport(HttpServletRequest request) throws SQLException {

        String contextPath = request.getServletContext().getRealPath("jasper/JRUser1.jrxml");

        JasperConnection jc = new JasperConnection();
        Connection conn = jc.getConn();
        try {

            InputStream input = new FileInputStream(new File(contextPath));

            System.out.println("Context Path is  : =" + contextPath);
            JasperDesign jasperDesign = JRXmlLoader.load(input);

            System.out.println("Compiling Report Designs");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            System.out.println("Creating JasperPrint Object");
            Map<String, String> parameters = new HashMap<String, String>();
            parameters.put("ReportTitle", "PDF JasperReport");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

            
            File f = new File("/home/akursat/SpringDemo/src/main/webapp/jasper/UserReport.pdf");
            f.createNewFile();

            //Exporting the report
            OutputStream output = new FileOutputStream(f);

            JasperExportManager.exportReportToPdfStream(jasperPrint, output);

            System.out.println("Report Generation Complete");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
