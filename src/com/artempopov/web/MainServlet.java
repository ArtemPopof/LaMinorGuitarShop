package com.artempopov.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.artempopov.web.beans.GuitarBean;
import com.artempopov.web.data.CartItemList;

@WebServlet(urlPatterns = "/main", initParams={@WebInitParam(name="initial_bookmark", value="full")})

public class MainServlet extends HttpServlet {
	
	private static GuitarBean[] guitars;
	private String page;
	private int currentItem;
	private static CartItemList cartItems;
	private boolean firstStart;
	private boolean isLogged = false;
	private static boolean isCartEmpty = false;
	
	private static SessionFactory sessionFactory;

	
	public MainServlet () {
		super();
		
		guitars = new GuitarBean[6];
		
		//String productName, String productType, String vendorCode, int productPrice, String pictureImageURL
		
		guitars[0] = new GuitarBean("Fender American Deluxe Stratocaster",
				"Electric guitar", "F0001", 3000, "res/fender");
		
		guitars[0].setReview("When many people think of an electric guitar, this is the one that leaps to mind. The Stratocaster is an archetypal instrumentâamong the world's most popular guitars and an elegantly versatile creation that is both a musical and cultural touchstone. At its heart, though, the Stratocaster remains a fantastic toolâwith unmistakable sound and timeless design that have made it the first choice among players worldwide, many of whom have used it to create much of the most important music of our time.", "en");
		guitars[0].setReview("Дизайн FENDER AMERICAN DELUXE STRAT PLUS MN MYSTIC 3-COLOR SUNBURST остался традиционным – в духе знаменитого «Stratocaster».Кардинальные изменения коснулись внутреннего содержимого гитары – креативные инженеры «Fender» соедили три инструмента в один, при этом добились ошеломляющего результата без создания громоздких конструкций и дополнительных педалей эффектов. Шестиструнная электрогитара FENDER AMERICAN DELUXE STRAT PLUS MN MYSTIC 3-COLOR SUNBURST оснащена небольшими сменными беспаячными картами, размер которых не превышает знакомые нам кредитные карты. Достаточно вставить одну из них в специальный отсек звуковых настроек, который находится на задней панели инструмента, и Вы сможете наслаждаться мгновенной сменой конфигураций гитары. С такими возможностями создание богатого звучания становится реальным и доступным, равно как и обладание огромными возможностями для экспериментирования со звуком.", "ru");
		guitars[0].setReview("Σχεδιασμός FENDER AMERICAN DELUXE STRAT PLUS MN MYSTIC 3-COLOR Sunburst παραδοσιακή αριστερά - στο πνεύμα του διάσημου «Stratocaster». Δραστικές αλλαγές έχουν επηρεάσει το περιεχόμενο της εσωτερικής κιθάρα - δημιουργική μηχανικοί «Fender» Port τρία εργαλεία σε ένα, κατά το ίδιο χρονικό διάστημα πέτυχε εντυπωσιακά αποτελέσματα χωρίς να δημιουργούνται επιπλέον δυσκίνητη δομές και πεντάλ εφέ. Έξι-ηλεκτρικό FENDER AMERICAN DELUXE STRAT PLUS MN MYSTIC 3-COLOR Sunburst εξοπλισμένα με solderless μικρή αφαιρούμενη κάρτα, το μέγεθος των οποίων δεν υπερβαίνει τη γνωστή πιστωτική κάρτα. Απλά τοποθετήστε ένα από αυτά σε μια ειδική θήκη των ρυθμίσεων ήχου, η οποία είναι στην πίσω πλευρά, και μπορείτε να απολαύσετε τη στιγμιαία αλλαγή των συνθέσεων του κιθάρα. Με χαρακτηριστικά δημιουργώντας ένα πλούσιο ήχο γίνεται πραγματικό και προσιτή, καθώς διαθέτει μεγάλες δυνατότητες για πειραματισμό με τον ήχο.", "gr");
		
		guitars[1] = new GuitarBean("Gibson Les Paul Traditional 2014 HERITAGE",
				"Electric guitar", "G0001", 4250, "res/gibsonlespaultrad");
		
		guitars[1].setReview("первая электрогитара с цельным корпусом от компании Gibson, один из символов рок-музыки и одна из самых долгоживущих и популярных моделей музыкальных инструментов в мире. Модель была разработана в начале 1950 года Тедом Маккарти совместно с гитаристом Лесом Полом (полное имя — Лестер Уильям Полcфусc). Первый Gibson Les Paul был продан в 1952 году. Les Paul — одна из самых известных и часто копируемых различными производителями электрогитар в мире, наряду со Stratocaster и Telecaster.", "ru");
		guitars[1].setReview("The Gibson Les Paul is a solid body electric guitar that was first sold by the Gibson Guitar Corporation in 1952.[1] The Les Paul was designed by Gibson president Ted McCarty, factory manager John Huis and their team, along with guitarist/inventor Les Paul. The Les Paul was originally offered with a gold finish and two P-90 pickups. In 1957, humbucking pickups were added, along with sunburst finishes in 1958. The sunburst 1958–1960 Les Paul – today one of the best-known electric guitar types in the world – was considered a failure, with low production and sales. For 1961, the Les Paul was redesigned into what is now known as the Gibson SG.", "en");
		guitars[1].setReview("Η Gibson Les Paul είναι ένα στερεό σώμα ηλεκτρική κιθάρα που πωλήθηκε για πρώτη φορά από τον Gibson Guitar Corporation το 1952. [1] Το Les Paul σχεδιάστηκε από τον Gibson πρόεδρος Ted McCarty, διευθυντής του εργοστασίου John Huis και η ομάδα τους, μαζί με τον κιθαρίστα / εφευρέτης Les Paul. Το Les Paul αρχικά προσφέρεται με ένα χρυσό φινίρισμα και δύο μαγνήτες P-90. Το 1957, προστέθηκαν pickups humbucking, μαζί με ηλιοφάνεια τελειώνει το 1958. Η Sunburst 1958-1960 Les Paul - σήμερα ένα από τα πιο γνωστά είδη ηλεκτρική κιθάρα στον κόσμο - θεωρήθηκε μια αποτυχία, με χαμηλή παραγωγή και τις πωλήσεις. Για το 1961, η Les Paul επανασχεδιάστηκε σε αυτό που είναι τώρα γνωστή ως ΓΓ Gibson.", "gr");

		guitars[2] = new GuitarBean("Ibanez AF75TDG",
				"Hollow body", "IHB001", 2300, "res/IBANEZAF75TDG");
		
		guitars[2].setReview("Полая дека из клена, гриф из красного дерева и тремоло в духе Bigsby - все это Ibanez AF75TDG. Цвет придает гитаре более винтажный внешний вид, делая ее похожей на Gretsch White Falcon", "ru");
 

		guitars[3] = new GuitarBean("Yamaha FSX-315",
				"Acoustic guitar", "YA0001", 1200, "res/yamahafsx315c");

		guitars[3].setReview("Качественная электроакустическая гитара с системой звукоснимателей - доступная, с удобным грифом и приятным внешним видом. Гитара, которая подойдет как для домашних занятий, так и для выступлений на сцене. Контактные звукоснимателb Однополосный активный предусилитель и датчик, основанный на технологии A.R.T.(Acoustic Resonance Transducer). Эта система очень компактна, но, несмотря на это, она дает возможность получить естественный акустический звук. Имеет бустер средних частот и качественный точный тюнер.", "ru");

		guitars[4] = new GuitarBean("Martinez c91n",
				"Acoustic guitar", "MA001", 125, "res/martinezc91n");
		
		guitars[4].setReview("Классическая гитара MARTINEZ C-91N – это одна из самых недорогих моделей. Дека изготовлена из липы, гриф – из клёна. Такой инструмент можно смело рекомендовать начинающим гитаристам, либо в качестве подарка для тех, кто ещё только собирается начать обучение музыке. Несмотря на низкую стоимость, гитара обладает приятным, мягким звучанием – не таким ярким и насыщенным, как у более дорогих моделей, но одним из лучших в своём классе. В этом легко убедиться, если посмотреть наш видеоролик – сложно поверить, что это звучит именно MARTINEZ C-91N!", "ru");

		guitars[5] = new GuitarBean("Gibson ES-335",
				"Hollow body", "GHB001", 12000, "res/gibsonES-335");

		guitars[5].setReview("ES-335 начали производить 56 лет назад, в 1958 году. С тех пор эта гитара стала бесспорной классикой, причем не только классикой Gibson, но классикой всех электрогитар. То, чем стал Les Paul для цельнокорпусных гитар, ES-335 стала для полуакустических – красивая, универсальная, желанная для гитаристов всех жанров.", "ru");

		page = "list";
		currentItem = 0;
		
		cartItems = new CartItemList();
		cartItems.add(guitars[0]);
		cartItems.add(guitars[4]);
		cartItems.add(guitars[5]);
		cartItems.add(guitars[1]);		
		firstStart = true;
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html");
		
		request.getSession().setAttribute("page", "main");
		
		// session data removes every time when server restarts and 
		// when exit from account action is taken
		
		if (firstStart || 
				(request.getParameter("action")!=null && request.getParameter("action").equals("exit"))) {
			// clean last session info
			request.getSession().invalidate();
			
			firstStart = false;
		}
		
		if (true) {
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("lastFilter")) {
						this.filterItems(cookies[i].getValue(), request, response);
						firstStart = !firstStart;
						break;
					}
				}
				
			}
		}
		
		if (request.getParameter("search") != null) {
			filterItems(request.getParameter("search"), request, response);
		}
		if (request.getParameter("page") != null)
			this.page = request.getParameter("page");
				
		if (page != null && page.equals("productCard")) {
			goToProductCard(request, response);
		} else if (page != null && page.equals("cart")) {
			goToCart(request, response);
		} else if (page != null && page.equals("auth")) {
			goToAuthPage(request, response);
		} else if (page != null && page.equals("authError")) {
			goToAuthErrorPage(request, response);
		} else {
			try {
				boolean addedToCart = false;
				
				if (request.getParameter("cartAdd") != null) {
					for (int i = 0; i < guitars.length; i++) {
						if (guitars[i].getId() == Integer.parseInt(request.getParameter("cartAdd"))) {
							cartItems.add(guitars[i]);
						}
					};
					addedToCart = true;
				}
				
				String initBookmark = getInitParameter("initial_bookmark");
				request.getSession().setAttribute("initial_bookmark", initBookmark);
				request.getSession().setAttribute("isCartEmpty", isCartEmpty);
				
				Translator tr = getCurrentTranslator(request, response);
				
				request.setAttribute("tr", tr);
				request.setAttribute("initBookmark", initBookmark);
				request.setAttribute("guitars", guitars);
				request.setAttribute("toCartAdded", addedToCart);
				request.setCharacterEncoding("UTF-8");
				request.getServletContext().getRequestDispatcher("/productList.jsp")
				.forward(request, response);
						
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (ServletException se) {
				se.printStackTrace();
			}
			
		}

		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
	
	private void goToProductCard(HttpServletRequest request, HttpServletResponse response) {
		try {
			String initBookmark = getInitParameter("initial_bookmark");
			
			if (request.getParameter("itemId") != null) 
				currentItem = Integer.parseInt(request.getParameter("itemId"));
			
			boolean addedToCart = false;
			
			if (request.getParameter("cartAdd") != null) {
				cartItems.add(guitars[currentItem-1]);
				addedToCart = true;
			}
						
			Translator tr = getCurrentTranslator(request, response);
			
			request.setAttribute("tr", tr);
			request.setAttribute("initBookmark", initBookmark);
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("toCartAdded", addedToCart);
			request.setAttribute("product", guitars[currentItem-1]);
			request.getServletContext().getRequestDispatcher("/index.jsp?init="+initBookmark)
			.forward(request, response);
					
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
		
	}
	
	private void goToCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			Translator tr = getCurrentTranslator(request, response);
			
			// if itemNumberChanged
			int index = -1;
			if (request.getParameter("dec") != null) {
				index = Integer.parseInt(request.getParameter("dec"));
				cartItems.decreaseItemCount(index);
			} else if (request.getParameter("inc") != null) {
				index = Integer.parseInt(request.getParameter("inc"));
				cartItems.increaseItemCount(index);
			} else if (request.getParameter("del") != null) {
				index = Integer.parseInt(request.getParameter("del"));
				cartItems.removeItem(index);
			}
			request.setAttribute("tr", tr);
			request.setAttribute("cartItems", cartItems);
			request.setAttribute("isLogged", isLogged);
			request.setCharacterEncoding("UTF-8");
			request.getServletContext().getRequestDispatcher("/cart.jsp")
			.forward(request, response);
					
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
		
	}
	
	private void filterItems(String filter, HttpServletRequest request, HttpServletResponse response) {
		List<GuitarBean> items = (List<GuitarBean>) Arrays.asList(guitars);
		ArrayList<GuitarBean> filteredItems = new ArrayList<>();
		
		Cookie lastFilter = new Cookie("lastFilter", filter);
		lastFilter.setMaxAge(10);
		firstStart = true;
		
		for (GuitarBean guitar : items) {
			if (guitar.getName().contains(filter)) {
				filteredItems.add(guitar);
			}
		}
		
		try {
			String initBookmark = getInitParameter("initial_bookmark");
			
			System.out.println(initBookmark);
			
			Translator tr = getCurrentTranslator(request, response);
			
			response.addCookie(lastFilter);
			request.setAttribute("tr", tr);
			request.setAttribute("initBookmark", initBookmark);
			request.setAttribute("guitars", filteredItems.toArray());
			request.setCharacterEncoding("UTF-8");
			request.getServletContext().getRequestDispatcher("/productList.jsp")
			.forward(request, response);
					
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
		
		
	}
	
	private void goToAuthPage(HttpServletRequest request, HttpServletResponse response) {
		
		Translator tr = getCurrentTranslator(request, response);
		
		request.setAttribute("tr", tr);
	
		try {
			
			request.setCharacterEncoding("UTF-8");
			request.getServletContext().getRequestDispatcher("/authPage.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void goToAuthErrorPage(HttpServletRequest request, HttpServletResponse response) {
		
		Translator tr = getCurrentTranslator(request, response);
		
		request.setAttribute("tr", tr);
	
		try {
			
			request.setCharacterEncoding("UTF-8");
			request.getServletContext().getRequestDispatcher("/authPageError.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void goAndProceedOrder(HttpServletRequest request, HttpServletResponse response) {
		
		Translator tr = getCurrentTranslator(request, response);
		
		request.setAttribute("tr", tr);
	
		try {
			
			request.setCharacterEncoding("UTF-8");
			request.getServletContext().getRequestDispatcher("/authPage.jsp")
			.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Translator getCurrentTranslator(HttpServletRequest request, HttpServletResponse response) {
		
		String locale = request.getParameter("locale");
		Translator tr;
		if (locale == null) {
			
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("locale")) {
						locale = (String) cookies[i].getValue();
					}
				}
			}
			
			if (locale != null) {
				tr = new Translator(new Locale(locale, locale.toUpperCase()));
			} else {
				tr = new Translator(new Locale("ru", "RU"));
			}
								
		}else {
			tr = new Translator(new Locale(locale, locale.toUpperCase()));
			response.addCookie(new Cookie("locale", locale));
		}
		
		request.getSession().setAttribute("translator", tr);
		return tr;
		
	}
	
	public GuitarBean findGuitarByVendorCode(String vendorCode) {
		
		for (GuitarBean guitar : guitars) {
			if (guitar.getVendorCode() == vendorCode) {
				return guitar;
			}
		}
		
		return null;
	}

	public static GuitarBean[] getItems() {
		return guitars;
	}
	
	public static CartItemList getCartItems() {
		return cartItems;
	}
	
	public static void emptyCart() {
		isCartEmpty = true;
		cartItems.clear();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		MainServlet.sessionFactory = sessionFactory;
	}

}
