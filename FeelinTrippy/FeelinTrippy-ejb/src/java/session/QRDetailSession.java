/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BookedActivity;
import entity.QRDetail;
import error.NoResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fengl
 */
@Stateless
public class QRDetailSession implements QRDetailSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createQRRecord(QRDetail qr) {
        em.persist(qr);
    }

    @Override
    public QRDetail getQRDetailWithBookedActivity(BookedActivity b) throws NoResultException {
        Query q;
        if (b != null) {
            q = em.createQuery("SELECT b FROM QRDetail q WHERE "
                    + "q.bookedActivity IN :bookedActivity");
            q.setParameter("bookedActivity", b);
        } else {
            return new QRDetail();
        }

        return (QRDetail) q.getResultList().get(0);
    }

    @Override
    public QRDetail getQRDetailByID(Long qrDetailID) throws NoResultException {
        QRDetail qr = em.find(QRDetail.class, qrDetailID);
        if (qr != null) {
            return qr;
        } else {
            throw new NoResultException("QRDetail no found!");
        }
    }

    @Override
    public void deleteQRRecord(Long qrDetailID) throws NoResultException {
        QRDetail qr = em.find(QRDetail.class, qrDetailID);
        if (qr != null) {
            em.remove(qr);
        } else {
            throw new NoResultException("QRDetail no found!");
        }
    }

    ///////////////////Might not need this method at all////////
    @Override
    public void updateQRRecord(QRDetail qr) throws NoResultException {
        QRDetail oldQR = em.find(QRDetail.class, qr.getQrDetailID());
        if (oldQR != null) {
            oldQR.setBookedActivity(qr.getBookedActivity());
            oldQR.setQrText(qr.getQrText());
        } else {
            throw new NoResultException("QR Detail not found!");
        }
    }

    @Override
    public List<QRDetail> getAllQRDetails() {
        Query q = em.createQuery("SELECT qr FROM QRDetail qr");
        return q.getResultList();
    }

    @Override
    public QRDetail createTempQRDetails() {
        QRDetail qr = new QRDetail();
        em.persist(qr);

        Query q = em.createQuery("SELECT q FROM QRDetail q ORDER BY q.qrDetailID DESC");
        return (QRDetail) q.getResultList().get(0);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
