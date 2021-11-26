package org.patsimas.optical_lens_store.controllers;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactCardDto;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactLenDto;
import org.patsimas.optical_lens_store.services.ContactLenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/contact-lens")
@RestController
@Slf4j
@Api(description = "ContactLens (Φακοί Επαφής)")
public class ContactLenController {

    @Autowired
    private ContactLenService contactLenService;

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ContactCardDto> findByCustomerId(@PathVariable Long customerId) {
        log.info("Fetch ContactCard from customer[id:{}]", customerId);
        return contactLenService.fetchByCustomerId(customerId);
    }

    @PostMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity save(@PathVariable Long customerId, @RequestBody ContactCardDto contactCardDto) {

        log.info("Save ContactCard from customer[id:{}]", customerId);
        contactLenService.save(customerId, contactCardDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/lens/{contactCardId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity saveGlassLen(@PathVariable Long contactCardId, @RequestBody ContactLenDto contactLenDto) {

        log.info("Save Lens from contactCard[id:{}]", contactCardId);
        contactLenService.saveContactLen(contactCardId, contactLenDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable Long id) {

        log.info("Delete Glass[id:{}]", id);
        contactLenService.deleteContactCard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
