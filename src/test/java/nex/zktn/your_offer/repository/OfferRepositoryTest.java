package nex.zktn.your_offer.repository;

import nex.zktn.your_offer.entity.Offer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferRepositoryTest {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAll() {
        Offer offer = new Offer();
        offer.setCreateDate(new Date());
        offer.setTitle("Toyota Rav4");
        offer.setCategory("Auto");
        offer.setDescription("Description ...");
        offer.setStartPrice(1_000_000L);
        this.entityManager.persist(offer);
        Assert.assertEquals(offerRepository.findAll().size(), 1);
    }
}
