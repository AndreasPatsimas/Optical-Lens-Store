package org.patsimas.optical_lens_store.controllers;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.dto.glasses.GlassCategoryDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassLenDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassSkeletonDto;
import org.patsimas.optical_lens_store.services.GlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/glasses")
@RestController
@Slf4j
@Api(description = "Glasses (Γυαλιά)")
public class GlassController {

    @Autowired
    private GlassService glassService;

    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GlassDto> findByCustomerId(@PathVariable Long customerId) {
        log.info("Fetch Glasses from customer[id:{}]", customerId);
        return glassService.fetchByCustomerId(customerId);
    }

    @PostMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity save(@PathVariable Long customerId, @RequestBody GlassDto glassDto) {

        log.info("Save Glasses from customer[id:{}]", customerId);
        glassService.save(customerId, glassDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/categories/{glassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity saveGlassCategory(@PathVariable Long glassId, @RequestBody GlassCategoryDto glassCategoryDto) {

        log.info("Save Categories from glass[id:{}]", glassId);
        glassService.saveGlassCategory(glassId, glassCategoryDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/lens/{glassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity saveGlassLen(@PathVariable Long glassId, @RequestBody GlassLenDto glassLenDto) {

        log.info("Save Lens from glass[id:{}]", glassId);
        glassService.saveGlassLen(glassId, glassLenDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/skeleton/{glassId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity saveGlassSkeleton(@PathVariable Long glassId, @RequestBody GlassSkeletonDto glassSkeletonDto) {

        log.info("Save Skeleton from glass[id:{}]", glassId);
        glassService.saveGlassSkeleton(glassId, glassSkeletonDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable Long id) {

        log.info("Delete Glass[id:{}]", id);
        glassService.deleteGlass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
