
package com.lavanderia.utp.utils;


public class Common {
    // Email config
    public static final String SMTP_HOST = "smtp.gmail.com";
    public static final int SMTP_PORT = 465;
    public static final String SMTP_USERNAME = "arequipa.javascript@gmail.com";
    public static final String SMTP_PASSWORD = "hrtmmsjynvulhbll";
    
    // Nombre de la empresa
    private static final String EMPRESA = "Lavanderia UTP - ";
    
    // Email asuntos
    public static final String SOLICITUD_ASUNTO = EMPRESA + "Solicitud registrada";
    public static final String SERVICIO_ASUNTO = EMPRESA + "Servicio registrado";
    public static final String VISITA_ASUNTO = EMPRESA + "Programacion de visita registrada.";
    public static final String CLIENTE_ASUNTO = EMPRESA + "¡Bienvenido!";
    
    // Email mensajes
    public static final String SOLICITUD_MENSAJE = "Su solicitud ha sido registrada exitosamente con nro: 00-";
    public static final String SERVICIO_MENSAJE = "Para brindar el servicio de: ";
    public static final String CLIENTE_MENSAJE = "Se ha registrado exitosamente en nuestro servicio de lavanderia.";
    public static final String VISITA_MENSAJE = "Se ha programado una visita a su domicilio de acuerdo a su solicitud nro: ";
    public static final String FECHA_HORA_VISITA = "Fecha y hora de visita.";
    public static final String GRACIAS = "Muchas gracias por confiar en Lavanderia UTP.";
    
}
