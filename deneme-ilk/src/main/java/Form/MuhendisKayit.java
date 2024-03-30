package Form;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/muhendisKayit")
public class MuhendisKayit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
        String kullaniciAdi = request.getParameter("username");
        String sifre = request.getParameter("password");
        String sifreTekrar = request.getParameter("passwordRepeat");
        String ad = request.getParameter("ad"); 
        String soyad = request.getParameter("soyad"); 
        
        if (sifre != null && sifre.equals(sifreTekrar)) {
            DatabaseHelper db = new DatabaseHelper();
            boolean insertResult = db.insertUserAndEngineer(kullaniciAdi, sifre, ad, soyad);

            if (insertResult) {
                response.sendRedirect("kayitBasarili.jsp");
            } else {
                response.sendRedirect("kayitBasarisiz.jsp");
            }
        } else {
            response.sendRedirect("kayitFormu.jsp?error=sifrelerEslesmiyor");
        }

    }
}
