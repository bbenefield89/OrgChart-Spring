package com.nexient.orgchart.mapper;

/**
 * Created by mrangel on 7/26/2016.
 */

public interface EntityModelMapper<E, M> {

    M entityToModel(E entity);

    E modelToEntity(M model);

}
