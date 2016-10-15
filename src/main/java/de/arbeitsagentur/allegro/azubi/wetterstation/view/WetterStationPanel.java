package de.arbeitsagentur.allegro.azubi.wetterstation.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
/**
 * View der Anwendung
 * @author RichterD042
 *
 */
public class WetterStationPanel extends JPanel{

	private static final long serialVersionUID = 3958636934659367614L;
	private JTextField tfMax, tfMin, tfAvg, tfMaxDiff, tfTag, tfWert;
	private JButton btnAdd;
	private JTable table;

	/**
	 * Darstellung des Diagrams
	 */
	private JFreeChart chart;
	private CellConstraints cc = new CellConstraints();
	private DefaultCategoryDataset ds = new DefaultCategoryDataset();

	public WetterStationPanel(){
		tfMax = new JTextField();
		tfMin = new JTextField();
		tfAvg = new JTextField();
		tfMaxDiff  = new JTextField();
		tfTag = new JTextField("1");
		tfWert = new JTextField("");
		btnAdd = new JButton("Hinzufügen");
		tfMax.setEditable(false);
		tfMin.setEditable(false);
		tfAvg.setEditable(false);
		tfMaxDiff.setEditable(false);
		createPanel();
	}

	/**
	 * Setzt das Layout und fügt die Komponenten dem Panel hinzu
	 */
	private void createPanel(){
		FormLayout layout = new FormLayout(
				"10dlu, pref, max(50dlu;pref), max(300dlu;pref):grow, 10dlu, max(50dlu;pref), max(50dlu;pref), max(300dlu;pref):grow, 10dlu",
				"10dlu, pref, 10dlu, pref, 10dlu, max(300dlu;pref):grow, 10dlu, pref, 10dlu, pref,10dlu, pref, 10dlu");
		this.setLayout(layout);

		this.add(new JLabel("Max: "), cc.xy(2, 2));
		this.add(new JLabel("Min: "), cc.xy(2, 4));
		this.add(new JLabel("Durchschnitt: "), cc.xy(6, 2));
		this.add(new JLabel("Maximale Differenz: "), cc.xy(6, 4));
		this.add(new JLabel("Tag: "), cc.xy(2, 8));
		this.add(new JLabel("Wert: "), cc.xy(2, 10));

		this.add(tfMax, cc.xy(3, 2));
		this.add(tfMin, cc.xy(3, 4));
		this.add(tfAvg, cc.xy(7, 2));
		this.add(tfMaxDiff, cc.xy(7, 4));
		this.add(tfTag, cc.xy(3, 8));
		this.add(tfWert, cc.xy(3, 10));

		this.add(createTable(), cc.xyw(2,  6,  3));
		this.add(new ChartPanel(createChart()), cc.xyw(6,  6,  3));
		this.add(btnAdd, cc.xyw(2, 12, 2));
	}

	/**
	 * Erstellet ein leeres Diagramm
	 * @return
	 */
	private JFreeChart createChart() {
		chart = ChartFactory.createBarChart(
				"WetterStation",
				"Zeit",
				"Temperatur",
				ds,
				PlotOrientation.VERTICAL,
				true, true, false
				);

		return chart;
	}

	/**
	 * Erstellet eine leere Tabelle
	 * @return
	 */
	private JComponent createTable() {
		table = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"Tag, Wert"}));
		table.setFillsViewportHeight(true);
		return new JScrollPane(table);
	}

	/**
	 * Setzt die Diagramm Daten
	 * @param dcd
	 */
	public void addChartData(int value, String key1, String key2){
		this.ds.addValue(value, key1, key2);
	}

	public void addChartData(double value, String key1, String key2){
		this.ds.addValue(value, key1, key2);
	}

	public void setChartData(DefaultCategoryDataset ds){
		this.ds = ds;
	}


	/**
	 * Setzt die Diagramm Daten
	 * @param dtm
	 */
	public void setTableData(DefaultTableModel dtm){
		table.setModel(dtm);
	}

	public void setTfMax(String text){
		this.tfMax.setText(text);
	}

	public void setTfMin(String text){
		this.tfMin.setText(text);
	}

	public void setTfMaxDiff(String text){
		this.tfMaxDiff.setText(text);
	}

	public void setTfAvg(String text){
		this.tfAvg.setText(text);
	}

	public void setTfTag(String text){
		this.tfTag.setText(text);
	}

	/**
	 * Fügt dem "Hinzufügen" Button einen Listener hinzu
	 * @param l
	 */
	public void addButtonListener(ActionListener l){
		btnAdd.addActionListener(l);
	}

	public String getTfTag(){
		return tfTag.getText();
	}

	public String getTfWert(){
		return tfWert.getText();
	}

	public void resetEingabeFelder(){
		tfTag.setText("");
		tfWert.setText("");
	}
}
