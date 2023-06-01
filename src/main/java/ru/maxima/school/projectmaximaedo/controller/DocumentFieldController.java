package ru.maxima.school.projectmaximaedo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.dto.DocumentFieldDto;
import ru.maxima.school.projectmaximaedo.impl.DocumentFieldServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/document_fields")
public class DocumentFieldController {
    private final DocumentFieldServiceImpl documentFieldService;
    @Autowired
    public DocumentFieldController(DocumentFieldServiceImpl documentFieldService) {
        this.documentFieldService = documentFieldService;
    }

    /**
     * Считывание списка полей документа
     * @return список полей документа или ошибку
     */
    @GetMapping()
    public ResponseEntity<List<DocumentFieldDto>> getDocumentFields() {
        List<DocumentFieldDto> documentFields = documentFieldService.getAll();
        if(documentFields == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список полей документа пуст");
        }
        return new ResponseEntity<>(documentFields, HttpStatus.OK);
    }

    /**
     * Считывание поля документа
     * @param id
     * @return поле документа или ошибку
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentFieldDto> getDocumentField(@PathVariable("id") Long id) {
        DocumentFieldDto documentFieldDto = documentFieldService.getById(id);
        if(documentFieldDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Поле с идентификатором " + id + " не найдено");
        }
        return new ResponseEntity<>(documentFieldDto, HttpStatus.OK);
    }

    /**
     * Обновление поля документа
     * @param id
     * @param documentFieldDto
     * @return статус обновления поля документа
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateDocumentField(@Valid @PathVariable("id") Long id,
                                                           @RequestBody DocumentFieldDto documentFieldDto) {
        if (documentFieldService.update(documentFieldDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Поле с идентификатором " + id + " не найдено");
        }
        return ResponseEntity.accepted().body(new Response("Обновление поля документа прошло успешно", LocalDateTime.now()));
    }

    /**
     * Создание поля документа
     * @param documentFieldDto
     * @return статус создания поля документа
     */
    @PostMapping()
    public ResponseEntity<Response> createDocumentField(@Valid @RequestBody DocumentFieldDto documentFieldDto) {
        if (documentFieldService.create(documentFieldDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в создании поля документа");
        }
        return ResponseEntity.accepted().body(new Response("Создание поля документа прошло успешно", LocalDateTime.now()));
    }

    /**
     * Удаление поля документа
     * @param id
     * @return статус удаления поля документа
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDocumentField(@PathVariable("id") Long id) {
        if (documentFieldService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление поля документа прошло успешно", LocalDateTime.now()));
    }
}
