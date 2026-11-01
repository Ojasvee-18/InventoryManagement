# SmartInventory Management System

## Overview
SmartInventory is an enterprise-grade inventory and expiry tracking system built with Java Spring Boot, React, and MySQL. It provides comprehensive batch tracking, expiry management, and real-time analytics.

## Features
- ✅ Real-time Dashboard with Analytics
- ✅ Batch/Lot Number Tracking
- ✅ Expiry Date Management (FEFO/FIFO)
- ✅ Automated Expiry Alerts
- ✅ Multi-Category Inventory Management
- ✅ Wastage Reduction Tracking
- ✅ Role-Based Access Control
- ✅ Comprehensive Reporting
- ✅ RESTful API Architecture
- ✅ Responsive Modern UI

## Technology Stack
- **Backend**: Java 17, Spring Boot 3.2.0
- **Frontend**: React 18, Material-UI, Chart.js
- **Database**: MySQL 8.0
- **Security**: Spring Security with JWT
- **Build Tools**: Maven (Backend), npm (Frontend)


## Installation & Setup

### Prerequisites
- Java 17 or higher
- Node.js 16+ and npm
- MySQL 8.0+
- Maven 3.6+

### Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE smart_inventory;
```

2. Update database credentials in `backend/src/main/resources/application.properties`

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
Backend will run on: http://localhost:8080

### Frontend Setup
```bash
cd frontend
npm install
npm start
```
Frontend will run on: http://localhost:3000

## Default Credentials
- **Username**: admin
- **Password**: admin123

## API Documentation
Once the backend is running, access Swagger UI at:
http://localhost:8080/swagger-ui.html

## Key Endpoints
- `POST /api/auth/login` - User authentication
- `GET /api/dashboard/overview` - Dashboard metrics
- `GET /api/inventory` - Get all inventory items
- `GET /api/inventory/near-expiry` - Get items nearing expiry
- `POST /api/inventory` - Add new inventory item
- `GET /api/reports/wastage` - Wastage trend report
- `GET /api/alerts` - Get active alerts

## Configuration

### Application Properties
Edit `backend/src/main/resources/application.properties`:
- Database connection settings
- JWT secret key
- Server port
- Expiry alert thresholds

### Environment Variables
```bash
DB_HOST=localhost
DB_PORT=3306
DB_NAME=smart_inventory
DB_USERNAME=root
DB_PASSWORD=yourpassword
JWT_SECRET=your-secret-key
```

## Features in Detail

### 1. Dashboard Overview
- Total stock count across categories
- Inventory value tracking
- Near expiry alerts (30 days)
- Expired items count
- 6-month expiry forecast
- Stock distribution by category

### 2. Inventory Management
- CRUD operations for products
- Batch/lot number tracking
- Manufacturing and expiry dates
- Quantity management
- Category-based organization
- Search and filter capabilities

### 3. Expiry Tracking
- FEFO (First Expiry, First Out) logic
- Automated expiry alerts
- Configurable alert thresholds
- Critical expiry reports
- Restock recommendations

### 4. Reports & Analytics
- Inventory value by category
- Wastage reduction trends
- Expiry analytics
- Stock movement reports
- Supplier performance

### 5. User Management
- Role-based access (Admin, Manager, Operator)
- Secure authentication with JWT
- Activity audit logs

## Business Logic

### FEFO Implementation
The system automatically prioritizes items based on expiry dates:
1. Items expiring soonest are flagged first
2. Restock recommendations for expired/near-expiry items
3. Wastage calculation and trending

### Alert System
- **Critical**: Items expired
- **Warning**: Items expiring within 30 days
- **Info**: Items expiring within 60 days

## Testing
```bash
# Backend tests
cd backend
mvn test

# Frontend tests
cd frontend
npm test
```

## Deployment

### Docker Deployment (Optional)
```bash
docker-compose up -d
```

### Production Build
```bash
# Backend
mvn clean package -DskipTests

