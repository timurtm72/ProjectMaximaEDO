package ru.maxima.school.projectmaximaedo.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maxima.school.projectmaximaedo.dto.DocumentTemplateDto;
import org.springframework.web.server.ResponseStatusException;
import ru.maxima.school.projectmaximaedo.serviceImpl.DocumentTemplateServiceImpl;
import ru.maxima.school.projectmaximaedo.utils.Response;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/document_templates")
public class DocumentTemplateController {
    private final DocumentTemplateServiceImpl documentTemplateService;

    @Autowired
    public DocumentTemplateController(DocumentTemplateServiceImpl documentTemplateService) {
        this.documentTemplateService = documentTemplateService;
    }

    /**
     * Считывание списка шаблонов документов
     * @return список шаблонов документов или ошибку
     */
    @GetMapping()
    public ResponseEntity<List<DocumentTemplateDto>> getDocumentTemplates() {
        List<DocumentTemplateDto> documentTemplates = documentTemplateService.getAll();
        if(documentTemplates == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Список шаблонов документов пуст");
        }
        return new ResponseEntity<>(documentTemplates, HttpStatus.OK);
    }

    /**
     * Считывание шаблона документа
     * @param id
     * @return шаблон документа или ошибку
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentTemplateDto> getDocumentTemplate(@PathVariable("id") Long id) {
        DocumentTemplateDto documentTemplateDto = documentTemplateService.getById(id);
        if(documentTemplateDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Документ с идентификатором " + id + " не найден");
        }
        return new ResponseEntity<>(documentTemplateDto, HttpStatus.OK);
    }

    /**
     * Обновление шаблона документа
     * @param id
     * @param documentTemplateDto
     * @return статус обновления шаблона документа
     */
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateDocumentTemplate(@PathVariable("id") Long id,
                                                           @Valid @RequestBody DocumentTemplateDto documentTemplateDto) {
        if (documentTemplateService.update(documentTemplateDto,id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Документ с идентификатором " + id + " не найден");
        }
        return ResponseEntity.accepted().body(new Response("Обновление шаблона документа прошло успешно", LocalDateTime.now()));
    }

    /**
     * Создание шаблона документа
     * @param documentTemplateDto
     * @return статус создания шаблона документа
     */
    @PostMapping()
    public ResponseEntity<Response> createDocumentTemplate(@Valid @RequestBody DocumentTemplateDto documentTemplateDto){
         if (documentTemplateService.create(documentTemplateDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ошибка в создании шаблона документа или id поля документа начинается с 1");
        }
        return ResponseEntity.accepted().body(new Response("Создание шаблона документа прошло успешно", LocalDateTime.now()));
    }

    /**
     * Удаление шаблона документа
     * @param id
     * @return статус удаления шаблона документа
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteDocumentTemplate(@PathVariable("id") Long id) {

        if (documentTemplateService.safeDelete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Неверно заданный id для удаления");
        }
        return ResponseEntity.accepted().body(new Response("Удаление шаблона документа прошло успешно", LocalDateTime.now()));
    }

}
