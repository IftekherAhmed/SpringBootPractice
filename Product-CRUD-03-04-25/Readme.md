# API Documentation

![API Icon](https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/icons/gear-fill.svg) 

## Category APIs

### Endpoints

| HTTP Method | Endpoint             | Description           | Request Body                     |
|-------------|----------------------|-----------------------|----------------------------------|
| POST        | `/api/categories`    | Create a new category | `{ "name": "Electronics" }`     |
| GET         | `/api/categories`    | Get all categories    | None                             |
| GET         | `/api/categories/{id}` | Get a category by ID  | None                             |
| DELETE      | `/api/categories/{id}` | Delete a category     | None                             |

---

## Product APIs

### Endpoints

| HTTP Method | Endpoint             | Description                                      | Request Body                                                         |
|-------------|----------------------|--------------------------------------------------|----------------------------------------------------------------------|
| POST        | `/api/products`      | Create a new product (must have at least 1 category) | `{ "name": "Laptop", "description": "Gaming Laptop", "price": 1500.0, "categoryIds": [1, 2] }` |
| GET         | `/api/products`      | Get all products                                | None                                                                 |
| GET         | `/api/products/{id}` | Get a product by ID                             | None                                                                 |
| PUT         | `/api/products/{id}` | Update a product                                | `{ "name": "Updated Laptop", "price": 1200.0, "categoryIds": [1] }` |
| DELETE      | `/api/products/{id}` | Delete a product                                | None                                                                 |

---

## Comment APIs

### Endpoints

| HTTP Method | Endpoint                  | Description                | Request Body                      |
|-------------|---------------------------|----------------------------|-----------------------------------|
| POST        | `/api/comments/{productId}` | Add a comment to a product | `{ "content": "Great product!" }` |
| GET         | `/api/comments/{productId}` | Get all comments for a product | None                               |
| DELETE      | `/api/comments/{id}`      | Delete a comment           | None                               |

---

## Sample Data for Categories

| Name              |
|-------------------|
| Electronics       |
| Home Appliances   |
| Gaming            |
| Fashion           |
| Sports            |

---

## Sample Data for Products

| Name          | Description               | Price   | Categories |
|---------------|---------------------------|---------|------------|
| Laptop        | Gaming Laptop             | 1500.0  | [1, 3]     |
| Smartphone    | Latest 5G Phone           | 1200.0  | [1]        |
| Washing Machine | Front Load Automatic   | 800.0   | [2]        |
| Running Shoes | Lightweight Shoes         | 100.0   | [5]        |
| Smartwatch    | Waterproof Smartwatch     | 200.0   | [1, 5]     |

---

## Sample Data for Comments

| Product ID | Content                          |
|------------|----------------------------------|
| 1          | This laptop is amazing!          |
| 1          | Best performance ever!           |
| 1          | Battery life is outstanding!     |
| 1          | Would recommend to any gamer!    |
| 2          | Love the camera quality!         |
| 2          | Fast charging is really useful.  |
| 2          | Best smartphone I have used!     |
| 2          | Sleek design, very stylish!      |
| 3          | Saves water and energy!          |
| 3          | Very quiet operation!            |
| 3          | Cleans clothes really well!      |
| 3          | A must-have for big families!    |
| 4          | Very comfortable to wear!        |
| 4          | Lightweight and durable!         |
| 4          | Best shoes for running!          |
| 4          | Good grip, prevents slipping.    |
| 5          | Best smartwatch ever!            |
| 5          | Tracks steps accurately!         |
| 5          | Very stylish and waterproof!     |
| 5          | Battery lasts all day!           |