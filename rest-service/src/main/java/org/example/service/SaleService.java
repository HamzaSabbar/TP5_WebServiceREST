package org.example.service;

import org.example.model.Sale;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SaleService {

    private static SaleService instance;
    private Map<Long, Sale> sales = new ConcurrentHashMap<>();
    private AtomicLong idCounter = new AtomicLong(1);

    private SaleService() {
        addSale(new Sale(null, "2026-03-20", "Laptop HP", 2, 5500.0, 0.0, "Ali Benjelloun", "Rabat"));
        addSale(new Sale(null, "2026-03-21", "iPhone 15", 1, 12000.0, 0.0, "Sara Alaoui", "Casablanca"));
        addSale(new Sale(null, "2026-03-22", "Samsung Galaxy S24", 3, 9500.0, 0.0, "Youssef Amrani", "Marrakech"));
    }

    public static synchronized SaleService getInstance() {
        if (instance == null) {
            instance = new SaleService();
        }
        return instance;
    }

    public List<Sale> getAllSales() {
        return new ArrayList<>(sales.values());
    }

    public Sale getSaleById(Long id) {
        return sales.get(id);
    }

    public Sale addSale(Sale sale) {
        if (sale.getId() == null) {
            sale.setId(idCounter.getAndIncrement());
        }
        sale.setTotal(sale.getQuantity() * sale.getPrice());
        sales.put(sale.getId(), sale);
        System.out.println("Vente ajoutée : " + sale);
        return sale;
    }

    public Sale updateSale(Long id, Sale sale) {
        if (!sales.containsKey(id)) {
            return null;
        }
        sale.setId(id);
        sale.setTotal(sale.getQuantity() * sale.getPrice());
        sales.put(id, sale);
        System.out.println("Vente mise à jour : " + sale);
        return sale;
    }

    public boolean deleteSale(Long id) {
        if (sales.containsKey(id)) {
            sales.remove(id);
            System.out.println("Vente supprimée avec l'id : " + id);
            return true;
        }
        return false;
    }

    public int countSales() {
        return sales.size();
    }
}