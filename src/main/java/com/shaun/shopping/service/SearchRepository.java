package com.shaun.shopping.service;

import com.shaun.shopping.model.Product;

import java.util.List;

public interface SearchRepository {
    List<Product> findByText(String text);
}
