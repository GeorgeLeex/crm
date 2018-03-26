package crm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.neuedu.system.tools.ConvertUtils;

public class DataSourceTest {
	
	private static ComboPooledDataSource ds;
	
	@BeforeClass
	public static void init(){
		//ds = new ComboPooledDataSource("dataSource");
	}
	@Test
	public void test1(){
		try {
			Connection conn = ds.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test2(){
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i <= 4; i++) {
			Map<String, Object> m = new HashMap<>();
			m.put(i+"", "100" + i);
			list.add(m);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test3() throws Exception{
		InputStream in = new FileInputStream("d://test1.txt");
		byte[] b = new byte[1024];
		int len = in.read(b);
		String str = "";
		while (len != -1) {
			str += new String(b, 0, len);
			len = in.read(b);
		}
		in.close();
		System.out.println(str);
	}
	@Test
	public void test4() throws Exception{
		OutputStream out = new FileOutputStream("d://test1.txt", true);
		String str = "\njavjajjvjajvjajvjavjajvjajvavajjv";
		byte[] b = str.getBytes();
		out.write(b);
		out.close();
	}
	@Test
	public void test5() throws Exception{
		File file = new File("d://software");
		File[] files = file.listFiles();
		String[] names = file.list();
		for (File f : files) {
			System.out.print(f.getName() + f.isFile() + "\t");
		}
		for (String name : names) {
			System.out.println(name);
		}
	}
	@Test
	public void test6() throws Exception{
		Writer writer = new FileWriter("d://test1.txt");
		BufferedWriter bw = new BufferedWriter(writer);
		bw.newLine();
		bw.write("pythonpythonpythonpythonpythonpythonpython");
		bw.flush();
		bw.close();
	}
	@Test
	public void test7() throws Exception{
		Reader reader = new FileReader("d://test1.txt");
		BufferedReader br = new BufferedReader(reader);
		String str = br.readLine();
		while (str != null) {
			str = br.readLine();
		}
		reader.close();
		br.close();
		System.out.println(str);
		/*String str = "";
		String temp = br.readLine();
		while (temp != null) {
			str += temp;
			temp = br.readLine();
		}
		System.out.println(str);
		reader.close();
		br.close();*/
	}
	@Test
	public void test8() throws Exception{
		Class clazz = this.getClass().getClassLoader().loadClass("crm.Demo1");
		Field[] fields = clazz.getFields();
		Method[] methods = clazz.getMethods();
		for (Field field : fields) {
			System.out.println(Modifier.toString(field.getModifiers()));
			System.out.println(field.getType().getSimpleName());
			System.out.println(field.getName());
		}
		System.out.println();
		for (Method method : methods) {
			System.out.println(method.getName());
			System.out.println(Modifier.toString(method.getModifiers()));
			System.out.println(method.getReturnType().getSimpleName());
		}
	}
	@Test
	public void test9() throws Exception{
		Class clazz = Class.forName("Demo1");
		Package p = clazz.getPackage();
		System.out.println(p.getName());
	}
	@Test
	public void test10(){
		Object obj = new Object();
		int i = (int) obj;
	}
	@Test
	public void test11(){
		int a = 5;
		String b = "null";
		Object obj = b;
		System.out.println("null".equals(obj));
	}
	@Test
	public void test12(){
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("key1", "value1");
		map1.put("key2", "value2");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("key3", "value3");
		map2.put("key4", "value4");
		list.add(map1);
		list.add(map2);
		//System.out.println(ConvertUtils.convertMapListToString(list));
	}
	@Test
	public void test13(){
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(null);
		list.add(null);
		System.out.println(list.size());
	}
}
