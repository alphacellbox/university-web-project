package uny.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

import uny.dao.watchdao;
import uny.entity.watch;
import uny.jms.jmsbrowser;

@ApplicationScoped
@Named
public class shop_controller {
	@Inject
	private jmsbrowser jbrows;

	@EJB
	private watchdao wdao;
	private StreamedContent file_1;
	private String text_1;
	private String cost_1;
	private StreamedContent file_2;
	private String text_2;
	private String cost_2;
	private StreamedContent file_3;
	private String text_3;
	private String cost_3;
	private StreamedContent file_4;
	private String text_4;
	private String cost_4;
	private String id_1;
	private String id_2;
	private String id_3;
	private String id_4;
	int one = 0;
	int two = 1;
	int three = 2;
	int four = 3;
	int five = 4;
	int six = 5;
	int seven = 6;
	int x = 0;
	private	List<watch> a;
	@Resource(lookup = "java:/jms/queue/watches")
	private Queue watches;

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	public String go_to_shop() {

		return "shop?faces-redirect=true";
	}

	public void delete_watch_1() throws IOException {
		watch w = wdao.findItem(id_1);
		wdao.delete(id_1);

		id_1 = null;
		id_2 = null;
		id_3 = null;
		id_4 = null;

		File f = new File(w.getUrl());

		boolean r = f.delete();

	}

	public void delete_watch_2() throws IOException {

		watch w = wdao.findItem(id_2);
		wdao.delete(id_2);

		id_1 = null;
		id_2 = null;
		id_3 = null;
		id_4 = null;

		File f = new File(w.getUrl());

		boolean r = f.delete();

	}

	public void delete_watch_3() throws IOException {

		watch w = wdao.findItem(id_3);
		wdao.delete(id_3);

		id_1 = null;
		id_2 = null;
		id_3 = null;
		id_4 = null;

		File f = new File(w.getUrl());

		boolean r = f.delete();

	}

	public void delete_watch_4() throws IOException {

		watch w = wdao.findItem(id_4);
		wdao.delete(id_4);

		id_1 = null;
		id_2 = null;
		id_3 = null;
		id_4 = null;

		File f = new File(w.getUrl());

		boolean r = f.delete();

	}

	public void change_search_page() {
		one = 0;
		two = 1;
		three = 2;
		four = 3;
		five = 4;
		System.out.println(a.size());
		Double s = Double.valueOf(a.size());
		System.out.println(s);
		Double i = s / 4;

		System.out.println(i);
		if (i > 0) {

			one += 1;
			System.out.println("one");

		}
		if (i > one) {

			two += 1;

			System.out.println("two");
		}
		if (i > two) {

			three += 1;

		}
		if (i > three) {

			four += 1;

		}
		if (i > four) {

			five += 1;

		}

	}

