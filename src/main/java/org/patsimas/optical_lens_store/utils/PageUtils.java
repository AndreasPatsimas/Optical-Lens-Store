package org.patsimas.optical_lens_store.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtils {

    public static  <T> Page<T> buildPagedResults(List<T> list, Long countResult, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        return new PageImpl<>
                (list, PageRequest.of(pageable.getPageNumber(), pageSize), countResult);
    }
}
