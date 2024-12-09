package com.shaun.shopping.service;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.shaun.shopping.model.Product;
import lombok.extern.apachecommons.CommonsLog;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository{

    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;

    @Override
    public List<Product> findByText(String text) {
        List<Product> products = new ArrayList<>();
        MongoDatabase database = client.getDatabase("shaun");
        MongoCollection<Document> collection = database.getCollection("products");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                new Document("text",
                        new Document("query", "Laptop")
                                .append("path", "pname")))));

        result.forEach(doc -> products.add(converter.read(Product.class, doc)));

        return products;
    }
}
