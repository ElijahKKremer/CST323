package com.elijah.cloudtest.cloud_test_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/about")
    public String about() {
        return "about";
    }

  //  @GetMapping("/products")
    //public String products() {
      //  return "products";
    //}
}