# Frontend
npm run build
```

## Troubleshooting

### Common Issues
1. **Database connection failed**: Check MySQL is running and credentials are correct
2. **Port already in use**: Change port in application.properties
3. **CORS errors**: Verify frontend URL in CorsConfig.java
4. **JWT token expired**: Token validity is 24 hours, re-login required

## Contributing
1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License.


## Roadmap
- [ ] Mobile app (React Native)
- [ ] Barcode/QR code scanning
- [ ] Multi-warehouse support
- [ ] Advanced predictive analytics
- [ ] Integration with ERP systems
- [ ] Automated reorder system
- [ ] Email/SMS notifications

## Credits
Developed for enterprise inventory management with focus on expiry tracking and waste reduction.

---
**Version**: 1.0.0  
**Last Updated**: December 2025



# Smartinventory — Enterprise Inventory & Expiry Tracking System
## Comprehensive Engineering & System Architecture Project Report

---

## Executive Summary

**SmartInventory** is an enterprise-grade inventory and expiry management software platform engineered to mitigate product obsolescence, eliminate financial wastage, and enforce regulatory compliance in perishable goods supply chains. Operating across industries such as pharmaceuticals, food and beverage, chemicals, and retail, the system automates inventory auditing through batch-level tracking and dynamic **First Expiry, First Out (FEFO)** prioritization.

The system is developed on a modern decoupled architecture: a reactive Single Page Application (SPA) built with **React 18** and **Material-UI (MUI)** on the presentation tier, coupled with a robust enterprise micro-service backend built with **Java 17**, **Spring Boot 3.2.0**, **Spring Data JPA**, and **Spring Security** backed by a relational database schema (**MySQL 8.0** / **H2**). Communication is handled via RESTful web services secured with JSON Web Token (JWT) stateless authentication.

---

## 1. Introduction

### 1.1 Problem Context
Traditional inventory management systems operate predominantly on simple quantity counting or standard First-In, First-Out (FIFO) paradigms. In sectors where inventory possesses definite expiration lifespans, FIFO introduces critical operational failures:
- Newer batches may be utilized before older stock, causing older items to expire unnoticed on warehouse shelves.
- Manual inspection cycles are resource-intensive, error-prone, and incapable of scaling across multiple stock-keeping units (SKUs) and batch variants.
- Financial write-offs due to spoilage directly degrade corporate operating margins.
- Regulatory penalties and health risks emerge if expired pharmaceuticals or food supplies enter commercial distribution.

### 1.2 Project Objectives
1. **Batch/Lot Traceability**: Maintain granular lifecycle tracking for every physical batch, including unique batch identifiers, manufacturing dates, expiry dates, and unit acquisition costs.
2. **Automated FEFO Pipeline**: Automatically classify inventory status (`GOOD`, `NEAR_EXPIRY`, `EXPIRED`) and dynamically prioritize dispatch workflows based on remaining shelf life.
3. **Real-Time Predictive Analytics**: Provide operational managers with interactive dashboards, 6-month expiry projection models, and category-wise capital allocation analytics.
4. **Automated Multi-Tier Alerting**: Flag critical operational thresholds (imminent expiry within 30/60 days and stock deprivations) with persistent acknowledgment tracking.
5. **Role-Based Governance**: Ensure strict separation of privileges between administrators, warehouse managers, and inventory operators through cryptographic access tokens.
6. **Integrated Operational Intelligence**: Support on-demand inventory queries through an embedded natural-language rule-based assistant.

---

## 2. System Architecture

Smartk follows a strict **Three-Tier Layered Client-Server Architecture** designed for horizontal scalability, high maintainability, and clean separation of concerns.

```
┌────────────────────────────────────────────────────────────────────────┐
│                        PRESENTATION TIER                               │
│                   React 18 Single Page Application                     │
│  ┌────────────────────┬────────────────────┬────────────────────────┐  │
│  │   Pages & Views    │ Material-UI Theme  │  Data Visualizations   │  │
│  │ Dashboard, Reports │ Responsive Layout  │   Chart.js / Canvas    │  │
│  └─────────┬──────────┴─────────┬──────────┴───────────┬────────────┘  │
│            └────────────────────┼──────────────────────┘               │
│                                 ▼                                      │
│                 Axios Interceptor & API Client Layer                   │
└─────────────────────────────────┬──────────────────────────────────────┘
                                  │ HTTPS / JSON
                                  │ Authorization: Bearer <JWT>
