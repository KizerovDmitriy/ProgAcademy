package com.example.exam;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    private final Repository repository;

    public CalcController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/calc")
    public Long calculate(@RequestParam String type,
                          @RequestParam Long x,
                          @RequestParam Long y) {
        DataBase dataBase = new DataBase(type, x, y);
        long result;
        switch (type) {
            case "sum" -> {
                result = x + y;
                dataBase.setResult(result);
                repository.save(dataBase);
            }
            case "mult" -> {
                result = x * y;
                dataBase.setResult(result);
                repository.save(dataBase);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return result;
    }
}
