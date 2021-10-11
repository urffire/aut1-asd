package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad feedback) {
        return em.merge(feedback);
    }

    public List<Ad> findAll() {
        return em.createQuery("SELECT n FROM Ad n", Ad.class).getResultList();
    }

    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }

    public Ad findByAd(Ad ad) {
        if (ad.getPassword().equals(findById(ad.getId()).getPassword()))
        {
            save(ad);
            return ad;
        }
        return null;
    }

    public List<Ad> findByKeyword(String keyword, Integer mini, Integer maxi) {
        return em.createQuery("SELECT n FROM Ad n WHERE n.title LIKE ?1 AND n.price < ?3 AND n.price>?2", Ad.class).setParameter(1, '%' + keyword + '%').setParameter(2 , mini).setParameter( 3, maxi).getResultList();
    }


    @Transactional
    public void deleteById(long id) {
        Ad todo = findById(id);
        em.remove(todo);
    }
}
