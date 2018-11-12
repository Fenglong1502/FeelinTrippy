/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BookedActivity;
import entity.QRDetail;
import static entity.QRDetail_.bookedActivity;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fengl
 */
@Local
public interface QRDetailSessionLocal {
    public void createQRRecord(QRDetail qr);
    public QRDetail getQRDetailWithBookedActivity(BookedActivity b) throws NoResultException;
    public QRDetail getQRDetailByID(Long qrDetailID) throws NoResultException;
    public void deleteQRRecord(Long qrDetailID) throws NoResultException;
    public void updateQRRecord(QRDetail qr) throws NoResultException;
    public List<QRDetail> getAllQRDetails();

    
    //Testing methods
    public QRDetail createTempQRDetails();
}
