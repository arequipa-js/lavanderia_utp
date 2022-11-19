package com.lavanderia.utp.utils;


import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class PDFService {
    String REPORT_JRXML = PDFUtils.BASE_DIR + "report.jrxml";
    String REPORT_PDF = PDFUtils.BASE_DIR + "report.pdf";

    public void exportPDF(HashMap<String, Object> parameters) throws JRException {
        
        JasperReport jasperReport = JasperCompileManager.compileReport(REPORT_JRXML);
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        JasperExportManager.exportReportToPdfFile(jp, REPORT_PDF);
    }
}