┌─────────────────────────────────▼──────────────────────────────────────┐
│                        APPLICATION TIER                                │
│                   Spring Boot 3.2.0 Microservice                       │
│  ┌──────────────────────────────────────────────────────────────────┐  │
│  │           Security Filter Chain (CORS, CSRF, JWT Filter)         │  │
│  └──────────────────────────────┬───────────────────────────────────┘  │
│                                 ▼                                      │
│  ┌──────────────────────────────────────────────────────────────────┐  │
│  │      REST Controllers (/api/auth, /api/inventory, /api/dashboard)│  │
│  └──────────────────────────────┬───────────────────────────────────┘  │
│                                 ▼                                      │
│  ┌──────────────────────────────────────────────────────────────────┐  │
│  │       Service Layer (Business Logic, FEFO Rules, Forecasting)    │  │
│  └──────────────────────────────┬───────────────────────────────────┘  │
│                                 ▼                                      │
│  ┌──────────────────────────────────────────────────────────────────┐  │
│  │    Data Access Layer (Spring Data JPA Repositories & Auditing)   │  │
│  └──────────────────────────────┬───────────────────────────────────┘  │
└─────────────────────────────────┼──────────────────────────────────────┘
                                  │ JDBC / Hibernate ORM
┌─────────────────────────────────▼──────────────────────────────────────┐
│                           DATA TIER                                    │
│                 Relational Database Management System                  │
│       MySQL 8.0 (Production) / H2 In-Memory (Dev & Testing)            │
│  ┌──────────────┬──────────────┬──────────────────┬─────────────────┐  │
│  │    users     │  categories  │    suppliers     │    products     │  │
│  ├──────────────┼──────────────┼──────────────────┼─────────────────┤  │
│  │inv_batches   │stock_movement│      alerts      │   audit_logs    │  │
│  └──────────────┴──────────────┴──────────────────┴─────────────────┘  │
└────────────────────────────────────────────────────────────────────────┘
```

---

## 3. Comprehensive Database Schema (SQL DDL)

The data tier is structured according to third normal form (3NF) principles, enforcing relational integrity through explicit primary keys, foreign key constraints, and unique indices across eight tables.

### 3.1 Entity Relationship Model

```
 ┌───────────────┐          ┌───────────────┐
 │  categories   │          │   suppliers   │
 └───────┬───────┘          └───────┬───────┘
         │ 1                        │ 1
         │                          │
         │ N                        │ N
 ┌───────▼──────────────────────────▼───────┐
 │                 products                 │
 └────────────────────┬─────────────────────┘
                      │ 1
                      │
                      │ N
 ┌────────────────────▼─────────────────────┐       ┌───────────────┐
 │            inventory_batches             │       │     users     │
 └────────┬─────────────────────────┬───────┘       └───────┬───────┘
          │ 1                       │ 1                     │ 1
          │                         │                       │
          │ N                       │ N                     │ N
 ┌────────▼──────────────┐   ┌──────▼───────────────┐       │
 │    stock_movements    │   │        alerts        │◄──────┘ (acknowledged_by)
 └───────────────────────┘   └──────────────────────┘
                                        ▲
 ┌───────────────────────┐              │
 │      audit_logs       │◄─────────────┘ (user_id)
 └───────────────────────┘
```

### 3.2 SQL Schema Definition Scripts

```sql
-- =====================================================================
-- SmartInventory Database Schema Definition (schema.sql)
-- Target RDBMS: MySQL 8.0+ / H2 (MySQL Mode)
-- =====================================================================

