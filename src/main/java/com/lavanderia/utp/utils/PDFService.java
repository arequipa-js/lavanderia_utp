package com.lavanderia.utp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class PDFService {

    String REPORT_JRXML = PDFUtils.BASE_DIR + "reporte_servicios.jrxml";
    String REPORT_PDF = PDFUtils.BASE_DIR + "reporte_servicios.pdf";
    static Connection con = DBConnection.getConnection();

    public void exportPDF(HashMap<String, Object> sqlParameters) throws JRException, SQLException, FileNotFoundException {
        HashMap<String, Object> parameters = new HashMap<>();
        String sql = "SELECT CONCAT(p.nombres, ' ', p.apellidos) AS cliente, cat.nombre as categoria, se.nombre as servicio, CONCAT('S/.', tarifa) as monto, s.fecha_solicitud as fecha\n" +
        "FROM solicitud_detalles sd \n" +
        "JOIN solicitudes s on sd.solicitud_id = s.id \n" +
        "JOIN personas p ON p.id = s.persona_id \n" +
        "JOIN servicios se on se.id = sd.servicio_id \n" +
        "JOIN prendas pre ON pre.id = sd.prenda_id \n" +
        "JOIN categorias cat on cat.id = se.categoria_id WHERE 1=1";

        int personaId = (Integer) sqlParameters.get("personaId");
        int categoriaId = (Integer) sqlParameters.get("categoriaId");
        int servicioId = (Integer) sqlParameters.get("servicioId");
        
        if (personaId != 0) {
            sql += " AND p.id = " + personaId + "";
        }
        if (categoriaId != 0) {
            sql += " AND cat.id = " + categoriaId + "";
        }
        if (servicioId != 0) {
            sql += " AND se.id = " + servicioId + "";
        }

        parameters.put("query", sql);
        System.out.println(sql);
        JasperReport jasperReport = JasperCompileManager.compileReport(REPORT_JRXML);
        JasperPrint jp = JasperFillManager.fillReport(jasperReport, parameters, con);
        JasperExportManager.exportReportToPdfFile(jp, REPORT_PDF);
    }
}
