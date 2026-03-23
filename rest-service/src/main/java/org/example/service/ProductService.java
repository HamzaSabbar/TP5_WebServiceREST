package org.example.service;

import org.example.model.Product;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductService {

    private static ProductService instance;
    private Map<Long, Product> products = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    // Singleton
    private ProductService() {
        // Initialiser avec quelques produits
        addProduct(new Product(null, "Laptop HP", "Ordinateur portable 15 pouces", 5500.0, 10));
        addProduct(new Product(null, "iPhone 15", "Smartphone Apple", 12000.0, 5));
        addProduct(new Product(null, "Samsung Galaxy S24", "Smartphone Samsung", 9500.0, 8));
    }

    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    // GET ALL - Récupérer tous les produits
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // GET BY ID - Récupérer un produit par ID
    public Product getProductById(Long id) {
        return products.get(id);
    }

    // POST - Ajouter un nouveau produit
    public Product addProduct(Product product) {
        if (product.getId() == null) {
            product.setId(idCounter.getAndIncrement());
        }
        products.put(product.getId(), product);
        System.out.println("Produit ajouté: " + product);
        return product;
    }

    // PUT - Mettre à jour un produit
    public Product updateProduct(Long id, Product product) {
        if (!products.containsKey(id)) {
            return null;
        }
        product.setId(id);
        products.put(id, product);
        System.out.println("Produit mis à jour: " + product);
        return product;
    }

    // DELETE - Supprimer un produit
    public boolean deleteProduct(Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
            System.out.println("Produit supprimé avec l'id : " + id);
            return true;
        }
        return false;
    }

    // SEARCH - Rechercher des produits par nom
    public List<Product> searchProducts(String keyword) {
        List<Product> results = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(product);
            }
        }
        return results;
    }

    // COUNT - Compter le nombre de produits
    public int countProducts() {
        return products.size();
    }
}