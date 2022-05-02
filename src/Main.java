import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class Main extends JFrame {
    public Main(String title) throws IOException {
        super(title);
        DataManager dm = new DataManager();
        Utility util = new Utility(dm);
        dm.convertFlightsJsonToArray();
        dm.convertCargosJsonToArray();
        dm.setLists();

        JFrame frame = new JFrame("JTable Test Display");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 1));
        infoPanel.setSize(500, 500);
        mainPanel.add(BorderLayout.CENTER, infoPanel);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        String [] header = {"Flight Id", "Flight Number", "Departure Airport IATA Code", "Arrival Airport IATA Code", "Departure Date"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        JTable flightsTable = new JTable(model);
        for (int i = 0; i < dm.flights.length; i++) {
            model.addRow(new Object[]{dm.flights[i].flightId, dm.flights[i].flightNumber, dm.flights[i].departureAirportIATACode, dm.flights[i].arrivalAirportIATACode, dm.flights[i].departureDate});
        }
        JTableHeader flightsTableHeader = flightsTable.getTableHeader();
        tablePanel.add(flightsTableHeader, BorderLayout.NORTH);
        tablePanel.add(flightsTable, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.NORTH);

        infoPanel.add(new JLabel("FLIGHT INFO"));
        JTextArea flighsInfoArea = new JTextArea("Choose a flight to see more...");
        infoPanel.add(flighsInfoArea);

        infoPanel.add(new JLabel("AIRPORT INFO"));
        JPanel checkboxes = new JPanel();
        JComboBox<String> IATACodes = new JComboBox<String>(dm.airportsList.toArray(new String[0]));
        JComboBox<String> dates = new JComboBox<String>(dm.datesFormattedList.toArray(new String[0]));
        JButton searchBut = new JButton("Search");
        checkboxes.add(IATACodes);
        checkboxes.add(dates);
        checkboxes.add(searchBut);
        infoPanel.add(checkboxes);

        JTextArea airportInfoArea = new JTextArea("Choose an airport and date for more info...");
        infoPanel.add(airportInfoArea);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(new Dimension(1100, 500));
        this.setLocationRelativeTo(null);

        flightsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int flightId = (int) flightsTable.getValueAt(flightsTable.getSelectedRow(), 1);
                Date date = (Date) flightsTable.getValueAt( flightsTable.getSelectedRow(), 4);
                flighsInfoArea.setText(util.getFlightInfo(flightId, date));
            }
        });

        searchBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                airportInfoArea.setText(util.getAirportInfo((String) IATACodes.getSelectedItem(), dates.getSelectedIndex()));
            }
        });
    }

    public static void main (String[]args) throws IOException {
        DataManager dm = new DataManager();
        Main mw = new Main("Smart4Aviation");
        mw.setVisible(true);
    }
}
