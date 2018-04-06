package controller;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", new Product());
        req.setAttribute("action", "new");
        req.getRequestDispatcher("/product-form.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory.getBlobstoreService().getUploads(req);
        List<BlobKey> blobKeyList = blobs.get("myImage");
        BlobKey blobKey = blobKeyList.get(0);

        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        ServingUrlOptions servingOptions = ServingUrlOptions.Builder.withBlobKey(blobKey);
        String imageUrl = imagesService.getServingUrl(servingOptions);

        String username = req.getParameter("username");
        String strUnitPrice = req.getParameter("unitPrice");
        int unitPrice = Integer.parseInt(strUnitPrice);

        Product product = new Product();
        product.setId(System.currentTimeMillis());
        product.setName(username);
        product.setUnitPrice(unitPrice);
        product.setPhotos(imageUrl);
        product.setStatus(1);
        ofy().save().entity(product).now();
        resp.getWriter().println(product.getPhotos());
//        resp.sendRedirect("/admin/product/list");
    }
}
