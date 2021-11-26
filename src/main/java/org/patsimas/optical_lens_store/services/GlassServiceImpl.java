package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.domain.Customer;
import org.patsimas.optical_lens_store.domain.glasses.Glass;
import org.patsimas.optical_lens_store.domain.glasses.GlassCategory;
import org.patsimas.optical_lens_store.domain.glasses.GlassLen;
import org.patsimas.optical_lens_store.domain.glasses.GlassSkeleton;
import org.patsimas.optical_lens_store.dto.glasses.GlassCategoryDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassLenDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassSkeletonDto;
import org.patsimas.optical_lens_store.enums.GlassCategoryType;
import org.patsimas.optical_lens_store.enums.GlassLenType;
import org.patsimas.optical_lens_store.repositories.CustomerRepository;
import org.patsimas.optical_lens_store.repositories.glasses.GlassCategoryRepository;
import org.patsimas.optical_lens_store.repositories.glasses.GlassLenRepository;
import org.patsimas.optical_lens_store.repositories.glasses.GlassRepository;
import org.patsimas.optical_lens_store.repositories.glasses.GlassSkeletonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class GlassServiceImpl implements GlassService {

    private CustomerRepository customerRepository;
    private GlassRepository glassRepository;
    private GlassCategoryRepository glassCategoryRepository;
    private GlassLenRepository glassLenRepository;
    private GlassSkeletonRepository glassSkeletonRepository;
    private ConversionService conversionService;

    @Autowired
    public GlassServiceImpl(CustomerRepository customerRepository, GlassRepository glassRepository, GlassCategoryRepository glassCategoryRepository,
                            GlassLenRepository glassLenRepository, GlassSkeletonRepository glassSkeletonRepository,
                            ConversionService conversionService) {
        this.customerRepository = customerRepository;
        this.glassRepository = glassRepository;
        this.glassCategoryRepository = glassCategoryRepository;
        this.glassLenRepository = glassLenRepository;
        this.glassSkeletonRepository = glassSkeletonRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<GlassDto> fetchByCustomerId(Long customerId) {

        log.info("Fetch Glasses from customer[id:{}] process begins", customerId);

        List<Glass> glasses = glassRepository.findByCustomer_IdOrderByLastUpdateDate(customerId);

        List<GlassDto> glassDtoList = glasses.stream()
                .map(glass -> conversionService.convert(glass, GlassDto.class))
                .collect(Collectors.toList());

        log.info("Fetch Glasses from customer[id:{}] process end", customerId);

        return glassDtoList;
    }

    @Override
    public void save(Long customerId, GlassDto glassDto) {

        log.info("Save Glasses from customer[id:{}] process begins", customerId);

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        customerOptional.ifPresent(customer -> {

            customer.setLastUpdateDate(Instant.now());
            customerRepository.save(customer);
            Glass glass = conversionService.convert(glassDto, Glass.class);
            if (!ObjectUtils.isEmpty(glass)) {
                glass.setCustomer(customer);
                glass = glassRepository.save(glass);
                if (ObjectUtils.isEmpty(glassDto.getId()))
                    initialSaveGlasses(glass);
            }
        });

        log.info("Save Glasses from customer[id:{}] process end", customerId);
    }

    @Override
    public void saveGlassCategory(Long glassId, GlassCategoryDto glassCategoryDto) {

        log.info("Save Categories from glass[id:{}] process begins", glassId);

        Optional<Glass> glassOptional = glassRepository.findById(glassId);

        glassOptional.ifPresent(glass -> {

            glass.setLastUpdateDate(Instant.now());
            glassRepository.save(glass);
            GlassCategory glassCategory = conversionService.convert(glassCategoryDto, GlassCategory.class);
            if (!ObjectUtils.isEmpty(glassCategory)) {
                glassCategory.setGlass(glass);
                glassCategoryRepository.save(glassCategory);
                log.info("Save Categories from glass[id:{}] process end", glassId);
            }
        });
    }

    @Override
    public void saveGlassLen(Long glassId, GlassLenDto glassLenDto) {

        log.info("Save Lens from glass[id:{}] process begins", glassId);

        Optional<Glass> glassOptional = glassRepository.findById(glassId);

        glassOptional.ifPresent(glass -> {

            glass.setLastUpdateDate(Instant.now());
            glassRepository.save(glass);
            GlassLen glassLen = conversionService.convert(glassLenDto, GlassLen.class);
            if (!ObjectUtils.isEmpty(glassLen)) {
                glassLen.setGlass(glass);
                glassLenRepository.save(glassLen);
                log.info("Save Lens from glass[id:{}] process end", glassId);
            }
        });
    }

    @Override
    public void saveGlassSkeleton(Long glassId, GlassSkeletonDto glassSkeletonDto) {

        log.info("Save Skeleton from glass[id:{}] process begins", glassId);

        Optional<Glass> glassOptional = glassRepository.findById(glassId);

        glassOptional.ifPresent(glass -> {

            glass.setLastUpdateDate(Instant.now());
            glassRepository.save(glass);
            GlassSkeleton glassSkeleton = conversionService.convert(glassSkeletonDto, GlassSkeleton.class);
            if (!ObjectUtils.isEmpty(glassSkeleton)) {
                glassSkeleton.setGlass(glass);
                glassSkeletonRepository.save(glassSkeleton);
                log.info("Save Lens from glass[id:{}] process end", glassId);
            }
        });
    }

    @Override
    public void deleteGlass(Long glassId) {

        log.info("Delete Glass[id:{}] process begins", glassId);

        glassRepository.deleteById(glassId);

        log.info("Delete Glass[id:{}] process end", glassId);
    }

    private void initialSaveGlasses(Glass glass) {

        Set<GlassCategory> glassCategories = Stream.of(GlassCategory.builder()
                .type(GlassCategoryType.SMALL.code())
                .glass(glass)
                .build(), GlassCategory.builder()
                .type(GlassCategoryType.MEDIUM.code())
                .glass(glass)
                .build(), GlassCategory.builder()
                .type(GlassCategoryType.LARGE.code())
                .glass(glass)
                .build())
                .collect(Collectors.toSet());
        glassCategoryRepository.saveAll(glassCategories);

        Set<GlassLen> glassLens = Stream.of(GlassLen.builder()
                .type(GlassLenType.RIGHT.code())
                .glass(glass)
                .build(), GlassLen.builder()
                .type(GlassLenType.LEFT.code())
                .glass(glass)
                .build())
                .collect(Collectors.toSet());
        glassLenRepository.saveAll(glassLens);

        glassSkeletonRepository.save(GlassSkeleton.builder()
                .glass(glass)
                .build());
    }
}
