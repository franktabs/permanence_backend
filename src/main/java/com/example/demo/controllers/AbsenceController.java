package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.AbsenceConvertController;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("absence")
public class AbsenceController extends AbsenceConvertController {

/*    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody AbsenceDto absenceDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Absence absence = convertDtoToAbsence(absenceDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertAbsenceToDto(absenceService.create(absence), 1, 1, 1));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }*/

}
