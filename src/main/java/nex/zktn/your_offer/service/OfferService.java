package nex.zktn.your_offer.service;


import nex.zktn.your_offer.entity.Offer;
import nex.zktn.your_offer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository repository;

    private final String uploadPath;

    @Autowired
    public OfferService(final OfferRepository repository,
                        final @Value("${upload.path}") String uploadPath) {
        this.repository = repository;
        this.uploadPath = uploadPath;
    }

    public List<Offer> findAll() {
        List<Offer> result = repository.findAll();
        result.sort(Comparator.comparing(Offer::getChangeDate).reversed());
        return result;
    }

    public Optional<Offer> findById(final Long id) {
        return repository.findById(id);
    }

    public List<Offer> findByCategory(final String category) {
        List<Offer> result = repository.findByCategory(category);
        result.sort(Comparator.comparing(Offer::getChangeDate).reversed());
        return result;
    }

    public void insertOrUpdate(final Offer offer) {
        repository.save(offer);
    }

    public void delete(final Long id) {
        repository.deleteById(id);
    }

    public void saveFile(final MultipartFile file,
                         final Offer offer) throws IOException {
        if (file != null && file.getOriginalFilename() != null
                && file.getOriginalFilename().length() > 0) {

            File dir = new File(uploadPath);

            if (!dir.exists()) dir.mkdir();

            String filename = UUID.randomUUID() + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + filename));
            offer.setFilename(filename);
        }
    }

    public List<Offer> prepareData(List<Offer> offers) {
        return offers.stream()
                .peek(offer -> offer.setStartPrice(offer.getStartPrice()/100))
                .collect(Collectors.toList());
    }

}
