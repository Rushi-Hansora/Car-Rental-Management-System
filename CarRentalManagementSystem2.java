import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CarRentalManagementSystem2 extends Frame implements ActionListener, WindowListener {
    TextField carRegNo, carModel, personName, personId, mobileNo;
    TextArea rentDocuments;
    Label warningLabel;
    Dialog availabilityDialog;
    Canvas canvas;
    HashMap<String, String> carBookings;
    Image backgroundImage;

    public CarRentalManagementSystem2() {
        carBookings = new HashMap<>();
        Frame loginFrame = new Frame("Login");
        loginFrame.setTitle("Welcome");
        loginFrame.setSize(1535, 910);
        loginFrame.setLayout(new GridBagLayout());
        loginFrame.setBackground(Color.orange);
        GridBagConstraints c = new GridBagConstraints();

        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        loginFrame.add(userLabel, c);

        TextField userText = new TextField(30);
        c.gridx = 1;
        c.gridy = 0;
        loginFrame.add(userText, c);

        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 1;
        loginFrame.add(passLabel, c);

        TextField passText = new TextField(30);
        passText.setEchoChar('*');
        c.gridx = 1;
        c.gridy = 1;
        loginFrame.add(passText, c);

        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 1;
        c.gridy = 2;
        loginFrame.add(loginButton, c);

        loginButton.addActionListener(e -> {
            loginFrame.setVisible(false);
            createMainFrame();
        });

        loginFrame.addWindowListener(this);
        loginFrame.setVisible(true);
    }

    public void paint(Graphics g) {

    }

    public void createMainFrame() {
        setTitle("Rushi's Car Rent Shop");
        setSize(1535, 910);
        setLayout(new GridBagLayout());
        // setBackground(Color.orange);
        setBackground(Color.CYAN);
        GridBagConstraints c = new GridBagConstraints();

        Label titleLabel = new Label("Rushi's Car Rent Shop");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 0, 15);
        add(titleLabel, c);

        Label carRegNoLabel = new Label("Car Registration No :-");
        carRegNoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(carRegNoLabel, c);

        carRegNo = new TextField(48);
        c.gridx = 1;
        c.gridy = 1;
        add(carRegNo, c);

        Label carModelLabel = new Label("Car Model                 :-");
        carModelLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 2;
        add(carModelLabel, c);

        carModel = new TextField(48);
        c.gridx = 1;
        c.gridy = 2;
        add(carModel, c);

        Label personNameLabel = new Label("Person Name            :-");
        personNameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 3;
        add(personNameLabel, c);

        personName = new TextField(48);
        c.gridx = 1;
        c.gridy = 3;
        add(personName, c);

        Label personIdLabel = new Label("Person ID                 :-");
        personIdLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 4;
        add(personIdLabel, c);

        personId = new TextField(48);
        c.gridx = 1;
        c.gridy = 4;
        add(personId, c);

        Label mobileNoLabel = new Label("Mobile No                 :-");
        mobileNoLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 5;
        add(mobileNoLabel, c);

        mobileNo = new TextField(48);
        c.gridx = 1;
        c.gridy = 5;
        add(mobileNo, c);

        Label rentDocumentsLabel = new Label("Rent Documents     :- ");
        rentDocumentsLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 0;
        c.gridy = 6;
        add(rentDocumentsLabel, c);

        rentDocuments = new TextArea(5, 48);
        c.gridx = 1;
        c.gridy = 6;
        add(rentDocuments, c);

        Button rentButton = new Button("Rent");
        rentButton.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 1;
        c.gridy = 7;
        add(rentButton, c);

        Button returnButton = new Button("Return");
        returnButton.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 1;
        c.gridy = 8;
        add(returnButton, c);

        warningLabel = new Label("");
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridx = 1;
        c.gridy = 9;
        add(warningLabel, c);

        canvas = new Canvas() {
            public void paint(Graphics g) {
                canvas.setBackground(Color.WHITE);
                int y = 20;
                for (String key : carBookings.keySet()) {
                    g.drawString(key + ": " + carBookings.get(key), 20, y);
                    y += 20;
                }
            }
        };
        canvas.setSize(640, 300);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        add(canvas, c);

        rentButton.addActionListener(this);
        returnButton.addActionListener(e -> returnCar());
        addWindowListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (carRegNo.getText().isEmpty() || carModel.getText().isEmpty() ||
                personName.getText().isEmpty() || personId.getText().isEmpty() || mobileNo.getText().isEmpty() ||
                rentDocuments.getText().isEmpty()) {
            warningLabel.setText("Please fill all the details!");
        } else {
            String carKey = carRegNo.getText() + "-" + personId.getText();
            if (carBookings.containsKey(carKey)) {
                showAvailabilityDialog("Sorry, this car is already booked.");
            } else {
                carBookings.put(carKey, personName.getText());
                canvas.repaint();
                showAvailabilityDialog("Congratulations Sir/Madam, this car is available!");
            }
            warningLabel.setText("");
        }
    }

    public void returnCar() {
        if (carRegNo.getText().isEmpty() || personId.getText().isEmpty()) {
            warningLabel.setText("Please fill Car Registration No and Person ID to return the car!");
        } else {
            String carKey = carRegNo.getText() + "-" + personId.getText();
            if (carBookings.containsKey(carKey)) {
                carBookings.remove(carKey);
                canvas.repaint();
                showAvailabilityDialog("Car returned successfully. It is now available for rent.");
            } else {
                showAvailabilityDialog("No such booking found.");
            }
            warningLabel.setText("");
        }
    }

    public void showAvailabilityDialog(String message) {
        availabilityDialog = new Dialog(this, "Car Availability", true);
        availabilityDialog.setSize(1535, 900);
        availabilityDialog.setLayout(new GridBagLayout());
        availabilityDialog.setBackground(Color.YELLOW);
        GridBagConstraints c = new GridBagConstraints();

        Label messageLabel = new Label(message);
        c.gridx = 0;
        c.gridy = 0;
        availabilityDialog.add(messageLabel, c);

        Button okButton = new Button("OK");
        c.gridx = 0;
        c.gridy = 1;
        availabilityDialog.add(okButton, c);

        okButton.addActionListener(e -> availabilityDialog.setVisible(false));

        availabilityDialog.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public static void main(String[] args) {
        new CarRentalManagementSystem2();
    }
}
