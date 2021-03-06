package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.service.api.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@Api("Categories")
@RequestMapping("/rest/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("Create category")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> addCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>(category.getId(), HttpStatus.CREATED);
    }

    @ApiOperation("Modify category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
    public BigInteger modifyCategory(@PathVariable("categoryId") BigInteger categoryId, @RequestBody Category category) {
        Category oldCategory = categoryService.getCategory(categoryId);
        BeanUtils.copyProperties(category, oldCategory, "id");
        categoryService.saveCategory(oldCategory);
        return oldCategory.getId();
    }

    @ApiOperation("Get category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public Category getCategory(@PathVariable("categoryId") BigInteger categoryId) {
        return categoryService.getCategory(categoryId);
    }

    @ApiOperation("Get categories list by query")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Category> getCategories(@RequestParam(value = "q", required = false) String query) {
        return categoryService.getCategories(query);
    }

    @ApiOperation("Delete category")
    @RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@PathVariable("categoryId") BigInteger categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
