package com.lavanderia.utp.utils;

import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Functions {

    public static int toInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int getSessionClienteId() {
        try {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            int clienteId = 0;
            if (session.getAttribute("clienteId") != null) {
                clienteId = (Integer) session.getAttribute("clienteId");
            }
            return clienteId;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
