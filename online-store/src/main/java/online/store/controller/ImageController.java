package online.store.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import online.store.constants.OnlineShopConstant;

@Controller
@RequestMapping("/image")
public class ImageController {
	@RequestMapping(value = "/getImg/{fineName:.+}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImageAsByteArray(@PathVariable("fineName") String fineName) throws IOException {
	    String dir =  OnlineShopConstant.UPLOAD_PATH;
		InputStream in = new FileInputStream(new File(dir + fineName));
	    return IOUtils.toByteArray(in);
	}
	
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public @ResponseBody String submit(@RequestParam("file") MultipartFile[] files, ModelMap modelMap) throws IllegalStateException, IOException {
//	    modelMap.addAttribute("files", files);
		String dir = OnlineShopConstant.UPLOAD_PATH;
		String fileName = UUID.randomUUID().toString();
		String suffix = "";
		if(files[0].getContentType().endsWith("jpeg")) {
			suffix = ".jpg";
		}
		files[0].transferTo(new File(dir + fileName + suffix));
	    return files[0].getContentType();		
	}
}