-- 1. Users & Authentication Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    full_name VARCHAR(100),
    role VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 2. Product Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    color VARCHAR(7),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 3. Supplier Master Table
CREATE TABLE IF NOT EXISTS suppliers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 4. Products Catalog Table
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    category_id BIGINT,
    supplier_id BIGINT,
    sku VARCHAR(50) UNIQUE,
    unit VARCHAR(20),
    min_stock_level INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) 
        REFERENCES categories(id) ON DELETE SET NULL,
    CONSTRAINT fk_product_supplier FOREIGN KEY (supplier_id) 
        REFERENCES suppliers(id) ON DELETE SET NULL
);

-- 5. Inventory Batches Table (Core Expiry & FEFO Entity)
CREATE TABLE IF NOT EXISTS inventory_batches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    batch_number VARCHAR(100) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    unit_price DECIMAL(10, 2),
    manufacturing_date DATE,
    expiry_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'GOOD',
    storage_location VARCHAR(100),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_batch_product FOREIGN KEY (product_id) 
        REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT uq_product_batch UNIQUE (product_id, batch_number)
);

-- 6. Stock Movements Table (Audit & Inventory In/Out Ledger)
CREATE TABLE IF NOT EXISTS stock_movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    batch_id BIGINT NOT NULL,
    movement_type VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    reference_number VARCHAR(100),
    notes TEXT,
    user_id BIGINT,
    movement_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_movement_batch FOREIGN KEY (batch_id) 
        REFERENCES inventory_batches(id) ON DELETE CASCADE,
    CONSTRAINT fk_movement_user FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE SET NULL
);

-- 7. System Alerts & Warnings Table
CREATE TABLE IF NOT EXISTS alerts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    batch_id BIGINT NOT NULL,
    alert_type VARCHAR(20) NOT NULL,
    severity VARCHAR(20) NOT NULL,
    message TEXT NOT NULL,
    is_acknowledged BOOLEAN DEFAULT FALSE,
    acknowledged_by BIGINT,
    acknowledged_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_alert_batch FOREIGN KEY (batch_id) 
        REFERENCES inventory_batches(id) ON DELETE CASCADE,
    CONSTRAINT fk_alert_acknowledged_user FOREIGN KEY (acknowledged_by) 
        REFERENCES users(id) ON DELETE SET NULL
);

-- 8. Enterprise Audit Logs Table
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    action VARCHAR(50) NOT NULL,
    entity_type VARCHAR(50),
    entity_id BIGINT,
    old_value TEXT,
    new_value TEXT,
    ip_address VARCHAR(45),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_audit_user FOREIGN KEY (user_id) 
        REFERENCES users(id) ON DELETE SET NULL
);
```

### 3.3 Data Dictionary & Constraints Analysis

| Table | Attribute | Data Type | Nullable | Description / Integrity Rules |
|---|---|---|:---:|---|
| `users` | `id` | BIGINT | No | Surrogate Primary Key (Auto-Increment). |
| | `username` | VARCHAR(50) | No | Unique identity login handle. |
| | `password` | VARCHAR(255) | No | One-way cryptographic BCrypt hash string. |
| | `role` | VARCHAR(20) | No | Authorization role: `ROLE_ADMIN`, `ROLE_MANAGER`, `ROLE_OPERATOR`. |
| `categories` | `color` | VARCHAR(7) | Yes | Hex color code (`#RRGGBB`) utilized by frontend charts. |
| `products` | `sku` | VARCHAR(50) | Yes | Stock Keeping Unit (Globally unique constraint). |
| | `min_stock_level`| INT | Yes | Threshold below which `LOW_STOCK` alerts trigger. |
| `inventory_batches` | `batch_number` | VARCHAR(100) | No | Batch/Lot identifier assigned by manufacturer. |
| | `expiry_date` | DATE | No | Hard expiry cut-off date used for FEFO prioritization. |
| | `status` | VARCHAR(20) | Yes | Evaluated status: `GOOD`, `NEAR_EXPIRY`, `EXPIRED`. |
| | `(product_id, batch_number)` | — | No | Composite Unique Index preventing duplicate batch entries per SKU. |
| `alerts` | `alert_type` | VARCHAR(20) | No | Categorized: `EXPIRY`, `LOW_STOCK`, `RESTOCK_RECOMMENDATION`. |
| | `severity` | VARCHAR(20) | No | Urgency marker: `CRITICAL`, `WARNING`, `INFO`. |

