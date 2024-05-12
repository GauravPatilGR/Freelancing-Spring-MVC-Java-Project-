package test.controllers;



import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import test.beans.Freelancer;
import test.dao.CompanyDao;


@Controller
public class FreelancerController {
	
	@Autowired
	CompanyDao fd;
	
	
	@RequestMapping(value = "/freelancerdata",method = RequestMethod.POST)
	public String dataenterfreelancer(@ModelAttribute("c1") Freelancer c1,@RequestParam("fprofilephoto") MultipartFile filename,ModelMap mm) throws IOException
	{
		if(c1.getPassword().equals(c1.getCpassword()))
		{
		
        String f=filename.getOriginalFilename();
		
		String path="C:\\Users\\gaura\\eclipse-workspace\\SpringMVCPersonal_Project\\src\\main\\webapp\\files\\webimages";
		
		BufferedOutputStream bf= new BufferedOutputStream(new FileOutputStream(path+"/"+f));
		
		byte [] b=filename.getBytes();
		
		bf.write(b);
		bf.close();
		
		c1.setFpfile(f);
		
		
		
		fd.datasave(c1);
		
		mm.addAttribute("message","Register Successfully Done Login Now");
		
		return "loginf";
		}
		
		else {
			
			mm.addAttribute("messagee","Something Went Wrong Try Again!");
			return "registerf";
		}
	}
	
	
	@RequestMapping(value = "/Logincheakf",method = RequestMethod.POST)
	public String logincheakfreelancer(@RequestParam("email") String email,@RequestParam("password") String password)
	{
		
		
	List<Freelancer> f	=fd.checkdata(email,password);
	
	if(f.isEmpty())
	{
		return "redirect:/loginf";
	}
		
		
		
	else 
	{
	  return "redirect:/homef";
	}
		
	}
	
	@RequestMapping("/homef")
	public String homepagef()
	{
		
		return "homef";
	}
	
	
	
	@RequestMapping("/loginf")
	public String freelancerloginpage()
	{
		
		return "loginf";
		
	}
	
	@RequestMapping("/registerf")
	public String freelancerregisterpage()
	{
		return "registerf";
	}

}