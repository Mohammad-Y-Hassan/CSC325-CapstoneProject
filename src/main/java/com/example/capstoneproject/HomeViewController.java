package com.example.capstoneproject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class HomeViewController extends SceneLoader {

    @FXML
    private TextField SearchField;

    @FXML
    private ImageView uploadPostPic;

    @FXML
    private Text maxLimitText;

    @FXML
    private GridPane productGridPane;

    @FXML
    private Label ItemNumberLabel;

    @FXML
    private Text nameTextField;

    private int itemCount = 0;
    private int row = 0;
    private int column = 0;
    StorageManager storageManager = StorageManager.getInstance(); // Storage manager instance


    @FXML
    public void initialize() {
        productGridPane.getChildren().clear();
        maxLimitText.setText("");
        try {
            List<SharedModel.Product> products = StorageManager.getInstance().loadProducts();
            if (products.isEmpty()) {
                System.out.println("No products loaded from storage.");
            }
            SharedModel.getInstance().setProducts(products);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load products: " + e.getMessage());
        }
        loadProducts();
        nameTextField.setText(SharedModel.getInstance().getFirstName());
        updateItemCountDisplay();
        setupSearchBar();
    }

    public void loadProducts() {
        Platform.runLater(() -> {
            List<SharedModel.Product> products = SharedModel.getInstance().getProducts();
            if (products.isEmpty()) {
                System.out.println("SharedModel has no products to display.");
            }
            for (SharedModel.Product product : products) {
                System.out.println("Loading product: " + product.getTitle());
                addProductToGrid(product.getImageURI(), product.getTitle(), product.getPrice());
            }
        });
    }

    private void addProductToGrid(String imageURI, String title, String price) {
        ImageView imageView = new ImageView(new Image(imageURI));
        imageView.setFitHeight(100);
        imageView.setFitWidth(140);
        imageView.setPreserveRatio(true);

        Label titleLabel = new Label(title);
        titleLabel.setMaxWidth(Double.MAX_VALUE);

        Label priceLabel = new Label(price);
        priceLabel.setMaxWidth(Double.MAX_VALUE);

        Button addToCartButton = new Button("Add to cart");
        addToCartButton.setMaxWidth(Double.MAX_VALUE);

        // Set the product data as user data for the VBox
        SharedModel.Product product = new SharedModel.Product(imageURI, title, price);
        VBox vbox = new VBox(5, imageView, titleLabel, priceLabel, addToCartButton);
        vbox.setUserData(product);

        addToCartButton.setOnAction(this::addItemToCart);
        productGridPane.add(vbox, productGridPane.getChildren().size() % 4, productGridPane.getChildren().size() / 4);
    }


    private void setupSearchBar() {
        SearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(newValue.toLowerCase());
        });
    }

    private void searchProducts(String searchText) {
        for (Node node : productGridPane.getChildren()) {
            VBox vbox = (VBox) node;
            Label titleLabel = (Label) vbox.getChildren().get(1);
            vbox.setVisible(titleLabel.getText().toLowerCase().contains(searchText));
        }
    }

    public void addItemToCart(ActionEvent event) {
        itemCount++;
        Button btn = (Button) event.getSource();
        VBox vbox = (VBox) btn.getParent();
        SharedModel.Product product = (SharedModel.Product) vbox.getUserData();

        if (product == null) {
            System.out.println("Error: Product data is missing!");
            return; // Exit the function to avoid adding a null product
        }

        // Log successful retrieval of product
        System.out.println("Adding product to cart: " + product.getTitle() + " at $" + product.getPrice());

        // Adding item to cart
        SharedModel.getInstance().getCart().addItem(product);
        SharedModel.getInstance().notifyCartListeners();
        updateItemCountDisplay();
        try {
            storageManager.saveProducts(SharedModel.getInstance().getProducts());
        } catch (IOException e) {
            System.out.println("Failed to save products: " + e.getMessage());
        }
        SharedModel.getInstance().notifyListeners(); // Notify UI update
    }

    public void updateItemCountDisplay() {
        if (itemCount > 26) {
            maxLimitText.setStyle("-fx-text-fill: red;");
            maxLimitText.setText("MAX LIMIT");
        } else {
            ItemNumberLabel.setText(String.valueOf(itemCount));
        }
    }
    public void uploadPost(MouseEvent event) throws IOException {
        toUploadPostView(event);
    }
    public void toProfile(MouseEvent event) throws IOException{
        toProfileView(event);
    }
    public void toCart(MouseEvent event) throws IOException {
        toCartView(event);
    }
}