---

## 4. Backend Engineering & Architecture

### 4.1 Framework & Core Stack
The backend is constructed as a modern enterprise service running on Java 17 and Spring Boot 3.2.0:
- **Spring Web MVC**: REST API contract design, content negotiation, serialization with Jackson.
- **Spring Security 6.x**: Declarative, role-based security filters with stateless session isolation.
- **Spring Data JPA & Hibernate 6**: Object-Relational Mapping, criteria builders, and custom JPQL query execution.
- **JJWT 0.12.5**: Modern cryptographic JSON Web Token signing (`HMAC-SHA512`).
- **Spring Scheduling & Auditing**: Automatic entity timestamps (`@EnableJpaAuditing`) and background cron readiness (`@EnableScheduling`).
- **SpringDoc OpenAPI 3.0**: Automated API endpoint specification and interactive Swagger UI interface.

### 4.2 Layered Architectural Implementation

#### 1. Security & Authentication Layer
- **`JwtUtils`**: Manages cryptographic token generation, expiration timestamps (24 hours), and token validation using dynamic HMAC-SHA keys.
- **`JwtAuthenticationFilter`**: Extends Spring's `OncePerRequestFilter`. Intercepts incoming HTTP requests, extracts the Bearer token from the `Authorization` header, parses user claims, validates signatures, and injects the authenticated `UserDetailsImpl` into the thread-local `SecurityContextHolder`.
- **`SecurityConfig`**: Defines CORS configurations, disables CSRF (stateless token authentication requirement), sets `SessionCreationPolicy.STATELESS`, disables HTTP frame-options for H2 console accessibility, and establishes granular endpoint authorizations:
  - Public endpoints: `/api/auth/**`, `/api/dashboard/**`, `/swagger-ui/**`, `/v3/api-docs/**`.
  - Admin endpoints: `/api/admin/**`.
  - Authenticated endpoints: All remaining API routes.

#### 2. Service Layer & Business Domain
- **`InventoryService`**:
  - Handles batch persistence, transactional modifications (`@Transactional`), and deletion cascades.
  - Implements dynamic FEFO status evaluation on entity persistence:
    $$\text{Status} = \begin{cases} \text{EXPIRED}, & \text{if } \text{expiryDate} < \text{today} \\ \text{NEAR\_EXPIRY}, & \text{if } \text{expiryDate} \le \text{today} + 30\text{ days} \\ \text{GOOD}, & \text{otherwise} \end{cases}$$
  - Computes dynamic shelf-life metrics, notably `daysUntilExpiry = ChronoUnit.DAYS.between(today, expiryDate)`.
- **`DashboardService`**:
  - Aggregates operational key performance indicators (KPIs) through high-performance repository queries:
    - Active batch totals: `batchRepository.countActiveBatches()`
    - Aggregate inventory capital valuation: `batchRepository.getTotalInventoryValue()`
    - Near-expiry item count: `batchRepository.countNearExpiryBatches(today, today + 30 days)`
    - Expired batch tally: `batchRepository.countExpiredBatches(today)`
  - Determines system health status (`HEALTHY` $\rightarrow$ `WARNING` $\rightarrow$ `CRITICAL`).
  - Generates a rolling 6-month monthly expiry forecast model.
  - Assembles category-level capital distribution mapping.
- **`ChatbotService`**:
  - Provides a domain-aware natural-language parsing engine.
  - Utilizes token extraction to parse conversational requests (e.g., extracting integer days from *"items expiring in 14 days"*).
  - Retrieves live operational numbers directly from the database and returns structured answers explaining FEFO, FIFO, batch concepts, and financial valuations.

