package com.product.price.calculator.controller;

import com.product.price.calculator.data.ProductListObj;
import com.product.price.calculator.model.Product;
import com.product.price.calculator.service.ProductPriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductPriceController.class)
class ProductPriceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductPriceService productPriceService;

    @Test
    void getAllProducts() throws Exception {
        when(productPriceService.findAllProducts()).thenReturn(ProductListObj.getProductList());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void saveProduct() throws Exception {
        Product product = new Product(1, "Penguin Ears", 20, 175.00);
        when(productPriceService.saveProduct(product)).thenReturn(ProductListObj.getProductList().get(0));

        String productRequest = "{\"id\": 2,\"name\":\"Horseshoe\",\"units\":5,\"cartonPrice\":825.00}";
        mockMvc.perform(MockMvcRequestBuilders
                .post("/products")
                .accept(MediaType.APPLICATION_JSON).content(productRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void calculatePPrice() throws Exception {
        when(productPriceService.getProduct(anyInt())).thenReturn(ProductListObj.getProductList().get(0));

        String priceRequest = "{\"id\" : \"1\",\"quantity\":0}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/products/price")
                .accept(MediaType.APPLICATION_JSON).content(priceRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findPrice() throws Exception {
        when(productPriceService.getProduct(anyInt())).thenReturn(ProductListObj.getProductList().get(0));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/price/" + anyInt()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}