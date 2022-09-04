package com.kadirirpik.controller;

import com.kadirirpik.business.dto.ProductDto;
import com.kadirirpik.business.service.IProductservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class ProductController {
    private IProductservice productService;
    @Autowired
    public ProductController(IProductservice productService) {
        this.productService = productService;
    }

    @GetMapping("/add/product")
    public String addProduct(Model model){
        model.addAttribute("newProduct", new ProductDto());
        return "addProduct";
    }

    @PostMapping("/add/product")
    public String postProduct(@Valid @ModelAttribute("newProduct") ProductDto productDto, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "addProduct";
        }
        productService.saveProduct(productDto);
        redirectAttributes.addFlashAttribute("addProductSuccess",true);
        return "redirect:/add/product";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("deleteSuccess", true);
        return "redirect:/index";
    }

    @GetMapping("/update/product/{id}")
    public String getUpdateProduct(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes){
        ProductDto productDto = productService.getProductDto(id);
        if (productDto != null){
            model.addAttribute("updateProduct", productDto);
            return "updateProduct";
        }
        redirectAttributes.addFlashAttribute("notFoundProduct", true);
        return "redirect:/index";
    }

    @PostMapping("/update/product/{id}")
    public String postUpdateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute("updateProduct") ProductDto productDto,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "updateProduct";
        }
        ProductDto productDtoNew = productService.updateProduct(id, productDto);
        String update = productDtoNew != null ? "updateSuccess" : "updateError";
        redirectAttributes.addFlashAttribute(update,true);
        return "redirect:/index";
    }
}
