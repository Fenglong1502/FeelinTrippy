/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import entity.Customer;
import entity.RecommendedActivity;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;
import session.RecommendedActivitySessionLocal;
import sun.misc.IOUtils;

/**
 *
 * @author Congx2
 */
@ManagedBean
@RequestScoped
public class RecommendActivityManagedBean {

    /**
     * Creates a new instance of RecommendActivityManagedBean
     */
    @EJB
    RecommendedActivitySessionLocal recommendedActivitySessionLocal;

    private String eventName;
    private String eventDescription;
    private float price;
    private String eventType;
    private String imgFile = "Noimage.jpg";
    private String address;
    private Customer user;
    private Part file;

    public String save(Customer c) {
        
        if (file != null) {
            imgFile = file.getSubmittedFileName();
            try {

                InputStream bytes = file.getInputStream();
                //Files.copy(bytes, path, StandardCopyOption.REPLACE_EXISTING);

                URL ftp = new URL("ftp://houszqzb:leonleon95@houseofcasesg.website/public_html/image-upload/trippyImages/" + file.getSubmittedFileName());
                URLConnection conn = ftp.openConnection();
                conn.setDoOutput(true);
                OutputStream out = conn.getOutputStream();
                // Copy an InputStream to that OutputStream then
                out.write(IOUtils.readFully(bytes, -1, false));
                out.close();

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }
        
        
        
        RecommendedActivity ra = new RecommendedActivity();
        ra.setRecName(eventName);
        ra.setRecDescription(eventDescription);
        ra.setRecPrice(price);
        ra.setEventType(eventType);
        ra.setRecImages(imgFile);
        ra.setAddress(address);
        ra.setRecStatus(null);
        ra.setRecByCustomerID(c.getUserID());
        
        
        recommendedActivitySessionLocal.createRecommendedActivity(ra);
        return "activitySaved.xhtml";
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public RecommendActivityManagedBean() {
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    

}
