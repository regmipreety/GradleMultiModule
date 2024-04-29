package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;


    @Test
    public void testDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        new CouponServlet().doGet(request, response);
        assertEquals("SUPERSALE", stringWriter.toString());
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("coupon")).thenReturn("SUPERSALE");
        when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
        new CouponServlet().doPost(request, response);
        verify(request).setAttribute("discount", "discount for coupon SUPERSALE is 50%");
        verify(requestDispatcher).forward(request, response);
    }
}
