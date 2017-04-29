import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlayerInfo extends JScrollPane {

    static JTable table;
    public PlayerInfo(){
        /*super(new GridLayout(1,0));

        String[] columnNames = {"Player Name ", "Sets", "Series"};

        Object[][] data = {{}};

        table = new JTable(data, columnNames);
        setVisible(true);*/

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Player Name");
        model.addColumn("Sets");
        model.addColumn("Series");

        add(table);
        setVisible(true);

    }
}