#### 3. API Controller Specifications

| HTTP Method | API Route | Request Body / Params | Role Required | Functionality |
|:---:|---|---|:---:|---|
| `POST` | `/api/auth/login` | `LoginRequest` (username, password) | Anonymous | Authenticates credentials and returns JWT bearer token. |
| `POST` | `/api/auth/register` | `RegisterRequest` (username, email, password, role) | Anonymous / Admin | Registers a new system account with BCrypt password encryption. |
| `GET` | `/api/dashboard/overview` | None | Authenticated / Public | Returns high-level metrics, 6-month forecast, and category distributions. |
| `GET` | `/api/inventory` | None | Authenticated | Retrieves all inventory batches sorted by expiry ascending (FEFO order). |
| `GET` | `/api/inventory/near-expiry` | `?days=30` | Authenticated | Filters inventory batches with remaining shelf-life within the threshold. |
| `GET` | `/api/inventory/expired` | None | Authenticated | Returns all batches that have passed expiration. |
| `GET` | `/api/inventory/search` | `?keyword={term}` | Authenticated | Full-text query on product names and batch numbers. |
| `POST` | `/api/inventory` | `InventoryBatch` payload | Authenticated | Creates a new inventory batch and computes initial status. |
| `PUT` | `/api/inventory/{id}` | `InventoryBatch` payload | Authenticated | Updates quantity, unit price, dates, or storage locations. |
| `DELETE`| `/api/inventory/{id}` | Path Variable (`id`) | Authenticated | Deletes a batch and cascades related alert dependencies. |
| `POST` | `/api/chat` | `ChatRequest` (message, mode) | Authenticated | Natural language query assistant interface. |

---

## 5. Frontend Engineering & Architecture

### 5.1 Technology Stack & Architectural Patterns
The client interface is developed using **React 18** with functional components, React Hooks (`useState`, `useEffect`, `useCallback`, `useMemo`), and single-direction state propagation.
- **Component System**: Material-UI (MUI v5) providing responsive design, accessible form controls, and CSS-in-JS theming.
- **Navigation & Routing**: `react-router-dom` (v6) with declarative route definitions and client-side authentication guards.
- **Data Visualizations**: `Chart.js` coupled with `react-chartjs-2` for HTML5 canvas rendering of trend charts, distributions, and forecasts.
- **Data Export Utilities**: Integrated `jsPDF`, `jspdf-autotable`, and `xlsx` (SheetJS) for generating audit-ready PDF summaries and Excel workbooks directly in the browser.

### 5.2 Application State & Routing Flow

```
                      ┌──────────────────────┐
                      │    Browser Client    │
                      └──────────┬───────────┘
                                 │
                        [App Initialization]
                                 │
                     Check localStorage('token')
                                 │
                 ┌───────────────┴───────────────┐
                 │                               │
            Token Absent                    Token Present
                 │                               │
                 ▼                               ▼
          /login or /register            /dashboard, /inventory, /reports
        ┌───────────────────┐          ┌───────────────────────────────────┐
        │ Public Auth Pages │          │ Authenticated Layout Framework    │
        │ - Login.jsx       │          │ - Topbar & Active User Info       │
        │ - Register.jsx    │          │ - Collapsible Navigation Drawer   │
        │ - ForgotPassword  │          │ - Embedded ChatWidget             │
        └───────────────────┘          │ - Global Toast Notification Center│
                                       └───────────────────────────────────┘
```

### 5.3 Detailed Component Breakdown

#### 1. Core Framework Components
- **`Layout.jsx`**: Provides a persistent responsive shell consisting of a collapsible sidebar, app bar, user profile summary, role indicator, and sign-out action handler.
- **`ToastProvider.jsx`**: Global notification context wrapping the React tree to deliver snackbar notifications for API errors, successful updates, and warnings.
- **`ChatWidget.jsx`**: Floating assistance drawer enabling conversational inquiries regarding FEFO methodologies and near-expiry thresholds.
- **`ConfirmDialog.jsx`**: Reusable modal dialog preventing accidental deletion of inventory batches.
- **`QRScannerDialog.jsx`**: Camera-supported barcode/QR scanning integration for rapid physical product reconciliation.

