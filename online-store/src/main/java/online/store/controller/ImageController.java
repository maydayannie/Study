package online.store.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/image")
public class ImageController {
	@RequestMapping(value = "/getImg/{fineName:.+}", method = RequestMethod.GET)
	public @ResponseBody byte[] getImageAsByteArray(@PathVariable("fineName") String fineName) throws IOException {
	    String dir = "/Users/annieshih/Desktop/onlinepic/";
		InputStream in = new FileInputStream(new File(dir + fineName));
	    return IOUtils.toByteArray(in);
	}
}
