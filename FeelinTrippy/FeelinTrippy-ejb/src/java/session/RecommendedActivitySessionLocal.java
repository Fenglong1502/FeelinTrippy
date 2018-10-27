/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.RecommendedActivity;
import error.NoResultException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author MC
 */
@Local
public interface RecommendedActivitySessionLocal {
    
    public void createRecommendedActivity(RecommendedActivity ra);
    public List<RecommendedActivity> getAllRecommendedActivity();
    public List<RecommendedActivity> getRecommendedActivityByStatus(String status);
    public void updateRecommendedActivity(RecommendedActivity ra) throws NoResultException;
    public void deleteRecommendedActivity(Long recID) throws NoResultException;
    
}
