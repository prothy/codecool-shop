package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.products.Cloud;
import com.codecool.shop.model.products.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        product.setId(data.size() + 1);
        data.add(product);
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    @Override
    public List<Product> createObjectsFromJson() throws IOException {
        String file ="src/main/java/com/codecool/shop/resources/products.json";

        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder jsonText = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            jsonText.append(line);
            line = reader.readLine();
        }
        reader.close();
        System.out.println(getJsonOfCloud(jsonText.toString()).get(1));


        return null;
    }

    private List<Product> getJsonOfCloud(String jsonString) {
        //Deserialize
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonDeserializer<Cloud> deserializer = (json, typeOfT, context) -> {
            JsonObject jsonObject = json.getAsJsonObject();

            if (jsonObject.get("category").getAsString().equals("Cloud")) {
                return new Cloud (
                    jsonObject.get("id").getAsInt(),
                    jsonObject.get("name").getAsString(),
                    jsonObject.get("description").getAsString(),
                    new ProductCategory(2, jsonObject.get("category").getAsString()),
                    new Supplier(2, jsonObject.get("supplier").getAsString()),
                    jsonObject.get("url-img").getAsString(),
                    new BigDecimal(0),
                    jsonObject.get("yearPrice").getAsBigDecimal(),
                    jsonObject.get("monthPrice").getAsBigDecimal()

                );
            }
            return null;
        };
        gsonBuilder.registerTypeAdapter(Cloud.class, deserializer);

        Gson customGson = gsonBuilder.create();
        Cloud[] cloudProducts = customGson.fromJson(jsonString, Cloud[].class);
        List<Product> finalCloudProducts = new ArrayList<>(Arrays.asList(cloudProducts));
        finalCloudProducts.removeAll(Collections.singleton(null));

        return finalCloudProducts;
    }
}
