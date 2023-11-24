/**
 * Ajay Kumar Dhonthoju
 */


package com.bandiClasses.DMS.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bandiClasses.DMS.Model.Dog;
import com.bandiClasses.DMS.Model.Trainer;
import com.bandiClasses.DMS.Repository.DogRepository;
import com.bandiClasses.DMS.Repository.TrainerRepository;



@Controller
public class DogController {
	ModelAndView mv = new ModelAndView();
	@Autowired
	DogRepository dr;
	
	@Autowired
	TrainerRepository trainerRepo;
	
//	@RequestMapping("dogHome")
//	public String home() {
//		return "home";
//	}
	
	@RequestMapping("dogHome")
	public ModelAndView home() {
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("add")
	public ModelAndView add() {
		mv.setViewName("addNewDog.html");
		Iterable<Trainer> trainerList = trainerRepo.findAll();
		mv.addObject("trainers", trainerList);
		return mv;
	}
	
	@RequestMapping("addNewDog")
	public ModelAndView addNewDog(Dog dog, @RequestParam("trainerId") int id) {
		
		Trainer trainer = trainerRepo.findById(id).orElse(new Trainer() );
		dog.setTrainer(trainer);
		dr.save(dog);
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("viewModifyDelete")
	public ModelAndView viewDogs() {
		mv.setViewName("viewDogs");
		Iterable<Dog> dogsList = dr.findAll();
		mv.addObject("dogs", dogsList);
		return mv;
	}

	@RequestMapping("editDog")
	public ModelAndView editDog(Dog d) {
		dr.save(d);
		mv.setViewName("editDog");
		return mv;
	}
	@RequestMapping("deleteDog")
	public ModelAndView deleteDog(Dog dog) {
		//based on id
//		Optional<Dog> dogFound = dr.findById(dog.getId());
//		if (dogFound.isPresent()) {
//			dr.delete(dog);
//		}
//		return home();
		
		//based on the name
		
//		List<Dog> dogsFound = dr.findByName(dog.getName());
//		
//		for(Dog d : dogsFound) {
//			dr.delete(d);
//		}
//		return home();
		
		Dog d = dr.findById(dog.getId()).orElse(new Dog());
		dr.delete(d);
		return home();
	}
	
	@RequestMapping("search")
	public ModelAndView searchById(int id){
		Dog dogFound = dr.findById(id).orElse(new Dog());
		
		mv.addObject(dogFound);
		
		mv.setViewName("searchResults");
		
		return mv;
	}
	
	@RequestMapping("addTrainer")
	public ModelAndView addTrainer() {
		mv.setViewName("addNewTrainer");
		return mv;
	}
	
	
	@RequestMapping("trainerAdded")
	public ModelAndView addNewTrainer(Trainer trainer) {
		trainerRepo.save(trainer);
		mv.setViewName("home");
		return mv;
	}
}
