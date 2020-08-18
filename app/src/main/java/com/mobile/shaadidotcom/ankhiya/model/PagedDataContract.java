package com.mobile.shaadidotcom.ankhiya.model;

import java.util.List;

/**
 * Contract to be implemented by concrete class
 * @param <T> entity
 */
public interface PagedDataContract<T> {

    int size();

    boolean isEmpty();

    List<T> getResults();

    String getSeed();

    int getTakeCount();

    int getPageNumber();

    String getAPIVersion();
}
