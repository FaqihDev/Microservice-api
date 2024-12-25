package com.faqihdev.oa_util_core.BaseMapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDATAMapper<SOURCE,TARGET> extends Converter<SOURCE,TARGET> {

    List<TARGET> entitiesIntoDTOs(Iterable<SOURCE> source);

    Page<TARGET> entitiesIntoDTOsPage(Pageable pageRequest, org.springframework.data.domain.Page<SOURCE> entities);

    Slice<TARGET> entitiesIntoDTOSlices(Slice<SOURCE> sources);

    Page<TARGET> entitiesPageIntoDTOPage(org.springframework.data.domain.Page<SOURCE> data);

    ConvertResponseEntity convertWithResponseEntity(SOURCE source);


}
