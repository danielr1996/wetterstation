package util;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class UtilTest {

	/**
	 * Testet die korrekte Funktionsweise der Methode Util.getDifferenzMenge()
	 */
	@Test
	public void getDifferenzMengeTest(){
		Map<Integer, Integer> a = new HashMap<Integer, Integer>();
		Map<Integer, Integer> b = new HashMap<Integer, Integer>();
		Map<Integer, Integer> should = new HashMap<Integer, Integer>();

		a.put(1, 1);


		b.put(1, 1);
		b.put(2, 2);

		should.put(2, 2);
//		assertEquals(Util.getDifferenzMenge(a, b), should);
		assertThat("", Util.getDifferenzMenge(a, b), is(should));
	}

	@Test
	public void getDifferenzMengeTestReverse(){
		Map<Integer, Integer> a = new HashMap<Integer, Integer>();
		Map<Integer, Integer> b = new HashMap<Integer, Integer>();
		Map<Integer, Integer> should = new HashMap<Integer, Integer>();

		b.put(1, 1);


		a.put(1, 1);
		a.put(2, 2);

		should.put(2, 2);
//		assertEquals(Util.getDifferenzMenge(a, b), should);
		assertThat("", Util.getDifferenzMenge(a, b), is(should));
	}

	@Test
	public void testAbsPositiv(){
		assertThat(Util.abs(10), is(10));
	}

	@Test
	public void testAbsNeutral(){
		assertThat(Util.abs(0), is(0));
	}

	@Test
	public void testAbsNegativ(){
		assertThat(Util.abs(-10), is(10));
	}

	@Test
	public void maxKeyTest(){
		final String KURZ = "      ";
		final String LANG = "                    ";
		Map<String, String> map = new HashMap<String, String>();
		map.put(KURZ,"");
		map.put(LANG,"");
		assertThat(Util.maxKey(map), is(LANG.length()));

		Map<String, String> map2 = new HashMap<String, String>();
		assertThat(Util.maxKey(map2), is(0));

	}

	@Test
	public void padTestRight(){
		final String KURZ = "kurz";
		char c = '*';

		assertThat(Util.pad(6, c, KURZ, Direction.RIGHT), is("kurz**"));
	}

	@Test
	public void padTestLeft(){
		final String KURZ = "kurz";
		char c = '*';

		assertThat(Util.pad(6, c, KURZ, Direction.LEFT), is("**kurz"));
	}

	@Test
	public void padTestSameLength(){
		final String s = "Test";
		char c =' ';
		assertThat(Util.pad(s.length(), c, s, Direction.RIGHT), is(s));
	}

	@Test
	public void toTableArrayTest(){
		SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		map.put(1, 2);
		map.put(3, 4);

		Object[][] array = Util.toTableArray(map);
		assertThat(array, is(new Object[][]{{1, 2},{3, 4}}));
	}

	@Test
	public void toTableArrayTestWithEmptyMap(){
		SortedMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

		Object[][] array = Util.toTableArray(map);
		assertThat(array, is(new Object[][]{}));
	}
}
