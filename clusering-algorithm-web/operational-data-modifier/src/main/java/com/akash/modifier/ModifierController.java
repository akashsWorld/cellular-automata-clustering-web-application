package com.akash.modifier;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("modifier/modify")
@RequiredArgsConstructor
public class ModifierController {

    private final ModifierService modifierService;
    @DeleteMapping("deleteFeature")
    public ResponseEntity<ArrayList<ArrayList<String>>> deleteFeature(@RequestBody ArrayList<ArrayList<String>> data,
                                                                      @RequestParam Integer index) throws IndexOutOfBoundsException{
        return ResponseEntity.ok(modifierService.deleteOneIndex(data,index));
    }

    @DeleteMapping("deleteFeature/{indexes}")
    public ResponseEntity<ArrayList<ArrayList<String>>> deleteMultipleFeature(@RequestBody ArrayList<ArrayList<String>> data,
                                                              @PathVariable List<Integer> indexes) throws IndexOutOfBoundsException{

        return ResponseEntity.ok(modifierService.deleteIndexes(data,new ArrayList<>(indexes)));
    }

}
