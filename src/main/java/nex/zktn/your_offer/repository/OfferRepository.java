package nex.zktn.your_offer.repository;

import nex.zktn.your_offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByCategory(final String category);
}
