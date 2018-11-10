/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import etc.RandomPassword;
import entity.BookedActivity;
import entity.Customer;
import entity.SavedTrip;
import entity.TrippyEventItem;
import entity.TrippyEventType;
import error.CustomerAddSavedTripException;
import error.NoResultException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/*
 * @author MC
 */
@Stateless
public class CustomerSession implements CustomerSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Customer> retrieveAllCustomer() {
        Query q;
        q = em.createQuery("SELECT c FROM Customer c");
        return q.getResultList();
    }

    @Override
    public Customer getCustomerById(Long cId) throws NoResultException {
        Customer c = em.find(Customer.class, cId);

        if (c != null) {
            return c;
        } else {
            throw new NoResultException("Not found");
        }
    } //end getCustomer

    @Override
    public void createCustomer(Customer c) {
        em.persist(c);
    } //end createCustomer

    @Override
    public List<Customer> searchCustomers(String name) {
        Query q;
        if (name != null) {
            q = em.createQuery("SELECT c FROM Customer c WHERE "
                    + "LOWER(c.name) LIKE :name");
            q.setParameter("name", "%" + name.toLowerCase() + "%");
        } else {
            q = em.createQuery("SELECT c FROM Customer c");
        }

        return q.getResultList();
    } //end searchCustomers

    @Override
    public void updateCustomer(Customer c) throws NoResultException {
        Customer oldC = em.find(Customer.class, c.getUserID());

        if (oldC != null) {
            oldC.setFirstName(c.getFirstName());
            oldC.setLastName(c.getLastName());
            oldC.setEmail(c.getEmail());
            oldC.setUsername(c.getUsername());
            oldC.setGender(c.getGender());
            oldC.setMobileNumber(c.getMobileNumber());

        } else {
            throw new NoResultException("Not found");
        }
    } //end updateCustomer

    @Override
    public void deleteCustomer(Long userID) throws NoResultException {
        Customer c = em.find(Customer.class, userID);

        if (c != null) {
            em.remove(c);
        } else {
            throw new NoResultException("Not found");
        }
    }//end deleteCustomer

    @Override
    public Customer getCustomerByName(String name) throws NoResultException {
        Query q;
        if (name != null) {
            q = em.createQuery("SELECT c FROM Customer c WHERE "
                    + "LOWER(c.username) = :username");
            q.setParameter("username", name.toLowerCase());

            if (!q.getResultList().isEmpty()) {
                return (Customer) q.getResultList().get(0);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public boolean Login(Customer c) {
        Query q = em.createQuery("SELECT c FROM Customer c WHERE "
                + "LOWER(c.email) = :email");
        q.setParameter("email", c.getEmail().toLowerCase());

        if (!q.getResultList().isEmpty()) {
            Customer checkC = (Customer) q.getResultList().get(0);
            if (checkC.getPassword().equals(c.getPassword())) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    @Override
    public void changePasword(Customer c, String newPass) {
        Customer customer = em.find(Customer.class, c.getUserID());
        customer.setPassword(newPass);
        em.flush();
    }

    @Override
    public void deactivateAccount(Long cId) {
        Customer customer = em.find(Customer.class, cId);
        customer.setAccountStatus(false);
        em.flush();
    }

    @Override
    public void activateAccount(Long cId) {
        Customer customer = em.find(Customer.class, cId);
        customer.setAccountStatus(true);
        em.flush();
    }

    @Override
    public int deductPoints(Customer c, int pointsDeducted) {
        Customer customer = em.find(Customer.class, c.getUserID());
        int currPoints = customer.getPoints();
        int newPoints = currPoints - pointsDeducted;
        customer.setPoints(newPoints);
        em.flush();

        return newPoints;
    }

    @Override
    public int addPoints(Customer c, int pointsToAdd) {
        Customer customer = em.find(Customer.class, c.getUserID());
        int currPoints = customer.getPoints();
        int newPoints = currPoints + pointsToAdd;
        customer.setPoints(newPoints);
        em.flush();

        return newPoints;
    }

    @Override
    public Customer getCustomerByEmail(String email) throws NoResultException {
        if (email != null) {
            Query q = em.createQuery("SELECT c FROM Customer c WHERE "
                    + "LOWER(c.email) = :email");
            q.setParameter("email", email.toLowerCase());
            if (!q.getResultList().isEmpty()) {
                return (Customer) q.getResultList().get(0);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean forgetPassword(Customer c) {
        Customer cust = em.find(Customer.class, c.getUserID());
        String recipientEmail = cust.getEmail();
        String newPassword = new RandomPassword().generateRandomPassword();

        String msg = "Your password has been reset! Please login with the new password:\n\"" + newPassword + "\"";
        sendEmail(recipientEmail, "Reset Password", msg);

        return true;
    }

    public static void sendEmail(String recipient, String subject, String msg) {

        String username = "automessage.feelintrippy@gmail.com";
        String password = "Fenglong1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Do-not-reply@FeelinTrippy.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    @Override
    public void addSavedTrip(Long id, SavedTrip s){
        Customer c = em.find(Customer.class, id);
        try {
            c.addSavedTrip(s);
            em.flush();
        } catch (CustomerAddSavedTripException ex) {
            Logger.getLogger(CustomerSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SavedTrip> getSavedTripByType(TrippyEventType type, Customer c) {
        List<SavedTrip> returnList = new ArrayList<SavedTrip>();
        List<SavedTrip> filterList = c.getSavedTrips();
        for(SavedTrip s: filterList){
            if(s.getEventItem().getEventTypeString().equals(type.getTypeName())){
                returnList.add(s);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEventExist(TrippyEventItem item, Long id) {
        boolean returnValue = false;
        Customer c = em.find(Customer.class, id);
        List<SavedTrip> savedList = c.getSavedTrips();
        for(SavedTrip s : savedList){
            if(s.getEventItem().getEventID() == item.getEventID()){
                return true;
            }
        }
        
        
        return returnValue;
    }

}
