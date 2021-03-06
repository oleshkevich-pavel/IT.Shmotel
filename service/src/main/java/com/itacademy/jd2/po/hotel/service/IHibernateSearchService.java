package com.itacademy.jd2.po.hotel.service;

import java.util.List;

public interface IHibernateSearchService<T> {

    void initializeHibernateSearch() throws InterruptedException;

    List<T> fuzzySearch(Class<?> T, String field, String searchTerm);
}
