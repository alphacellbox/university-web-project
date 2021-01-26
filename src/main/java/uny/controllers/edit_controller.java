package uny.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;
import javax.servlet.http.Part;
import javax.validation.constraints.NotBlank;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import uny.dao.watchdao;
import uny.entity.watch;
import uny.jms.jmsService;
import uny.jms.jmsbrowser;

@ApplicationScoped
@Named
public class edit_controller {

	boolean bool = false;
	ArrayList<String> id_list = new ArrayList<>();
	private String id;

	private String name_search;

	private Part image;

	public String go_to_edit() {
		System.out.println("aaaaaaaaaaaa");
		System.out.println(id);
		System.out.println("bbbbbbbbbbbb");
		return "edit?faces-redirect=true";

	}

	@Resource(lookup = "java:/jms/queue/watches")
	private Queue watches;
	@Inject
	private jmsService jms_service;

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Inject
	jmsbrowser jbrowser;
	private String text_1;
	private String cost_1;
	private StreamedContent file_1;

	private String json;
	int total;
	int one;
	private List<watch> a;
	@EJB
	private watchdao wdao;

	@PostConstruct
	public void search() {
		total++;

		System.out.println("hi bro 1");
		System.out.println(id);
		System.out.println("hi bro 1");
		int i = 0;
		a = wdao.getallwatches();

		do {

			System.out.println("hi bro 2");
			if (a.size() != 0) {

				System.out.println("hi bro 3");

				String url = a.get(i).getUrl();
				String text = a.get(i).getName();
				String cost = a.get(i).getCost();
				String temp_id = a.get(i).getId();
				System.out.println("hi bro 4");

				if (text.equals(name_search)) {

					System.out.println("hi bro 5");
					try {
						byte[] bytes = Files.readAllBytes(Paths.get(url));
						file_1 = DefaultStreamedContent.builder().contentType("a").name("c")
								.stream(() -> new ByteArrayInputStream(bytes)).build();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					text_1 = text;
					cost_1 = cost;
					id = temp_id;
					name_search = null;

					break;

				} else {
					InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
							.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");

					file_1 = new DefaultStreamedContent(iStream);
					text_1 = "there is no name";
					cost_1 = "there is no cost";
					System.out.println("hi bro 6");

				}
			} else {
				InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
						.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");
				file_1 = new DefaultStreamedContent(iStream);
				text_1 = "there is no name";
				cost_1 = "there is no cost";
				System.out.println("hi bro 7");

			}
			i++;
		} while (a.size() > i);

	}

	private String edit_name;
	private String edit_cost;
	private String edit_url;
	private String save_url;


	public void edit() {
		total++;

		System.out.println("hey bro 1");
		if (id != null) {
			System.out.println("hey bro 2");

			if (!edit_name.isEmpty()) {
				wdao.updatename(id, edit_name);
				System.out.println("hey bro 5");
			}
			if (!edit_cost.isEmpty()) {
				System.out.println("hey bro 6");
				wdao.updatecost(id, edit_cost);
			}
			if (image != null) {
				System.out.println("hey bro 7");
				File file = new File(wdao.findItem(id).getUrl());

				boolean r = file.delete();
				String filename = randomIdentifier() + ".jpg";
				try (InputStream input = image.getInputStream()) {

					File f = new File(filename);
					wdao.updateurl(id, f.getAbsolutePath());
					Files.copy(input, f.toPath());
					System.out.println(f.toPath());

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			System.out.println("hi bro 11");

			System.out.println("hi bro 22");

			System.out.println("hi bro 55");
			byte[] bytes;
			try {
				bytes = Files.readAllBytes(Paths.get(wdao.findItem(id).getUrl()));
				file_1 = DefaultStreamedContent.builder().contentType("a").name("c")
						.stream(() -> new ByteArrayInputStream(bytes)).build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			text_1 = wdao.findItem(id).getName();
			cost_1 = wdao.findItem(id).getCost();
			id = null;

		} else {
			InputStream iStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream("/images/realistic-clock-alarm-watch-vector-4324824.jpg");
			file_1 = new DefaultStreamedContent(iStream);
			text_1 = "there is no name";
			cost_1 = "there is no cost";
			System.out.println("hi bro 77");
		}

	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public String getEdit_name() {
		return edit_name;
	}

	public void setEdit_name(String edit_name) {
		this.edit_name = edit_name;
	}

	public String getEdit_cost() {
		return edit_cost;
	}

	public void setEdit_cost(String edit_cost) {
		this.edit_cost = edit_cost;
	}

	public String getEdit_url() {
		return edit_url;
	}

	public void setEdit_url(String edit_url) {
		this.edit_url = edit_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName_search() {
		return name_search;
	}

	public void setName_search(String name_search) {
		this.name_search = name_search;
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

	public StreamedContent getFile_1() {
		return file_1;
	}

	public void setFile_1(StreamedContent file_1) {
		this.file_1 = file_1;
	}

	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being
	// used or not
	final Set<String> identifiers = new HashSet<String>();

	public String randomIdentifier() {
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

}
