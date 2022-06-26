package utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HelperMethods {

    public static List<String> extractTextFromWebElements(List<WebElement> webElements){
        return webElements
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
