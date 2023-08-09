package com.uday.busreservation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uday.busreservation.domain.Booking;
import com.uday.busreservation.domain.Bus;
import com.uday.busreservation.domain.User;
import com.uday.busreservation.service.BookingService;
import com.uday.busreservation.service.BusService;
import com.uday.busreservation.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BusController {

	@Autowired
	private BusService busService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookingService bookingService;

	@GetMapping("/adminhome")
	public String home(Model m) {
		List<Bus> list = busService.listAll();
		m.addAttribute("list", list);
		System.out.println(list);
		return "adminhome";
	}

	@GetMapping("/addbus")
	public String addBus(Model model) {
		model.addAttribute("bus", new Bus());
		return "addbusform";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBus(@ModelAttribute("student") Bus bus) {
		busService.save(bus);
		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditBusPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("addbusform");
		Bus bus = busService.get(id);
		mav.addObject("bus", bus);
		return mav;

	}

	@RequestMapping("/delete/{id}")
	public String deletestudent(@PathVariable(name = "id") int id) {
		busService.delete(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/asearch", method = RequestMethod.GET)
	public String adminSearch(@RequestParam("ssource") String src, @RequestParam("sdestination") String dst, Model m) {
		List<Bus> list = busService.listAll();
		System.out.println(src + " " + dst);
		List<Bus> filteredlis = new ArrayList<Bus>();
		if (src.equals("") && dst.equals("")) {
			m.addAttribute("list", list);
		} else if (src.equals("")) {
			for (Bus bus : list) {
				if (bus.getStops().equalsIgnoreCase(dst)) {
					filteredlis.add(bus);
				}
			}
			m.addAttribute("list", filteredlis);
		} else if (dst.equals("")) {
			for (Bus bus : list) {
				if (bus.getBoards().equalsIgnoreCase(src)) {
					filteredlis.add(bus);
				}
			}
			m.addAttribute("list", filteredlis);
		} else {
			for (Bus bus : list) {
				if (bus.getBoards().equalsIgnoreCase(src) && bus.getStops().equalsIgnoreCase(dst)) {
					filteredlis.add(bus);
				}
			}
			m.addAttribute("list", filteredlis);
		}
		return "adminhome";
	}

	@GetMapping("/")
	public String index(Model m) {
		List<Bus> list = busService.listAll();
		m.addAttribute("list", list);
		System.out.println(list);
		return "index";
	}

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping("/loginpage")
	public String loginpage() {
		return "login";
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		System.out.println(user);
		userService.save(user);
		return "redirect:/loginpage";
	}

	@RequestMapping(value = "/validateuser", method = RequestMethod.POST)
	public String validateUser(@RequestParam("uname") String name, @RequestParam("pass") String password,
			HttpSession session) {
		List<User> list = userService.listAll();
		for (User u : list) {
			if (u.getName().equalsIgnoreCase(name) && u.getPassword().equals(password)) {
				session.setAttribute("loginuser", u);
				session.setAttribute("uname", u.getName());
				session.setAttribute("uid", u.getId());
				return "redirect:/";
			}
		}
		if(name.equalsIgnoreCase("uk") && password.equals("123"))
			return "redirect:/adminhome";
		return "redirect:/login";
	}

	@RequestMapping("/logoutuser")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("loginuser");
		return "redirect:/";
	}

	@RequestMapping(value="/usersearch",method=RequestMethod.POST)
       public ModelAndView showEditStudentPage(@RequestParam("start") String src, @RequestParam("end") String dst) {
           ModelAndView mav = new ModelAndView("index");
     	  List<Bus> list = busService.listAll();
    		System.out.println(src + " " + dst);
    		List<Bus> filteredlis = new ArrayList<Bus>();
    		if (src.equals("") && dst.equals("")) {
    			mav.addObject("list", list);
    		} else if (src.equals("")) {
    			for (Bus bus : list) {
    				if (bus.getStops().equalsIgnoreCase(dst)) {
    					filteredlis.add(bus);
    				}
    			}
    			mav.addObject("list", filteredlis);
    		} else if (dst.equals("")) {
    			for (Bus bus : list) {
    				if (bus.getBoards().equalsIgnoreCase(src)) {
    					filteredlis.add(bus);
    				}
    			}
    			mav.addObject("list", filteredlis);
    		} else {
    			for (Bus bus : list) {
    				if (bus.getBoards().equalsIgnoreCase(src) && bus.getStops().equalsIgnoreCase(dst)) {
    					filteredlis.add(bus);
    				}
    			}
    			mav.addObject("list", filteredlis);
    		}
    		return mav;
	}
	@RequestMapping("/book/{id}")
	public String bookBus(@PathVariable(name = "id") int id,HttpSession session) {
		Bus bus = busService.get(id);
		session.setAttribute("bus", bus);
		return "redirect:/booking";
	}
	@RequestMapping("/booking")
	public String booking() {
		return "booking";
	}
	@RequestMapping("/confirmbooking")
	public String confirmBooking(@RequestParam("seatsSelected") String seats,HttpSession session) {
		System.out.println(seats);
		Booking booking = new Booking();
		Bus bus = (Bus) session.getAttribute("bus");
		User user = (User) session.getAttribute("loginuser");
		booking.setBus(bus);
		booking.setUser(user);
		String seatnos = "";
		int ct = 1;
		for(int i=0;i<seats.length();i++) {
			if(seats.charAt(i) == ',') {
				seatnos = seatnos + ' ';
				ct += 1;
			}
			seatnos += seats.charAt(i);
		}
		booking.setSeatnos(seatnos);
		int price = ct * bus.getPrice();
		booking.setPrice(price);
		bookingService.save(booking);
		System.out.println(booking);
		session.setAttribute("booking",booking);
		return "confirmed";
	}
}
