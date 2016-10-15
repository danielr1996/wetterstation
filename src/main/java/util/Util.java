package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

public class Util {

	/**
	 * Gibt den Betrag der Zahl n zurück
	 * @param n die Zahl deren Betrag berechnet werden soll
	 * @return der Betrag der Zahl n
	 */
	public static int abs(int n){
		if(n>=0)return n;
		else return -n;
	}

	/**
	 * Gibt die String Länge des Längsten Keys aus der Map m zurück
	 * @param m die Map mit den Keys
	 * @return die Länge des Längsten Keys
	 */
	public static int maxKey(Map<?, ?> map){
		Object[] keys = map.keySet().toArray();
		Integer max = null;

		for(int i = 0; i < keys.length; i++){
			if(max == null || keys[i].toString().length()  > max ){
				max = keys[i].toString().length();
			}
		}
		return max == null ? 0 : max;
	}

	/**
	 * Ergänzt einen String um weitere Zeichen
	 * @param length die gewünschte Länge
	 * @param c das gewünschte Zeichen
	 * @param value der zu ergänzende String
	 * @param dir rechts oder links
	 * @return den ergänzten String
	 */
	public static String pad(int length, char c, String value, Direction dir){
		int diff = length-value.length();
		String result = "";
		if(diff <=0){
			result = value;
		}else{
			StringBuilder stringBuilder;
			if(dir == Direction.LEFT){
				stringBuilder = new StringBuilder();
				for(int i = 0; i < diff; i++){
					stringBuilder.append(c);
				}
				stringBuilder.append(value);
			}else{
				stringBuilder = new StringBuilder(value);
				for(int i = 0; i < diff; i++){
					stringBuilder.append(c);
				}
			}
			result = stringBuilder.toString();
		}

		return result;
	}

	/**
	 * Konvertiert eine Map in ein Object Array für eine JTable
	 * @param map die zu konvertierende Map
	 * @return die konvertierte Map
	 */
	public static Object[][] toTableArray(SortedMap<Integer, Integer> map){

		List<Integer[]> list = new ArrayList<Integer[]>();
		for(Entry<Integer, Integer> entry : map.entrySet()){
			list.add(new Integer[]{entry.getKey(), entry.getValue()});
		}

		Object[][] data = new Object[list.size()][2];

		for(int i = 0; i < list.size(); i++){
			data[i]=list.get(i);
		}

		return data;
	}

	/**
	 * Konvertiert eine Map in ein DefaultCategoryDataset für ein JFreeChart
	 * @param map die zu konvertierende Map
	 * @return die konvertierte Map
	 */
	/*public static DefaultCategoryDataset toDataSet(SortedMap<Integer, Integer> map){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for(Entry<Integer, Integer> entry : map.entrySet()){
			dataset.addValue(entry.getValue(), "", entry.getKey());
		}

		return dataset;
	}*/

	/**
	 * Gibt die DifferenzMenge zwischen zwei Maps zurück. Eine der Maps muss eine Echte Teilmenge der anderen Map sein
	 * @param a Map a
	 * @param b Map b
	 * @return die Differenzmenge zwischen a und b
	 */
	//TODO Generics
	public static Map<Integer, Integer> getDifferenzMenge(Map<Integer, Integer> a, Map<Integer, Integer> b){
		Map<Integer, Integer> obermenge = a.size() > b.size() ? a : b;
		Map<Integer, Integer> teilmenge = a.size() > b.size() ? b : a;

		Map<Integer, Integer> differenzmenge = new HashMap<Integer, Integer>();

		for(Entry<Integer, Integer> entry : obermenge.entrySet()){
			if(!teilmenge.containsKey(entry.getKey())){
				differenzmenge.put(entry.getKey(), entry.getValue());
			}
		}

		return differenzmenge;
	}
}
