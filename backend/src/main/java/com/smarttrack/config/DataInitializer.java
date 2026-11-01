package com.smarttrack.config;

import com.smarttrack.entity.*;
import com.smarttrack.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final InventoryBatchRepository inventoryBatchRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initializeUsers();
        initializeCategoriesAndSuppliers();
        initializeProductsAndBatches();
    }

    private void initializeUsers() {
        User admin = userRepository.findByUsername("admin").orElseGet(User::new);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEmail("admin@smarttrack.com");
        admin.setFullName("System Admin");
        admin.setRole(User.Role.ADMIN);
        admin.setIsActive(true);
        userRepository.save(admin);
        log.info("Default admin user created/updated: admin / admin123");
    }

    private void initializeCategoriesAndSuppliers() {
        createCategoryIfAbsent("Food & Beverage", "Perishable food items and beverages", "#4CAF50");
        createCategoryIfAbsent("Medicine & Pharma", "Pharmaceutical products and medicines", "#2196F3");
        createCategoryIfAbsent("Cosmetics", "Beauty and personal care products", "#E91E63");
        createCategoryIfAbsent("Electronics", "Electronic components and devices", "#FF9800");

        createSupplierIfAbsent("DairyFarm Inc.", "John Smith", "john@dairyfarm.com", "+1-555-0101");
        createSupplierIfAbsent("MediCorp", "Sarah Johnson", "sarah@medicorp.com", "+1-555-0102");
        createSupplierIfAbsent("TechWorld", "Mike Chen", "mike@techworld.com", "+1-555-0103");
    }

    private void initializeProductsAndBatches() {
        if (productRepository.count() == 0) {
            Category food = categoryRepository.findByName("Food & Beverage").orElse(null);
            Category pharma = categoryRepository.findByName("Medicine & Pharma").orElse(null);
            Category cosmetics = categoryRepository.findByName("Cosmetics").orElse(null);
            Category electronics = categoryRepository.findByName("Electronics").orElse(null);

            Supplier dairy = supplierRepository.findAll().stream()
                    .filter(s -> s.getName().equals("DairyFarm Inc.")).findFirst().orElse(null);
            Supplier medi = supplierRepository.findAll().stream()
                    .filter(s -> s.getName().equals("MediCorp")).findFirst().orElse(null);
            Supplier tech = supplierRepository.findAll().stream()
                    .filter(s -> s.getName().equals("TechWorld")).findFirst().orElse(null);

            Product milk = createProduct("Fresh Milk 1L", "Fresh whole milk", food, dairy, "PK-2023-001", "Liter", 10);
            Product painRelief = createProduct("Pain Relief Tablets", "500mg Paracetamol", pharma, medi, "PP-102-X", "Box", 50);
            Product cheese = createProduct("Cheddar Cheese", "Aged cheddar cheese", food, dairy, "OP-221", "Pack", 20);
            Product moisturizer = createProduct("Face Moisturizer", "Hydrating cream", cosmetics, tech, "CS-191", "Bottle", 30);
            Product mouse = createProduct("Wireless Mouse", "Optical wireless mouse", electronics, tech, "EL-555", "Unit", 50);

            // Batches
            createBatch(milk, "#PK-2023-001", 45, new BigDecimal("2.50"),
                    LocalDate.now().minusDays(15), LocalDate.now().plusDays(10),
                    InventoryBatch.BatchStatus.NEAR_EXPIRY, "Cold Storage A1");

            createBatch(painRelief, "#PP-102-X", 120, new BigDecimal("8.99"),
                    LocalDate.now().minusMonths(6), LocalDate.now().plusDays(20),
                    InventoryBatch.BatchStatus.NEAR_EXPIRY, "Pharmacy Vault B3");

            createBatch(cheese, "#OP-221", 12, new BigDecimal("5.75"),
                    LocalDate.now().minusMonths(2), LocalDate.now().minusDays(5),
                    InventoryBatch.BatchStatus.EXPIRED, "Cold Storage A2");

            createBatch(moisturizer, "#CS-191", 8, new BigDecimal("15.99"),
                    LocalDate.now().minusMonths(3), LocalDate.now().plusMonths(18),
                    InventoryBatch.BatchStatus.GOOD, "Cosmetics Shelf C2");

            createBatch(mouse, "#EL-555", 50, new BigDecimal("12.50"),
                    LocalDate.now().minusMonths(1), LocalDate.now().plusYears(3),
                    InventoryBatch.BatchStatus.GOOD, "Electronics Warehouse D1");

            log.info("Sample products and inventory batches seeded successfully!");
        }
    }

    private Category createCategoryIfAbsent(String name, String description, String color) {
        return categoryRepository.findByName(name).orElseGet(() -> {
            Category cat = new Category();
            cat.setName(name);
            cat.setDescription(description);
            cat.setColor(color);
            return categoryRepository.save(cat);
        });
    }

    private Supplier createSupplierIfAbsent(String name, String contact, String email, String phone) {
        Optional<Supplier> existing = supplierRepository.findAll().stream()
                .filter(s -> s.getName().equals(name)).findFirst();
        if (existing.isPresent()) {
            return existing.get();
        }
        Supplier sup = new Supplier();
        sup.setName(name);
        sup.setContactPerson(contact);
        sup.setEmail(email);
        sup.setPhone(phone);
        return supplierRepository.save(sup);
    }

    private Product createProduct(String name, String desc, Category cat, Supplier sup, String sku, String unit, int minStock) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(desc);
        p.setCategory(cat);
        p.setSupplier(sup);
        p.setSku(sku);
        p.setUnit(unit);
        p.setMinStockLevel(minStock);
        return productRepository.save(p);
    }

    private void createBatch(Product product, String batchNo, int qty, BigDecimal price,
                             LocalDate mfg, LocalDate expiry, InventoryBatch.BatchStatus status, String loc) {
        InventoryBatch b = new InventoryBatch();
        b.setProduct(product);
        b.setBatchNumber(batchNo);
        b.setQuantity(qty);
        b.setUnitPrice(price);
        b.setManufacturingDate(mfg);
        b.setExpiryDate(expiry);
        b.setStatus(status);
        b.setStorageLocation(loc);
        inventoryBatchRepository.save(b);
    }
}
