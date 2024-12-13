package se.distansakademin.java_ee_albums;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.distansakademin.java_ee_albums.models.Album;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "albumsServlet", value = "/albums-servlet")
public class AlbumsServlet extends HttpServlet {
    private EntityManagerFactory emf;
    private EntityManager em;

    public void init() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String id = request.getParameter("id");

        if(id == null){
            showAll(request, response);
        }
        else{
            showOne(id, request, response);
        }
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Album> albums = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();

        request.setAttribute("albums", albums);

        request.getRequestDispatcher("/albums.jsp").forward(request, response);
    }


    private void showOne(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Album album = getAlbum(id);

        request.setAttribute("album", album);

        request.getRequestDispatcher("/album.jsp").forward(request,response);
    }

    private Album getAlbum(String id) {
        Long longId = Long.parseLong(id);
        Album album = em.find(Album.class, longId);
        return album;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String albumName = request.getParameter("album-name");
        String id = request.getParameter("id");
        String action = request.getParameter("action");



        if(albumName == null && id != null && action != null && action.equals("delete") ){
            deleteAlbum(id);
        } else{
            createAlbum(albumName);
        }

        response.sendRedirect("albums-servlet");
    }

    private void deleteAlbum(String id){
        Album album = getAlbum(id);

        em.getTransaction().begin();
        em.remove(album);
        em.getTransaction().commit();
    }

    private void createAlbum(String name) {
        em.getTransaction().begin();

        Album album = new Album();
        album.setName(name);

        em.persist(album);
        em.getTransaction().commit();
    }

    public void destroy() {
        em.close();
        emf.close();
    }
}