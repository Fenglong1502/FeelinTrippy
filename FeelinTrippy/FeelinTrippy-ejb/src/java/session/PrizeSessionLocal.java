/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Prize;
import entity.PrizeOrder;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.NoResultException;

/**
 *
 * @author dk349
 */
@Local
public interface PrizeSessionLocal {
    public void createPrize(Prize prize);
    public void updatePrize(Prize p) throws NoResultException;
    public void removePrize(Long prizeID);
    public List<Prize> getAllPrize(); 
    public List<Prize> searchPrizeByName(String searchTerm);
    public boolean redeemPrize(Long customerID, Long prizeID, int qty);
    public List<PrizeOrder> getPrizeRedeemed(Long customerID);
    public Prize getPrizeById(Long prizeID) throws NoResultException;
}
