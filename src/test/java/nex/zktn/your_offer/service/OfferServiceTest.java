package nex.zktn.your_offer.service;

import nex.zktn.your_offer.entity.Offer;
import nex.zktn.your_offer.repository.OfferRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
//@JsonTest // для тестировании сериализации
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private OfferRepository offerRepository;

    @Before
    public void before() {
        given(this.offerRepository.findAll()).willReturn(Lists.newArrayList(new Offer(), new Offer()));
    }

    @Test
    public void findAll() {
        OfferService offerService = applicationContext.getBean(OfferService.class);
        List<Offer> offers = offerService.findAll();
        Assert.assertEquals(offers.size(), 2);
    }

}