#### 2. Application Views & Pages
- **`Login.jsx` & `Register.jsx`**: Modern authentication pages styled with gradient backgrounds, credential validation, role selectors, and animated error alerts.
- **`Dashboard.jsx`**: Operational command center displaying:
  - Metric summary cards: Total Stock, Total Inventory Valuation ($), Near Expiry Warning Count, Expired Batch Count.
  - Interactive Expiry Forecast (Line chart displaying prospective volume of expired stock across subsequent 6 months).
  - Stock Distribution by Category (Doughnut chart with category-coded color indicators).
- **`InventoryList.jsx`**: Comprehensive data management grid featuring:
  - Real-time search query filtering across batch codes and SKU titles.
  - Quick-filter tab buttons (`All Batches`, `Near Expiry`, `Expired`).
  - Color-coded MUI Status Chips (`GOOD` in green, `NEAR_EXPIRY` in orange, `EXPIRED` in red).
  - Add/Edit modal dialog with automated date constraints.
  - Inline batch deletion with security confirmation.
- **`Reports.jsx`**: Analytical reporting page featuring:
  - Category-wise valuation bar charts.
  - Historical wastage reduction line graphs.
  - Critical expiry tables highlighting imminent stock hazards.
  - Client-side PDF export with tabular formatting and Excel workbook download.

### 5.4 Client-Side API Communication & Interceptors
The communication layer in `services/api.js` is structured around a centralized Axios instance:
1. **Dynamic Environment Targeting**: Bases requests on `process.env.REACT_APP_API_URL` with a local fallback (`http://localhost:8080/api`).
2. **Request Interceptor**: Reads the JWT token from `localStorage` and appends `Authorization: Bearer <token>` to all outbound HTTP requests.
3. **Response Interceptor (Session Expiration Handling)**: Catches HTTP `401 Unauthorized` responses globally, flushes stale local storage tokens, and redirects the client application back to `/login`.

---

## 6. Core Business Logic — FEFO & Forecasting

### 6.1 FEFO (First Expiry, First Out) Algorithm
Standard warehouses often deplete stock based on FIFO (First In, First Out). SmartInventory replaces this with an algorithmic FEFO framework:

```
[Inbound Batch Received]
           │
           ▼
[Persist Batch with Explicit Expiry Date]
           │
           ▼
[Dynamic Status Evaluation]
  ├── Expiry < Current Date             ──► Assign Status = EXPIRED
  ├── Current Date <= Expiry <= +30 Days──► Assign Status = NEAR_EXPIRY
  └── Expiry > Current Date + 30 Days   ──► Assign Status = GOOD
           │
           ▼
[Dispatch Prioritization Engine]
  Sort Batches WHERE Status != 'EXPIRED' 
  ORDER BY expiry_date ASC, quantity DESC
           │
           ▼
[Earliest Expiring Stock Allocated First]
```

### 6.2 Expiry Forecast Calculation
The backend generates rolling six-month projections by executing date-partitioned bucket calculations:
$$\text{Volume}(m) = \sum_{b \in B} [b.\text{expiry\_date} \in [\text{StartOfMonth}(m), \text{EndOfMonth}(m)]]$$
This mathematical model enables procurement teams to identify impending expiration clusters months in advance, allowing for targeted promotional markdowns or cross-facility reallocations.

---

## 7. Security Implementation & Governance

