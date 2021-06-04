package com.ekros.library.model.dao.interfaces;

import java.sql.SQLException;

/**
 * IRepo - crud operations.
 * @author ekros
 * */

public interface IRepo<T> {

    /**
     * Insert some entity in db.
     * @param t - added entity
     * @return  - added entity with id
     * @throws SQLException for invalid data or connection error
     * */
    T insert(T t) throws SQLException;
    /**
     * Remove entity from db by id
     * @param id - identifier of the entity to be deleted
     * @return - identifier of the deleted entity
     * @throws SQLException for invalid data or connection error
     * */
    int delete(int id) throws SQLException;
    /**
     * Update some entity in db.
     * @param t - new entity state
     * @return  - updated entity
     * @throws SQLException for invalid data or connection error
     * */
    T update(T t) throws SQLException;


}
