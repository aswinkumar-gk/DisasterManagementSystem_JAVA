import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// Disaster Class
class Disaster {
    String type;
    String location;
    String severity;

    public Disaster(String type, String location, String severity) {
        this.type = type;
        this.location = location;
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Location: " + location + ", Severity: " + severity;
    }
}

// Volunteer Class
class Volunteer {
    String name;
    String contact;
    String expertise;

    public Volunteer(String name, String contact, String expertise) {
        this.name = name;
        this.contact = contact;
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Contact: " + contact + ", Expertise: " + expertise;
    }
}

// Main Class
public class DisasterManagementSystem {
    private ArrayList<Disaster> disasterList = new ArrayList<>();
    private ArrayList<Volunteer> volunteerList = new ArrayList<>();

    public DisasterManagementSystem() {
        // Main Frame
        JFrame frame = new JFrame("Disaster Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Label
        JLabel headerLabel = new JLabel("Disaster Management System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

        JButton addDisasterButton = new JButton("Add Disaster");
        JButton viewDisastersButton = new JButton("View Disasters");
        JButton addVolunteerButton = new JButton("Add Volunteer");
        JButton viewVolunteersButton = new JButton("View Volunteers");
        JButton sendAlertButton = new JButton("Send Alert");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(addDisasterButton);
        buttonPanel.add(viewDisastersButton);
        buttonPanel.add(addVolunteerButton);
        buttonPanel.add(viewVolunteersButton);
        buttonPanel.add(sendAlertButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Button Actions
        addDisasterButton.addActionListener(e -> addDisaster());
        viewDisastersButton.addActionListener(e -> viewDisasters());
        addVolunteerButton.addActionListener(e -> addVolunteer());
        viewVolunteersButton.addActionListener(e -> viewVolunteers());
        sendAlertButton.addActionListener(e -> sendAlert());
        exitButton.addActionListener(e -> System.exit(0));

        // Display the Frame
        frame.setVisible(true);
    }

    private void addDisaster() {
        JTextField typeField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField severityField = new JTextField();

        Object[] fields = {
                "Disaster Type:", typeField,
                "Location:", locationField,
                "Severity (Low/Medium/High):", severityField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Disaster", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String type = typeField.getText();
            String location = locationField.getText();
            String severity = severityField.getText();

            disasterList.add(new Disaster(type, location, severity));
            JOptionPane.showMessageDialog(null, "Disaster record added successfully!");
        }
    }

    private void viewDisasters() {
        if (disasterList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No disaster records found!");
        } else {
            StringBuilder records = new StringBuilder("Disaster Records:\n");
            for (Disaster disaster : disasterList) {
                records.append(disaster).append("\n");
            }
            JOptionPane.showMessageDialog(null, records.toString());
        }
    }

    private void addVolunteer() {
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField expertiseField = new JTextField();

        Object[] fields = {
                "Name:", nameField,
                "Contact Number:", contactField,
                "Area of Expertise:", expertiseField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Volunteer", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String contact = contactField.getText();
            String expertise = expertiseField.getText();

            volunteerList.add(new Volunteer(name, contact, expertise));
            JOptionPane.showMessageDialog(null, "Volunteer added successfully!");
        }
    }

    private void viewVolunteers() {
        if (volunteerList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No volunteers found!");
        } else {
            StringBuilder records = new StringBuilder("Volunteer List:\n");
            for (Volunteer volunteer : volunteerList) {
                records.append(volunteer).append("\n");
            }
            JOptionPane.showMessageDialog(null, records.toString());
        }
    }

    private void sendAlert() {
        if (disasterList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No disaster records to send alerts for.");
            return;
        }

        String disasterType = JOptionPane.showInputDialog("Enter Disaster Type to Send Alert (e.g., Flood, Fire):");

        if (disasterType != null && !disasterType.isEmpty()) {
            StringBuilder alertMessage = new StringBuilder("Sending alerts for " + disasterType + "...\n");
            for (Volunteer volunteer : volunteerList) {
                alertMessage.append("Alert sent to ").append(volunteer.name)
                        .append(" (").append(volunteer.contact).append(")\n");
            }
            JOptionPane.showMessageDialog(null, alertMessage.toString());
        }
    }

    public static void main(String[] args) {
        new DisasterManagementSystem();
    }
}