	public boolean permition_for_one() {
		Double s = Double.valueOf(a.size());

		Double i = s / 4;
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean permition_for_two() {
		Double s = Double.valueOf(a.size());

		Double i = s / 4;
		if (i > 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean permition_for_three() {
		Double s = Double.valueOf(a.size());

		Double i = s / 4;
		if (i > 2) {
			return true;
		} else {
			return false;
		}

	}

	public boolean permition_for_four() {
		Double s = Double.valueOf(a.size());

		Double i = s / 4;
		if (i > 3) {
			return true;
		} else {
			return false;
		}

	}

	public boolean permition_for_five() {
		Double s = Double.valueOf(a.size());

		Double i = s / 4;
		if (i > 4) {
			return true;
		} else {
			return false;
		}

	}


	

	

	public void set_watches() {
		int i = 0;
		if (x >= 1) {
			i = (x - 1) * 4;

		}

		a = wdao.getallwatches();
		System.out.println(a.size());
		change_search_page();
		if (a.size() > i) {

			byte[] bytes;
			try {
				bytes = Files.readAllBytes(Paths.get(a.get(i).getUrl()));
				file_1 = DefaultStreamedContent.builder().contentType("a").name("c")
						.stream(() -> new ByteArrayInputStream(bytes)).build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			id_1 = a.get(i).getId();
			text_1 = a.get(i).getName();
			cost_1 = a.get(i).getCost();
			i++;
		} else {
			InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");
			file_1 = new DefaultStreamedContent(iStream);
			text_1 = "there is no name";
			cost_1 = "there is no cost";

		}

		/////////////////////////////////////////////
		if (a.size() > i) {
			System.out.println("vvvvvvvvvvv");

			byte[] bytes;
			try {
				bytes = Files.readAllBytes(Paths.get(a.get(i).getUrl()));
				file_2 = DefaultStreamedContent.builder().contentType("a").name("c")
						.stream(() -> new ByteArrayInputStream(bytes)).build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id_2 = a.get(i).getId();
			text_2 = a.get(i).getName();
			cost_2 = a.get(i).getCost();
			i++;
		} else {
			InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");

			file_2 = new DefaultStreamedContent(iStream);
			text_2 = "there is no name";
			cost_2 = "there is no cost";
		}

		/////////////////////////////////////
		if (a.size() > i) {
			System.out.println("vvvvvvvvvvv");

			byte[] bytes;
			try {
				bytes = Files.readAllBytes(Paths.get(a.get(i).getUrl()));
				file_3 = DefaultStreamedContent.builder().contentType("a").name("c")
						.stream(() -> new ByteArrayInputStream(bytes)).build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id_3 = a.get(i).getId();
			text_3 = a.get(i).getName();
			cost_3 = a.get(i).getCost();
			i++;
		} else {
			InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");

			file_3 = new DefaultStreamedContent(iStream);
			text_3 = "there is no name";
			cost_3 = "there is no cost";
		}
		////////////////////////////
		if (a.size() > i) {
			System.out.println("vvvvvvvvvvv");

			byte[] bytes;
			try {
				bytes = Files.readAllBytes(Paths.get(a.get(i).getUrl()));
				file_4 = DefaultStreamedContent.builder().contentType("a").name("c")
						.stream(() -> new ByteArrayInputStream(bytes)).build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id_4 = a.get(i).getId();
			text_4 = a.get(i).getName();
			cost_4 = a.get(i).getCost();
			i++;
		} else {
			InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");

			file_4 = new DefaultStreamedContent(iStream);
			text_4 = "there is no name";
			cost_4 = "there is no cost";
		}

	}

	public StreamedContent getFile_1() {
		return file_1;
	}

	public String getId_1() {
		return id_1;
	}

	public void setId_1(String id_1) {
		this.id_1 = id_1;
	}

	public String getId_2() {
		return id_2;
	}

	public void setId_2(String id_2) {
		this.id_2 = id_2;
	}

	public String getId_3() {
		return id_3;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setId_3(String id_3) {
		this.id_3 = id_3;
	}

	public String getId_4() {
		return id_4;
	}

	public StreamedContent getFile_2() {
		return file_2;
	}

	public void setFile_22(StreamedContent file_2) {
		this.file_2 = file_2;
	}

	public void setId_4(String id_4) {
		this.id_4 = id_4;
	}

	public void setFile_1(StreamedContent file_1) {
		this.file_1 = file_1;
	}

	public String getText_1() {
		return text_1;
	}

	public void setText_1(String text_1) {
		this.text_1 = text_1;
	}

	public String getCost_1() {
		return cost_1;
	}

	public void setCost_1(String cost_1) {
		this.cost_1 = cost_1;
	}

	public String getText_2() {
		return text_2;
	}

	public void setText_2(String text_2) {
		this.text_2 = text_2;
	}

	public String getCost_2() {
		return cost_2;
	}

	public void setCost_2(String cost_2) {
		this.cost_2 = cost_2;
	}

	public StreamedContent getFile_3() {
		return file_3;
	}

	public void setFile_3(StreamedContent file_3) {
		this.file_3 = file_3;
	}

	public String getText_3() {
		return text_3;
	}

	public void setText_3(String text_3) {
		this.text_3 = text_3;
	}

	public String getCost_3() {
		return cost_3;
	}

	public void setCost_3(String cost_3) {
		this.cost_3 = cost_3;
	}

	public StreamedContent getFile_4() {
		return file_4;
	}

	public void setFile_4(StreamedContent file_4) {
		this.file_4 = file_4;
	}

	public String getText_4() {
		return text_4;
	}

	public void setText_4(String text_4) {
		this.text_4 = text_4;
	}

	public String getCost_4() {
		return cost_4;
	}

	public void setCost_4(String cost_4) {
		this.cost_4 = cost_4;
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}

	public int getThree() {
		return three;
	}

	public void setThree(int three) {
		this.three = three;
	}

	public int getFour() {
		return four;
	}

	public void setFour(int four) {
		this.four = four;
	}

	public int getFive() {
		return five;
	}

	public void setFive(int five) {
		this.five = five;
	}

	public int getSix() {
		return six;
	}

	public void setSix(int six) {
		this.six = six;
	}

	public int getSeven() {
		return seven;
	}

	public void setSeven(int seven) {
		this.seven = seven;
	}
	public List<watch> getA() {
		return a;
	}

	public void setA(List<watch> a) {
		this.a = a;
	}

	public void setFile_2(StreamedContent file_2) {
		this.file_2 = file_2;
	}

}
