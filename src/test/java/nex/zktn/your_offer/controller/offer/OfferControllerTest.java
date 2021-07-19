package nex.zktn.your_offer.controller.offer;

import nex.zktn.your_offer.controller.OfferController;
import nex.zktn.your_offer.entity.Offer;
import nex.zktn.your_offer.service.OfferService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    OfferService offerService;

    @Before
    public void before() {
        Offer offer = new Offer();
        offer.setId(1000L);
        offer.setCreateDate(new Date());
        offer.setTitle("Toyota Rav4");
        offer.setCategory("Auto");
        offer.setDescription("Description ...");
        offer.setStartPrice(1_000_000L);
        given(this.offerService.findAll()).willReturn(Lists.newArrayList(offer));
    }

    @Test
    public void list() throws Exception {
        MvcResult result = this.mvc.perform(get("/offer").accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isOk()).andReturn();
        List<Offer> offers = (List<Offer>) result.getModelAndView().getModelMap().getAttribute("offers");
        Assert.assertEquals(offers.size(), 1);

    }
}
