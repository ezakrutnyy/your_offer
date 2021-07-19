package nex.zktn.your_offer.controller;

import nex.zktn.your_offer.entity.Offer;
import nex.zktn.your_offer.entity.User;
import nex.zktn.your_offer.service.OfferService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public String list(@RequestParam(name = "filter", defaultValue = "", required = false) String filter, Model model) {
        List<Offer> offers = offerService.prepareData(StringUtils.isBlank(filter) ? offerService.findAll()
                : offerService.findByCategory(filter));
        model.addAttribute("offers", offers);
        model.addAttribute("filter", filter);
        model.addAttribute("url", "offers");
        return "offer/list";
    }

    @GetMapping("/create")
    public String createTemplate(Offer offer, Model model) {
        model.addAttribute("url", "offers");
        model.addAttribute("offer", offer);
        return "offer/create";
    }

    @PostMapping("/create")
    public String create(Offer offer, @AuthenticationPrincipal User user,
                         @RequestParam("file") MultipartFile file) throws IOException {
        offer.setCreateDate(new Date());
        offer.setChangeDate(new Date());
        // save amount in cents
        offer.setStartPrice(offer.getStartPrice() * 100);
        offer.setAuthor(user);
        offerService.saveFile(file, offer);
        offerService.insertOrUpdate(offer);
        return "redirect:/offer";
    }

    @GetMapping("/update/{id}")
    public String updateTemplate(Model model, @PathVariable("id") Long id) {
        final Offer offer = offerService.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("Offer with id = %s, isn't found", id)));
        // get amount in cents
        offer.setStartPrice(offer.getStartPrice() / 100);
        model.addAttribute("offer", offer);
        model.addAttribute("url", "offers");
        return "offer/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Offer offer,
                         @RequestParam("file") MultipartFile file) throws IOException {

        final Offer offerDB = offerService.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("Offer with id = %s, isn't found", id)));

        offer.setId(id);
        offer.setChangeDate(new Date());
        offer.setCreateDate(offerDB.getCreateDate());
        offer.setAuthor(offerDB.getAuthor());
        offer.setStartPrice(offer.getStartPrice() * 100);
        offerService.saveFile(file, offer);
        offerService.insertOrUpdate(offer);
        return "redirect:/offer";
    }

    @GetMapping("/view/{id}")
    public String viewTemplate(Model model, @PathVariable("id") Long id) {
        final Offer offer = offerService.findById(id).
                orElseThrow(() -> new RuntimeException(String.format("Offer with id = %s, isn't found", id)));
        // get amount in cents
        offer.setStartPrice(offer.getStartPrice() / 100);
        model.addAttribute("offer", offer);
        model.addAttribute("url", "offers");
        return "offer/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        offerService.delete(id);
        return "redirect:/offer";
    }


}