| Security Domain | Strategy & Implementation | Threat Mitigated |
|---|---|---|
| **Identity Verification** | Stateless JWT signed via `HMAC-SHA512` with 24-hour expiration. | Session Hijacking & Credential Replay |
| **Password Storage** | BCrypt Adaptive Salted Hashing ($2^{10}$ key iterations). | Rainbow Table & Dictionary Attacks |
| **Data Protection** | Parameterized JPA / Hibernate Criteria queries throughout data layer. | SQL Injection (SQLi) |
| **Browser Security** | Spring Security HTTP Header configuration (`X-Frame-Options: DENY`, `X-Content-Type-Options: nosniff`). | Clickjacking & MIME-type Sniffing |
| **Cross-Origin Policy** | Configurable CORS policy validating allowed origin domains. | Cross-Origin Data Theft |
| **Access Control (RBAC)** | Method-level security (`@EnableMethodSecurity`, `hasRole('ADMIN')`). | Privilege Escalation |

---

## 8. Quality Assurance & System Verification

### 8.1 Backend Verification
The backend includes a comprehensive testing suite utilizing **JUnit 5**, **Mockito**, and **Spring Boot Test**:
- **Service Unit Tests**: Mock repositories to validate business rule edge cases (e.g., verifying that a batch expiring tomorrow receives `NEAR_EXPIRY` while a batch expiring yesterday receives `EXPIRED`).
- **Security Integration Tests**: Verify that requests without a Bearer token receive `403 Forbidden` / `401 Unauthorized` on protected endpoints.
- **Repository Integration Tests**: Validate custom JPQL query execution against active in-memory relational databases.

### 8.2 Frontend Validation
- **React Testing Library & Jest**: Verifies component rendering, user event simulations, form validation warnings, and authentication guard redirects.
- **End-to-End Execution**: Validated through full-stack integration testing with simulated user sessions performing the complete lifecycle: registration $\rightarrow$ login $\rightarrow$ batch entry $\rightarrow$ dashboard rendering $\rightarrow$ report export.

---

## 9. Deployment Architecture

The system is designed for deployment across several cloud topologies:

```
┌─────────────────────────────────────────────────────────────┐
│                      CLIENT TIER                            │
│           Vercel / Netlify Edge Network (CDN)               │
│      Serves optimized static React production bundles       │
│      Dynamic client-side routing via vercel.json rewrites   │
└──────────────────────────────┬──────────────────────────────┘
                               │ HTTPS API Calls
┌──────────────────────────────▼──────────────────────────────┐
│                   APPLICATION CONTAINER                     │
│               Docker / Cloud App Engine (AWS / Render)      │
│      Embedded Tomcat Web Server running Spring Boot JAR     │
│      Configured with dynamic production application.props   │
└──────────────────────────────┬──────────────────────────────┘
                               │ Private VPC / JDBC
┌──────────────────────────────▼──────────────────────────────┐
│                      DATABASE TIER                          │
│               Managed MySQL Service (RDS / Aiven)           │
│      Persistent block storage with automated daily backups  │
└─────────────────────────────────────────────────────────────┘
```

---

## 10. Future Development Roadmap

1. **Native Mobile Clients**: Develop dedicated iOS and Android applications utilizing React Native to support warehouse workers with mobile cameras.
2. **Automated Continuous Reordering**: Integrate automated supplier purchase order generation when stock levels fall below `min_stock_level`.
3. **Multi-Warehouse Spatial Tracking**: Expand the database schema to support multiple facilities, zones, aisles, and bin-level spatial locations.
4. **Push & Webhook Notifications**: Implement real-time transactional alert delivery via WebSockets, SendGrid email services, and Twilio SMS.
5. **Machine Learning Spoilage Optimization**: Implement regression models to predict consumption velocity and dynamically adjust shelf-life thresholds.

---

## 11. Conclusion

The **Inventory & Expiry Tracking System** successfully demonstrates an end-to-end software engineering solution to the complex challenge of perishable goods management. By synthesizing a robust, secure **Spring Boot** application tier with an intuitive, dynamic **React 18** presentation interface, the system automates the complete lifecycle of batch monitoring. 

The implementation of **First Expiry, First Out (FEFO)** data sorting, automated visual health telemetry, and structured SQL relationship integrity guarantees substantial reductions in operational waste, strengthens inventory accuracy, and provides actionable intelligence for modern enterprise supply chain workflows.
