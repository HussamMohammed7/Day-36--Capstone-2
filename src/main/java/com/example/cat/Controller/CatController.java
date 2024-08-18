package com.example.cat.Controller;

import com.example.cat.Model.Cat;
import com.example.cat.Service.CatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping("/get")
    public ResponseEntity getAllCats() {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getCats());
    }

    @PostMapping("/add")
    public ResponseEntity addCat(@Valid @RequestBody Cat cat, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        catService.addCat(cat);
        return ResponseEntity.status(HttpStatus.OK).body("Cat added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCat(@PathVariable Integer id, @Valid @RequestBody Cat cat, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        catService.updateCat(id, cat);
        return ResponseEntity.status(HttpStatus.OK).body("Cat updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCat(@PathVariable Integer id) {
        catService.removeCat(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cat deleted successfully");
    }

    // New endpoints
    @GetMapping("/adopted")
    public ResponseEntity getAdoptedCats() {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getAdoptedCats());
    }

    @GetMapping("/available")
    public ResponseEntity getUnadoptedCats() {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getAvailableCats());
    }

    @GetMapping("/age-range")
    public ResponseEntity getCatsByAgeRange(@RequestParam Integer startAge, @RequestParam Integer endAge) {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getCatsByAgeRange(startAge, endAge));
    }

    @GetMapping("/breed")
    public ResponseEntity getCatsByBreed(@RequestParam String breed) {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getCatsByBreed(breed));
    }

    @GetMapping("/gender")
    public ResponseEntity getCatsByGender(@RequestParam String gender) {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getCatsByGender(gender));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity getCatsByOwner(@PathVariable Integer ownerId) {
        return ResponseEntity.status(HttpStatus.OK).body(catService.getCatsByOwner(ownerId));
    }
}
