package ch.hearc.mnemosyne_api.repository;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.mnemosyne_api.model.BookRest;

/**
 * Interface de gestion des données des livres
 * 
 * @author Dorian
 *
 */
public interface BookRestRepository_I extends CrudRepository<BookRest, Long> {

	BookRest findById(long id);

	BookRest findByName(String name);

    Iterable<BookRest> findByNameContainingIgnoreCase(String searchTerm);
}
