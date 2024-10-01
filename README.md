# **CSC325 Capstone Project**

This repository contains the final project for **CSC325**, a capstone course in computer science. The project demonstrates the development of a **Java-based** desktop application using **JavaFX** for the user interface and **Google Firebase** for backend database management. The application simulates an e-commerce platform where users can create profiles, upload posts, and manage a shopping cart.

## **Project Overview**

The project implements several features, including:

- **User Authentication**: Users can log in and create profiles using a custom user interface with **Google Firebase** as the authentication and user data storage backend.
- **Product Management**: Users can upload items for sale and view available products stored in **Firebase**.
- **Cart Management**: Users can add items to their shopping cart and view/manage their purchases, with cart data synchronized using **Firebase**.

## **Key Components**

### 1. **Controllers**

- `CreateAccountController.java`: Handles the creation of new user accounts using **Firebase Authentication**.
- `LogInController.java`: Manages login logic and user authentication via **Firebase**.
- `ProfileCreationController.java`: Allows users to input and create their profiles, saving the data in **Firebase**.
- `UploadPostController.java`: Handles logic for users to upload new product posts and save product details to **Firebase**.
- `UserCartController.java`: Manages the user's cart, allowing for product addition and removal, with changes reflected in **Firebase**.

### 2. **Models**

- `User.java`: Represents a user with their associated data such as username, password, and cart, stored in **Firebase**.
- `Product.java`: Represents a product uploaded by users, including attributes like price and description, with data persisted in **Firebase**.
- `SharedModel.java`: Stores shared data across different views, such as available products and users, synchronized with **Firebase**.

### 3. **Utilities**

- `SceneLoader.java`: Utility class to load different scenes (views) within the application.
- `StorageManager.java`: Manages data storage and retrieval from **Firebase**, including authentication and product data.

### 4. **Views (FXML Files)**

- `logIn.fxml`: The login screen interface.
- `profileCreation.fxml`: The profile creation screen.
- `uploadPost.fxml`: The interface for uploading products.
- `userCart.fxml`: The interface for viewing the user's shopping cart.

## **Features**

- **User-Friendly Interface**: Built using **JavaFX** to provide a clean and interactive user interface.
- **Firebase Integration**: Uses **Google Firebase** for real-time data synchronization and user authentication.
- **Profile Management**: Users can create, update, and manage their profiles with **Firebase**.
- **Product Management**: Users can upload and view products in a simplified e-commerce interface, with data stored in **Firebase**.
- **Shopping Cart**: Users can add items to their cart and manage purchases with real-time updates using **Firebase**.

## **Firebase Integration**

The project uses **Google Firebase** for:

1. **Authentication**: Securely handles user login and account creation.
2. **Database**: Stores user profile, product, and cart information in real-time using **Firebase Realtime Database**.

## **Technologies Used**

- **Java**: The core language used for the development.
- **JavaFX**: For building the graphical user interface.
- **Google Firebase**: Used for user authentication and real-time database management.
- **JUnit**: Used for testing different components of the application.
