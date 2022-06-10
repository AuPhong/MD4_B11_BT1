package com.blog.formatter;

import com.blog.model.Category;
import com.blog.service.blog.BlogService;
import com.blog.service.blog.IBLogService;
import com.blog.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;
@Component

public class CategoryFormatter implements Formatter<Category> {

    private ICategoryService categoryService;

    @Autowired
    public CategoryFormatter(ICategoryService iCategoryService){
        this.categoryService = iCategoryService;
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        Optional<Category> category = categoryService.findById(Long.parseLong(text));
        return category.get();
    }

    @Override
    public String print(Category object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
