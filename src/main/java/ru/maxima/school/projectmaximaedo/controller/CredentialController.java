package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.CredentialDto;
import ru.maxima.school.projectmaximaedo.serviceImpl.CredentialServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/credential")
public class CredentialController {
    private final CredentialServiceImpl credentialService;
    @Autowired
    public CredentialController(CredentialServiceImpl credentialService) {
        this.credentialService = credentialService;
    }
    /**
     * Считывание списка учетных данных
     * @return списка учетных данных или ошибку
     */
    @GetMapping()
    public ResponseEntity<List<CredentialDto>> getComments() {
        List<CredentialDto> credentials = credentialService.getAll();
        if(credentials == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список учетных данных  пуст");
        }
        return new ResponseEntity<>(credentials, HttpStatus.OK);
    }

    /**
     * Считывание учетных данных
     * @param id
     * @return Учетные данные  или ошибку
     */
    @GetMapping("/{id}")
    public ResponseEntity<CredentialDto> getComment(@PathVariable("id") Long id) {
        CredentialDto credentialDto = credentialService.getById(id);
        if(credentialDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Учетные данные с " + id + " не найден");
        }
        return new ResponseEntity<>(credentialDto, HttpStatus.OK);
    }

    /**
     * Обновление учетных данных
     * @param id
     * @param credentialDto
     * @return статус обновления учетных данных
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateComment(@PathVariable("id") Long id,
                                                  @Valid @RequestBody CredentialDto credentialDto) {
        if (credentialService.update(credentialDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Учетные данные с идентификатором " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление учетных данных прошло успешно", LocalDateTime.now()));
    }

    /**
     * Создание учетных данных
     * @param credentialDto
     * @return статус создания учетных данных
     */
    @PostMapping()
    public ResponseEntity<Response> createComment(@Valid @RequestBody CredentialDto credentialDto) {
        if (credentialService.create(credentialDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в создании учетных данных");
        }
        return ResponseEntity.accepted().body(new Response("Создание учетных данных прошло успешно", LocalDateTime.now()));
    }

    /**
     * Удаление учетных данных
     * @param id
     * @return статус удаления учетных данных
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCredential(@PathVariable("id") Long id) {

        if (credentialService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление учетных данных прошло успешно", LocalDateTime.now()));
    }

}
