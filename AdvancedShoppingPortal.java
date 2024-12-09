
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class AdvancedShoppingPortal {

    private DefaultListModel<String> productListModel = new DefaultListModel<>();
    private DefaultListModel<String> cartModel = new DefaultListModel<>();
    private Map<String, Double> productPriceMap = new HashMap<>();
    private Map<String, Integer> cartQuantityMap = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdvancedShoppingPortal();
            }
        });
    }

    public AdvancedShoppingPortal() {
        initializeProducts();
        showLoginScreen();
    }

    private void initializeProducts() {
        addProduct("Laptop", 1000.0);
        addProduct("Headphones", 150.0);
        addProduct("Mobile Phone", 800.0);
        addProduct("Refrigerator", 1200.0);
        addProduct("Washing Machine", 900.0);
    }

    private void addProduct(String name, double price) {
        String productDisplay = name + " - $" + price;
        productListModel.addElement(productDisplay);
        productPriceMap.put(name, price);
    }

    private void showLoginScreen() {
        JFrame loginFrame = new JFrame("Shopping Portal - Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(700, 500);
        loginFrame.setLayout(null);

        JPanel backgroundPanel = createGradientPanel();
        backgroundPanel.setBounds(0, 0, 700, 500);
backgroundPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Welcome to Shopping Portal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBounds(50, 30, 600, 50);
        titleLabel.setForeground(Color.BLACK);
        backgroundPanel.add(titleLabel);

        JButton customerLogin = createStyledButton("Customer Login", 150, 150, Color.GREEN, Color.ORANGE);
        customerLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProductPage("Customer");
            }
        });
        backgroundPanel.add(customerLogin);

        JButton retailerLogin = createStyledButton("Retailer Login", 400, 150, Color.BLUE, Color.CYAN);
        retailerLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRetailerPage();
            }
        });
        backgroundPanel.add(retailerLogin);

        JButton exitButton = createStyledButton("Exit", 270, 300, Color.RED, Color.PINK);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        backgroundPanel.add(exitButton);

        loginFrame.add(backgroundPanel);
        loginFrame.setVisible(true);
    }

    private void showRetailerPage() {
        JFrame retailerFrame = new JFrame("Retailer - Add Product");
        retailerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        retailerFrame.setSize(500, 400);
        retailerFrame.setLayout(null);

        JPanel retailerPanel = createGradientPanel();
        retailerPanel.setBounds(0, 0, 500, 400);
        retailerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Add New Product", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(50, 30, 400, 40);
        retailerPanel.add(titleLabel);

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        productNameLabel.setBounds(50, 100, 150, 30);
        retailerPanel.add(productNameLabel);

        JTextField productNameField = new JTextField();
        productNameField.setBounds(200, 100, 200, 30);
        retailerPanel.add(productNameField);

        JLabel productPriceLabel = new JLabel("Product Price ($):");
        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        productPriceLabel.setBounds(50, 150, 150, 30);
        retailerPanel.add(productPriceLabel);

        JTextField productPriceField = new JTextField();
        productPriceField.setBounds(200, 150, 200, 30);
        retailerPanel.add(productPriceField);

        JButton addProductButton = createStyledButton("Add Product", 150, 250, Color.MAGENTA, Color.YELLOW);
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                String productPriceText = productPriceField.getText();
                if (!productName.isEmpty() && !productPriceText.isEmpty()) {
                    try {
                        double productPrice = Double.parseDouble(productPriceText);
                        addProduct(productName, productPrice);
                        JOptionPane.showMessageDialog(retailerFrame, "Product added successfully!");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(retailerFrame, "Please enter a valid price.");
                    }
                } else {
                    JOptionPane.showMessageDialog(retailerFrame, "Please enter both product name and price.");
                }
            }
        });
        retailerPanel.add(addProductButton);

        retailerFrame.add(retailerPanel);
        retailerFrame.setVisible(true);
    }

    private void showProductPage(String userType) {
        JFrame productFrame = new JFrame("Shopping Portal - Products");
        productFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productFrame.setSize(1000, 700);
        productFrame.setLayout(null);

        JPanel productPanel = createGradientPanel();
        productPanel.setBounds(0, 0, 1000, 700);
        productPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + userType, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setBounds(50, 20, 900, 40);
        productPanel.add(welcomeLabel);

        JLabel productLabel = new JLabel("Available Products:");
        productLabel.setFont(new Font("Arial", Font.BOLD, 18));
        productLabel.setBounds(50, 70, 200, 30);
        productPanel.add(productLabel);

        JList<String> productList = new JList<>(productListModel);
        JScrollPane productScroll = new JScrollPane(productList);
        productScroll.setBounds(50, 110, 400, 250);
        productPanel.add(productScroll);

        JLabel cartLabel = new JLabel("Your Cart:");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 18));
        cartLabel.setBounds(550, 70, 200, 30);
        productPanel.add(cartLabel);

        JList<String> cartList = new JList<>(cartModel);
        JScrollPane cartScroll = new JScrollPane(cartList);
        cartScroll.setBounds(550, 110, 400, 250);
        productPanel.add(cartScroll);

        JLabel quantityLabel = new JLabel("Select Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        quantityLabel.setBounds(50, 380, 120, 30);
        productPanel.add(quantityLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(180, 380, 80, 30);
        productPanel.add(quantitySpinner);

        JButton addToCart = createStyledButton("Add to Cart", 50, 430, Color.MAGENTA, Color.YELLOW);
        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = productList.getSelectedValue();
                if (selectedItem != null) {
                    String productName = selectedItem.split(" - ")[0];
                    int quantity = (Integer) quantitySpinner.getValue();
                    cartQuantityMap.put(productName, cartQuantityMap.getOrDefault(productName, 0) + quantity);
                    updateCart(cartList);
                } else {
                    JOptionPane.showMessageDialog(productFrame, "Please select a product to add.");
                }
            }
        });
        productPanel.add(addToCart);

        JButton removeFromCart = createStyledButton("Remove from Cart", 250, 430, Color.RED, Color.PINK);
        removeFromCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = cartList.getSelectedValue();
                if (selectedItem != null) {
                    String productName = selectedItem.split(" x")[0];
                    cartQuantityMap.remove(productName);
                    updateCart(cartList);
                } else {
                    JOptionPane.showMessageDialog(productFrame, "Please select an item to remove.");
                }
            }
        });
        productPanel.add(removeFromCart);

        JLabel totalLabel = new JLabel("Total Price: $0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setBounds(550, 380, 400, 30);
        productPanel.add(totalLabel);

        JButton calculateTotal = createStyledButton("Calculate Total", 550, 430, Color.BLUE, Color.CYAN);
        calculateTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                for (Map.Entry<String, Integer> entry : cartQuantityMap.entrySet()) {
                    String productName = entry.getKey();
                    int quantity = entry.getValue();
                    total += productPriceMap.get(productName) * quantity;
                }
                totalLabel.setText("Total Price: $" + total);
            }
        });
        productPanel.add(calculateTotal);

        JButton paymentButton = createStyledButton("Proceed to Payment", 300, 520, Color.GREEN, Color.ORANGE);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentOptions();
            }
        });
        productPanel.add(paymentButton);

        productFrame.add(productPanel);
        productFrame.setVisible(true);
    }

    private void updateCart(JList<String> cartList) {
        cartModel.clear();
        for (Map.Entry<String, Integer> entry : cartQuantityMap.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            cartModel.addElement(productName + " x" + quantity);
        }
        cartList.setModel(cartModel);
    }

    private void showPaymentOptions() {
        JFrame paymentFrame = new JFrame("Shopping Portal - Payment");
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setSize(500, 300);
        paymentFrame.setLayout(null);

        JPanel paymentPanel = createGradientPanel();
        paymentPanel.setBounds(0, 0, 500, 300);
        paymentPanel.setLayout(null);

        JLabel paymentLabel = new JLabel("Choose Payment Option", SwingConstants.CENTER);
        paymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
        paymentLabel.setBounds(50, 30, 400, 40);
        paymentPanel.add(paymentLabel);

        JButton phonePay = createStyledButton("PhonePe", 50, 100, Color.MAGENTA, Color.YELLOW);
        phonePay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(paymentFrame,
                        "Order placed successfully via PhonePe!\nThank you for shopping");
            }
        });
        paymentPanel.add(phonePay);

        JButton gPay = createStyledButton("Google Pay", 200, 100, Color.BLUE, Color.CYAN);
        gPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(paymentFrame,
                        "Order placed successfully via GPay!\nThank you for shopping");
            }
        });
        paymentPanel.add(gPay);

        JButton cod = createStyledButton("Cash on Delivery", 350, 100, Color.GREEN, Color.ORANGE);
        cod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(paymentFrame,
                        "Order placed successfully via COD!\nThank you for shopping.");
            }
        });
        paymentPanel.add(cod);

        paymentFrame.add(paymentPanel);
        paymentFrame.setVisible(true);
    }

    private JPanel createGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.LIGHT_GRAY, getWidth(), getHeight(), Color.PINK);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private JButton createStyledButton(String text, int x, int y, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 40);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(baseColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(baseColor);
            }
        });
        return button;
    }
}

